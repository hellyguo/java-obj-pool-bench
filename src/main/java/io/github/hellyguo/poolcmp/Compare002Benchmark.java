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

import io.github.hellyguo.poolcmp.domain.DemoPojo;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static io.github.hellyguo.poolcmp.CompareConsts.ARRAY_SIZE;


@State(Scope.Benchmark)
@Fork(value = 1, jvmArgsPrepend = {"-Xmx400m", "-Xms400m", "-XX:-RestrictContended"})
@Threads(value = 8)
@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Compare002Benchmark {

    private static final ThreadLocal<DemoPojo[]> ARRAY_LOCAL
            = ThreadLocal.withInitial(() -> new DemoPojo[ARRAY_SIZE]);

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Compare002Benchmark.class.getSimpleName())
                .addProfiler(GCProfiler.class)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public void testBatch(PoolImplParam param, Blackhole blackhole) {
        DemoPojo[] pojoArray = getPojo();
        param.desc.getImplementor().testPoolBatch(blackhole::consume, pojoArray, ARRAY_SIZE);
    }

    @TearDown
    public void tearDown() {
        for (PoolImplDesc desc : PoolImplDesc.values()) {
            desc.getImplementor().shutdown();
        }
    }

    private DemoPojo[] getPojo() {
        return ARRAY_LOCAL.get();
    }

}
