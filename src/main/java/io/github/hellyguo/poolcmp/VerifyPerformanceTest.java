/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the License); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an AS IS BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.hellyguo.poolcmp;

import cn.danielw.fop.DisruptorObjectPool;
import cn.danielw.fop.PoolConfig;
import cn.danielw.fop.Poolable;
import cn.itcraft.frogspawn.ObjectsMemoryPool;
import cn.itcraft.frogspawn.ObjectsMemoryPoolFactory;
import io.github.hellyguo.poolcmp.domain.DemoPojo;
import io.github.hellyguo.poolcmp.misc.DemoPojoCreator;
import io.github.hellyguo.poolcmp.misc.DemoPojoObjectFastPoolFactory;
import io.github.hellyguo.poolcmp.misc.DemoPojoSAllocator;
import io.github.hellyguo.poolcmp.misc.PooledSlotDemoPojo;
import stormpot.BlazePool;
import stormpot.Config;
import stormpot.Timeout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class VerifyPerformanceTest {

    private static final int WARMUP_ROUNDS = 10_000;
    private static final int TEST_ROUNDS = 1_000_000;
    private static final int THREAD_COUNT = 8;

    private static ObjectsMemoryPool<DemoPojo> frogspawnPool;
    private static BlazePool<PooledSlotDemoPojo> stormpotPool;
    private static DisruptorObjectPool<DemoPojo> fastPool;

    public static void main(String[] args) throws Exception {
        setUp();
        try {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("对象池性能验证测试");
            System.out.println("=".repeat(60));
            System.out.println("预热轮次: " + WARMUP_ROUNDS);
            System.out.println("测试轮次: " + TEST_ROUNDS);
            System.out.println("多线程数: " + THREAD_COUNT);
            System.out.println("=".repeat(60));

            testJavaNewBaseline();
            
            testFrogspawnSingleThread();
            testStormpotSingleThread();
            testFastPoolSingleThread();

            testFrogspawnMultiThread();
            testStormpotMultiThread();
            testFastPoolMultiThread();

            System.out.println("\n" + "=".repeat(60));
            System.out.println("测试完成");
            System.out.println("=".repeat(60));
        } finally {
            tearDown();
        }
    }

    public static void setUp() {
        frogspawnPool = ObjectsMemoryPoolFactory.newPool(new DemoPojoCreator(), MAX_SIZE);

        Config<PooledSlotDemoPojo> config = new Config<PooledSlotDemoPojo>()
                .setAllocator(new DemoPojoSAllocator());
        config.setSize(MAX_SIZE);
        stormpotPool = new BlazePool<>(config);

        PoolConfig poolConfig = new PoolConfig();
        poolConfig.setMaxSize(MAX_SIZE);
        fastPool = new DisruptorObjectPool<>(poolConfig, new DemoPojoObjectFastPoolFactory());
    }

    public static void tearDown() throws Exception {
        if (frogspawnPool != null) {
            frogspawnPool = null;
        }
        if (stormpotPool != null) {
            stormpotPool.shutdown().await(new Timeout(5, TimeUnit.SECONDS));
        }
        if (fastPool != null) {
            fastPool.shutdown();
        }
    }

    public static void testFrogspawnSingleThread() {
        System.out.println("\n=== Frogspawn 单线程测试 ===");
        
        for (int i = 0; i < WARMUP_ROUNDS; i++) {
            DemoPojo pojo = frogspawnPool.fetch();
            frogspawnPool.release(pojo);
        }

        long start = System.nanoTime();
        for (int i = 0; i < TEST_ROUNDS; i++) {
            DemoPojo pojo = frogspawnPool.fetch();
            frogspawnPool.release(pojo);
        }
        long elapsed = System.nanoTime() - start;

        printResult("Frogspawn", TEST_ROUNDS, elapsed, 1);
    }

    public static void testStormpotSingleThread() throws Exception {
        System.out.println("\n=== Stormpot BlazePool 单线程测试 ===");
        Timeout timeout = new Timeout(1, TimeUnit.SECONDS);

        for (int i = 0; i < WARMUP_ROUNDS; i++) {
            PooledSlotDemoPojo pojo = stormpotPool.claim(timeout);
            if (pojo != null) {
                pojo.release();
            }
        }

        long start = System.nanoTime();
        for (int i = 0; i < TEST_ROUNDS; i++) {
            PooledSlotDemoPojo pojo = stormpotPool.claim(timeout);
            if (pojo != null) {
                pojo.release();
            }
        }
        long elapsed = System.nanoTime() - start;

        printResult("Stormpot BlazePool", TEST_ROUNDS, elapsed, 1);
    }

    public static void testFastPoolSingleThread() {
        System.out.println("\n=== FastPool+Disruptor 单线程测试 ===");

        for (int i = 0; i < WARMUP_ROUNDS; i++) {
            Poolable<DemoPojo> pojo = fastPool.borrowObject();
            fastPool.returnObject(pojo);
        }

        long start = System.nanoTime();
        for (int i = 0; i < TEST_ROUNDS; i++) {
            Poolable<DemoPojo> pojo = fastPool.borrowObject();
            fastPool.returnObject(pojo);
        }
        long elapsed = System.nanoTime() - start;

        printResult("FastPool+Disruptor", TEST_ROUNDS, elapsed, 1);
    }

    public static void testFrogspawnMultiThread() throws Exception {
        System.out.println("\n=== Frogspawn 多线程测试 (" + THREAD_COUNT + "线程) ===");
        runMultiThreadTest("Frogspawn", (threadId, counter) -> {
            for (int i = 0; i < TEST_ROUNDS / THREAD_COUNT; i++) {
                DemoPojo pojo = frogspawnPool.fetch();
                frogspawnPool.release(pojo);
                counter.incrementAndGet();
            }
        });
    }

    public static void testStormpotMultiThread() throws Exception {
        System.out.println("\n=== Stormpot BlazePool 多线程测试 (" + THREAD_COUNT + "线程) ===");
        Timeout timeout = new Timeout(1, TimeUnit.SECONDS);
        runMultiThreadTest("Stormpot BlazePool", (threadId, counter) -> {
            for (int i = 0; i < TEST_ROUNDS / THREAD_COUNT; i++) {
                try {
                    PooledSlotDemoPojo pojo = stormpotPool.claim(timeout);
                    if (pojo != null) {
                        pojo.release();
                    }
                    counter.incrementAndGet();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void testFastPoolMultiThread() throws Exception {
        System.out.println("\n=== FastPool+Disruptor 多线程测试 (" + THREAD_COUNT + "线程) ===");
        runMultiThreadTest("FastPool+Disruptor", (threadId, counter) -> {
            for (int i = 0; i < TEST_ROUNDS / THREAD_COUNT; i++) {
                Poolable<DemoPojo> pojo = fastPool.borrowObject();
                fastPool.returnObject(pojo);
                counter.incrementAndGet();
            }
        });
    }

    public static void testJavaNewBaseline() {
        System.out.println("\n=== Java new 基线测试 ===");
        
        for (int i = 0; i < WARMUP_ROUNDS; i++) {
            DemoPojo pojo = new DemoPojo();
            pojo.setVal1(i);
        }

        long start = System.nanoTime();
        for (int i = 0; i < TEST_ROUNDS; i++) {
            DemoPojo pojo = new DemoPojo();
            pojo.setVal1(i);
        }
        long elapsed = System.nanoTime() - start;

        printResult("Java new (基线)", TEST_ROUNDS, elapsed, 1);
    }

    private static void runMultiThreadTest(String name, ThreadTask task) throws Exception {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        long start = System.nanoTime();
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            executor.submit(() -> {
                try {
                    task.run(threadId, counter);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        long elapsed = System.nanoTime() - start;
        executor.shutdown();

        printResult(name, counter.get(), elapsed, THREAD_COUNT);
    }

    private static void printResult(String name, long operations, long elapsedNanos, int threads) {
        double elapsedMs = elapsedNanos / 1_000_000.0;
        double opsPerMs = operations / elapsedMs;
        double avgNanos = (double) elapsedNanos / operations;

        System.out.println("  操作数: " + String.format("%,d", operations));
        System.out.println("  耗时: " + String.format("%.2f ms", elapsedMs));
        System.out.println("  吞吐量: " + String.format("%,.0f ops/ms", opsPerMs));
        System.out.println("  平均耗时: " + String.format("%.2f ns/op", avgNanos));
        if (threads > 1) {
            System.out.println("  线程数: " + threads);
        }
    }

    @FunctionalInterface
    interface ThreadTask {
        void run(int threadId, AtomicLong counter);
    }
}