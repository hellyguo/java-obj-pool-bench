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

import be.yvanmazy.nomoreinstance.Cleanable;
import be.yvanmazy.nomoreinstance.CleanablePool;
import be.yvanmazy.nomoreinstance.Pool;
import be.yvanmazy.nomoreinstance.PoolConcurrency;
import io.github.hellyguo.poolcmp.PojoCustomer;
import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;

import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class NoMoreInstance002Clean implements PoolImplementor {

    protected static final CleanablePool<DemoPojo> NMI_POOL =
            Pool.<DemoPojo>newBuilder()
                // Optional object creation supplier
                .supplier(DemoPojo::new)
                // Optional cleaner for after use
                .cleaner(DemoPojo::resetThis)
                // Pool concurrency level
                .concurrency(PoolConcurrency.LOCK_FREE)
                // Build a SweepCleanablePool
                .build(DemoPojo.class, MAX_SIZE);

    @Override
    public void testPool(PojoCustomer customer) {
        try (final Cleanable<DemoPojo> cleanable = NMI_POOL.get()) {
            customer.consume(cleanable.value());
        } catch (Exception e) {
            //
        }
    }

    @Override
    public void testPoolBatch(PojoCustomer customer, DemoPojo[] pojoArray, int batchSize) {
        try {
            for (int i = 0; i < batchSize; i++) {
                pojoArray[i] = NMI_POOL.get().value();
            }
            customer.consume(pojoArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void shutdown() {
        NMI_POOL.cleanAll();
    }

}
