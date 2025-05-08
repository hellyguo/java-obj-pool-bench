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
import io.github.hellyguo.poolcmp.domain.DemoPojo;
import io.github.hellyguo.poolcmp.misc.DemoPojoSAllocator;
import io.github.hellyguo.poolcmp.misc.PooledSlotDemoPojo;
import stormpot.Config;
import stormpot.QueuePool;
import stormpot.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static io.github.hellyguo.poolcmp.CompareConsts.ARRAY_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class StormPot002QueuePool implements PoolImplementor {

    protected static final QueuePool<PooledSlotDemoPojo> S_QUEUE_POOL;

    private static final Timeout STORM_POT_TIMEOUT = new Timeout(2, TimeUnit.SECONDS);

    static {
        Config<PooledSlotDemoPojo> config = new Config<PooledSlotDemoPojo>().setAllocator(new DemoPojoSAllocator());
        config.setSize(MAX_SIZE);
        S_QUEUE_POOL = new QueuePool<>(config);
    }

    private static final Consumer<PooledSlotDemoPojo> RELEASER = pojo -> {
        try {
            if (pojo != null) {
                pojo.release();
            }
        } catch (Exception e) {
            //
        }
    };

    private static final ThreadLocal<PooledSlotDemoPojo[]> WRAP_LOCAL =
            ThreadLocal.withInitial(() -> new PooledSlotDemoPojo[ARRAY_SIZE]);

    @Override
    public void testPool(PojoCustomer customer) {
        PooledSlotDemoPojo pojo = null;
        try {
            pojo = S_QUEUE_POOL.claim(STORM_POT_TIMEOUT);
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
    public void testPoolBatch(PojoCustomer customer, DemoPojo[] pojoArray, int batchSize) {
        PooledSlotDemoPojo[] array = WRAP_LOCAL.get();
        try {
            for (int i = 0; i < batchSize; i++) {
                array[i] = S_QUEUE_POOL.claim(STORM_POT_TIMEOUT);
            }
            customer.consume(array);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Arrays.stream(array).forEach(RELEASER);
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

}
