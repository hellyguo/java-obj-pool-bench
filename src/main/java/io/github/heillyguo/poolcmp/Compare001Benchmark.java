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

import java.util.concurrent.TimeUnit;


@State(Scope.Benchmark)
@Fork(value = 1, jvmArgsPrepend = {"-Xmx4G", "-Xms4G", "-XX:-RestrictContended"})
@Threads(value = 8)
@Warmup(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class Compare001Benchmark {

    @Benchmark
    public void testPoolGetAndRelease(PoolImplParam param, Blackhole blackhole) {
        param.desc.getImplementor().testPool(blackhole);
    }

    @TearDown
    public void tearDown() {
        for (PoolImplDesc desc : PoolImplDesc.values()) {
            desc.getImplementor().shutdown();
        }
    }
}
