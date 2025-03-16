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

import java.util.concurrent.TimeUnit;


@State(Scope.Benchmark)
@Fork(value = 1, jvmArgsPrepend = {"-Xmx400m", "-Xms400m", "-XX:-RestrictContended"})
@Threads(value = 8)
@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Compare001Benchmark extends CompareBase {

    @Benchmark
    public void testCommonsPool(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = COMMONS_POOL.borrowObject();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            COMMONS_POOL.returnObject(pojo);
        }
    }

    @Benchmark
    public void testViburPool(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = VIBUR_POOL.tryTake(100, TimeUnit.MILLISECONDS);
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            VIBUR_POOL.restore(pojo);
        }
    }

    @Benchmark
    public void testLitePool(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = LITE_POOL.acquire();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            if (pojo != null) {
                LITE_POOL.release(pojo);
            }
        }
    }

    @Benchmark
    public void testBeeOP(Blackhole blackhole) {
        BeeObjectHandle handle = null;
        try {
            handle = BEEOP_POOL.getObject();
            blackhole.consume(handle.getObjectProxy());
        } catch (Exception e) {
            //
        } finally {
            if (handle != null) {
                try {
                    handle.close();
                } catch (Exception e) {
                    //
                }
            }
        }
    }

    @Benchmark
    public void testGOPool(Blackhole blackhole) {
        PoolableObject<DemoPojo> holder = null;
        try {
            holder = G_O_POOL.claim(1, TimeUnit.SECONDS); // null if timed out
            if (holder != null) {
                blackhole.consume(holder.getAllocatedObject());
            }
        } catch (Exception e) {
            //
        } finally {
            if (holder != null) {
                holder.release();
            }
        }
    }

    @Benchmark
    public void testFrogspawnPool(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = FS_POOL.fetch();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            FS_POOL.release(pojo);
        }
    }

    @Benchmark
    public void testNew(Blackhole blackhole) {
        blackhole.consume(new DemoPojo());
    }

}
