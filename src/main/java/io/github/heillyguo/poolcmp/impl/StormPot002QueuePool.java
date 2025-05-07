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
package io.github.heillyguo.poolcmp.impl;

import io.github.heillyguo.poolcmp.PoolImplementor;
import io.github.heillyguo.poolcmp.misc.DemoPojoSAllocator;
import io.github.heillyguo.poolcmp.misc.PooledSlotDemoPojo;
import org.openjdk.jmh.infra.Blackhole;
import stormpot.Config;
import stormpot.QueuePool;
import stormpot.Timeout;

import java.util.concurrent.TimeUnit;

import static io.github.heillyguo.poolcmp.CompareConsts.MAX_SIZE;

public class StormPot002QueuePool implements PoolImplementor {

    protected static final QueuePool<PooledSlotDemoPojo> S_QUEUE_POOL;

    private static final Timeout STORM_POT_TIMEOUT = new Timeout(2, TimeUnit.SECONDS);

    static {
        Config<PooledSlotDemoPojo> config = new Config<PooledSlotDemoPojo>().setAllocator(new DemoPojoSAllocator());
        config.setSize(MAX_SIZE);
        S_QUEUE_POOL = new QueuePool<>(config);
    }

    @Override
    public void testPool(Blackhole blackhole) {
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

    @Override
    public void shutdown() {
        try {
            S_QUEUE_POOL.shutdown().await(new Timeout(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
