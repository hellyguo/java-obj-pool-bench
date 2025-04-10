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
import org.pacesys.kbop.IPooledObject;
import stormpot.Timeout;

import java.util.concurrent.TimeUnit;


@State(Scope.Benchmark)
@Fork(value = 1, jvmArgsPrepend = {"-Xmx4G", "-Xms4G", "-XX:-RestrictContended"})
@Threads(value = 8)
@Warmup(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class Compare001Benchmark extends CompareBase {

    private static final Timeout STORM_POT_TIMEOUT = new Timeout(2, TimeUnit.SECONDS);

    @Benchmark
    public void test00New(Blackhole blackhole) {
        blackhole.consume(new DemoPojo());
    }

    @Benchmark
    public void test01BeeOP(Blackhole blackhole) {
        BeeObjectHandle handle = null;
        try {
            handle = BEEOP_FAST_POOL.getObject();
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
    public void test02BeeOP(Blackhole blackhole) {
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
    public void test03CommonsPool(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = COMMONS_1_POOL_STACK.borrowObject();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            try {
                COMMONS_1_POOL_STACK.returnObject(pojo);
            } catch (Exception e) {
                //
            }
        }
    }

    @Benchmark
    public void test04CommonsPool(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = COMMONS_1_POOL.borrowObject();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            try {
                COMMONS_1_POOL.returnObject(pojo);
            } catch (Exception e) {
                //
            }
        }
    }

    @Benchmark
    public void test05CommonsPool(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = COMMONS_1_POOL_SOFT_REF.borrowObject();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            try {
                COMMONS_1_POOL_SOFT_REF.returnObject(pojo);
            } catch (Exception e) {
                //
            }
        }
    }

    @Benchmark
    public void test06CommonsPool2(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = COMMONS_2_POOL.borrowObject();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            COMMONS_2_POOL.returnObject(pojo);
        }
    }

    @Benchmark
    public void test07CommonsPool2(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = COMMONS_2_POOL_SOFT_REF.borrowObject();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            try {
                COMMONS_2_POOL_SOFT_REF.returnObject(pojo);
            } catch (Exception e) {
                //
            }
        }
    }

    @Benchmark
    public void test08ERASOFTPool(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = ERASOFT_POOL.getObj();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            ERASOFT_POOL.returnObj(pojo);
        }
    }

    @Benchmark
    public void test09FrogspawnPool(Blackhole blackhole) {
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
    public void test10GOPool(Blackhole blackhole) {
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
    public void test11KBPool(Blackhole blackhole) {
        IPooledObject<DemoPojo> pojo = null;
        try {
            pojo = KOP_POOL.borrow("example1");
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            if (pojo != null) {
                pojo.release();
                KOP_POOL.release(pojo);
            }
        }
    }

    @Benchmark
    public void test12LitePool(Blackhole blackhole) {
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
    public void test13StormPotBlazePool(Blackhole blackhole) {
        PooledSlotDemoPojo pojo = null;
        try {
            pojo = S_BLAZE_POOL.claim(STORM_POT_TIMEOUT);
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            if (pojo != null) {
                pojo.release();
            }
        }
    }

    @Benchmark
    public void test14StormPotQueuePool(Blackhole blackhole) {
        PooledSlotDemoPojo pojo = null;
        try {
            pojo = S_QUEUE_POOL.claim(STORM_POT_TIMEOUT);
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            if (pojo != null) {
                pojo.release();
            }
        }
    }

    @Benchmark
    public void test15ViburPool(Blackhole blackhole) {
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

}
