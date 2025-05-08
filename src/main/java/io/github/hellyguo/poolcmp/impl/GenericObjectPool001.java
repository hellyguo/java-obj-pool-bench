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
import io.github.hellyguo.poolcmp.misc.DemoPojoAllocator;
import org.bbottema.genericobjectpool.GenericObjectPool;
import org.bbottema.genericobjectpool.PoolConfig;
import org.bbottema.genericobjectpool.PoolableObject;
import org.bbottema.genericobjectpool.expirypolicies.TimeoutSinceLastAllocationExpirationPolicy;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static io.github.hellyguo.poolcmp.CompareConsts.INITIAL_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class GenericObjectPool001 implements PoolImplementor {

    protected static final GenericObjectPool<DemoPojo> G_O_POOL;

    static {
        // deallocate after 1 hour, but every time an object is claimed the expiry timeout is reset
        TimeoutSinceLastAllocationExpirationPolicy<DemoPojo> expirationPolicy =
                new TimeoutSinceLastAllocationExpirationPolicy<>(1, TimeUnit.HOURS);
        PoolConfig<DemoPojo> poolConfig =
                PoolConfig.<DemoPojo>builder()
                          // keeps 2000 objects eagerly allocated at all times
                          .corePoolsize(INITIAL_SIZE)
                          .maxPoolsize(MAX_SIZE)
                          // deallocate after 1 hour, but every time an object is claimed the expiry timeout is reset
                          .expirationPolicy(expirationPolicy)
                          .build();
        G_O_POOL = new GenericObjectPool<>(poolConfig, new DemoPojoAllocator());
    }

    @Override
    public void testPool(PojoCustomer customer) {
        PoolableObject<DemoPojo> holder = null;
        try {
            // null if timed out
            holder = G_O_POOL.claim(1, TimeUnit.SECONDS);
            if (holder != null) {
                customer.consume(holder.getAllocatedObject());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (holder != null) {
                holder.release();
            }
        }
    }

    @Override
    public void shutdown() {
        try {
            Future<?> shutdownSequence = G_O_POOL.shutdown();
            // wait for shutdown to complete
            shutdownSequence.get();
            // until timeout
            shutdownSequence.get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
