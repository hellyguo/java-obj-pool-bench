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

import cn.nextop.lite.pool.Pool;
import cn.nextop.lite.pool.PoolBuilder;
import io.github.hellyguo.poolcmp.PojoCustomer;
import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;

import java.util.concurrent.TimeUnit;

import static io.github.hellyguo.poolcmp.CompareConsts.INITIAL_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class LitePool001 implements PoolImplementor {

    protected static final Pool<DemoPojo> LITE_POOL;

    static {
        PoolBuilder<DemoPojo> builder = new PoolBuilder<>();
        LITE_POOL = builder.local(true)
                           .supplier(DemoPojo::new)
                           .consumer(null)
                           .interval(15000)
                           .minimum(INITIAL_SIZE)
                           .maximum(MAX_SIZE)
                           .timeout(5000)
                           .tenancy(30000)
                           .ttl(MAX_SIZE)
                           .tti(MAX_SIZE)
                           .verbose(false)
                           .build("object pool");
    }

    @Override
    public void testPool(PojoCustomer customer) {
        DemoPojo pojo = null;
        try {
            pojo = LITE_POOL.acquire();
            customer.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            if (pojo != null) {
                LITE_POOL.release(pojo);
            }
        }
    }

    @Override
    public void shutdown() {
        LITE_POOL.stop(10, TimeUnit.SECONDS);
    }

}
