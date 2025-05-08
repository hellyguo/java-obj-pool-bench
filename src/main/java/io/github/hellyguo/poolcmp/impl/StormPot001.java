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
package io.github.hellyguo.poolcmp.impl;

import io.github.hellyguo.poolcmp.PojoCustomer;
import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.misc.DemoPojoSAllocator;
import io.github.hellyguo.poolcmp.misc.PooledSlotDemoPojo;
import stormpot.Pool;
import stormpot.Timeout;

import java.util.concurrent.TimeUnit;

public class StormPot001 implements PoolImplementor {

    protected static final Pool<PooledSlotDemoPojo> SP_POOL;

    private static final Timeout STORM_POT_TIMEOUT = new Timeout(2, TimeUnit.SECONDS);

    static {
        DemoPojoSAllocator allocator = new DemoPojoSAllocator();
        SP_POOL = Pool.from(allocator).build();
    }

    @Override
    public void testPool(PojoCustomer customer) {
        PooledSlotDemoPojo pojo = null;
        try {
            pojo = SP_POOL.claim(STORM_POT_TIMEOUT);
            customer.consume(pojo);
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
            SP_POOL.shutdown().await(new Timeout(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
