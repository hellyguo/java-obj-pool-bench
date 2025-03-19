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
package io.github.heillyguo.poolcmp;

import cn.beeop.BeeObjectHandle;
import org.bbottema.genericobjectpool.PoolableObject;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


@State(Scope.Benchmark)
@Fork(value = 1, jvmArgsPrepend = {"-Xmx400m", "-Xms400m", "-XX:-RestrictContended"})
@Threads(value = 8)
@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Compare002Benchmark extends CompareBase {

    private static final ThreadLocal<DemoPojo[]> ARRAY_LOCAL
            = ThreadLocal.withInitial(() -> new DemoPojo[ARRAY_SIZE]);

    @Benchmark
    public void test0NewOneRequestMultiTimes(Blackhole blackhole) {
        DemoPojo[] pojoArray = getPojo();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            pojoArray[i] = new DemoPojo();
        }
        blackhole.consume(pojoArray);
    }

    @Benchmark
    public void test1BeeOPOneRequestMultiTimes(Blackhole blackhole) {
        DemoPojo[] pojoArray = getPojo();
        BeeObjectHandle[] handles = new BeeObjectHandle[ARRAY_SIZE];
        try {
            for (int i = 0; i < ARRAY_SIZE; i++) {
                handles[i] = BEEOP_POOL.getObject();
                pojoArray[i] = (DemoPojo) handles[i].getObjectProxy();
            }
            blackhole.consume(pojoArray);
        } catch (Exception e) {
            //
        } finally {
            Arrays.stream(handles).forEach(handle -> {
                try {
                    if (handle != null) {
                        handle.close();
                    }
                } catch (Exception e) {
                    //
                }
            });
        }
    }

    @Benchmark
    public void test2CommonsPoolOneRequestMultiTimes(Blackhole blackhole) {
        DemoPojo[] pojoArray = getPojo();
        try {
            for (int i = 0; i < ARRAY_SIZE; i++) {
                pojoArray[i] = COMMONS_POOL.borrowObject();
            }
            blackhole.consume(pojoArray);
        } catch (Exception e) {
            //
        } finally {
            Arrays.stream(pojoArray).forEach(COMMONS_POOL::returnObject);
        }
    }

    @Benchmark
    public void test3FrogspawnPoolOneRequestMultiTimes(Blackhole blackhole) {
        DemoPojo[] pojoArray = getPojo();
        try {
            for (int i = 0; i < ARRAY_SIZE; i++) {
                pojoArray[i] = FS_POOL.fetch();
            }
            blackhole.consume(pojoArray);
        } catch (Exception e) {
            //
        } finally {
            Arrays.stream(pojoArray).forEach(FS_POOL::release);
        }
    }

    @SuppressWarnings("unchecked")
    @Benchmark
    public void test4GOPoolOneRequestMultiTimes(Blackhole blackhole) {
        DemoPojo[] pojoArray = getPojo();
        PoolableObject<DemoPojo>[] holders = new PoolableObject[ARRAY_SIZE];
        try {
            PoolableObject<DemoPojo> holder;
            for (int i = 0; i < ARRAY_SIZE; i++) {
                holder = G_O_POOL.claim(1, TimeUnit.SECONDS); // null if timed out
                if (holder != null) {
                    holders[i] = holder;
                    pojoArray[i] = holder.getAllocatedObject();
                }
            }
            blackhole.consume(pojoArray);
        } catch (Exception e) {
            //
        } finally {
            Arrays.stream(holders).forEach(holder -> {
                if (holder != null) {
                    holder.release();
                }
            });
        }
    }

    @Benchmark
    public void test5LitePoolOneRequestMultiTimes(Blackhole blackhole) {
        DemoPojo[] pojoArray = getPojo();
        try {
            for (int i = 0; i < ARRAY_SIZE; i++) {
                pojoArray[i] = LITE_POOL.acquire();
            }
            blackhole.consume(pojoArray);
        } catch (Exception e) {
            //
        } finally {
            Arrays.stream(pojoArray).forEach(pojo -> {
                if (pojo != null) {
                    LITE_POOL.release(pojo);
                }
            });
        }
    }

    @Benchmark
    public void test6ViburPoolOneRequestMultiTimes(Blackhole blackhole) {
        DemoPojo[] pojoArray = getPojo();
        try {
            for (int i = 0; i < ARRAY_SIZE; i++) {
                pojoArray[i] = VIBUR_POOL.tryTake(100, TimeUnit.MILLISECONDS);
            }
            blackhole.consume(pojoArray);
        } catch (Exception e) {
            //
        } finally {
            Arrays.stream(pojoArray).forEach(VIBUR_POOL::restore);
        }
    }

    private DemoPojo[] getPojo() {
        return ARRAY_LOCAL.get();
    }

}
