/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.hellyguo.poolcmp.impl;

import be.yvanmazy.nomoreinstance.Pool;
import be.yvanmazy.nomoreinstance.PoolConcurrency;
import be.yvanmazy.nomoreinstance.SweepCleanablePool;
import io.github.hellyguo.poolcmp.PojoCustomer;
import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;

import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

/**
 * NoMoreInstance SweepCleanablePool: scope/sweep model.
 *
 * <p>The pool has no per-object release: {@code get()} advances a cursor and
 * {@code cleanAll()} resets it. Use a per-thread pool so that {@code cleanAll()}
 * never resets a cursor other threads are using.</p>
 */
public class NoMoreInstance001SweepClean implements PoolImplementor {

    private static final ThreadLocal<SweepCleanablePool<DemoPojo>> NMI_POOL =
            ThreadLocal.withInitial(() -> Pool.<DemoPojo>newBuilder()
                    .supplier(DemoPojo::new)
                    .cleaner(DemoPojo::resetThis)
                    .concurrency(PoolConcurrency.NOT_CONCURRENT)
                    .buildSweep(DemoPojo.class, MAX_SIZE));

    @Override
    public void testPool(PojoCustomer customer) {
        SweepCleanablePool<DemoPojo> pool = NMI_POOL.get();
        try {
            customer.consume(pool.get());
        } catch (Exception e) {
            //
        } finally {
            pool.cleanAll();
        }
    }

    @Override
    public void testPoolBatch(PojoCustomer customer, DemoPojo[] pojoArray, int batchSize) {
        SweepCleanablePool<DemoPojo> pool = NMI_POOL.get();
        try {
            for (int i = 0; i < batchSize; i++) {
                pojoArray[i] = pool.get();
            }
            customer.consume(pojoArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            pool.cleanAll();
        }
    }

    @Override
    public void shutdown() {
        // Per-thread scope pools are reclaimed with their threads.
    }

}
