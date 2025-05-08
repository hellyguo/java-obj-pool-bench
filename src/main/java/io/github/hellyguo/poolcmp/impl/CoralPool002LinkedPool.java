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

import com.coralblocks.coralpool.LinkedObjectPool;
import com.coralblocks.coralpool.ObjectPool;
import io.github.hellyguo.poolcmp.PojoCustomer;
import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;

import static io.github.hellyguo.poolcmp.CompareConsts.INITIAL_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.PRELOAD_COUNT;

public class CoralPool002LinkedPool implements PoolImplementor {

    protected static final ObjectPool<DemoPojo> CORAL_LINKED_POOL;

    static {
        final Class<DemoPojo> klass = DemoPojo.class;
        CORAL_LINKED_POOL = new LinkedObjectPool<>(INITIAL_SIZE, PRELOAD_COUNT, klass);
    }

    @Override
    public void testPool(PojoCustomer customer) {
        DemoPojo pojo = null;
        try {
            pojo = CORAL_LINKED_POOL.get();
            customer.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            try {
                CORAL_LINKED_POOL.release(pojo);
            } catch (Exception e) {
                //
            }
        }
    }

    @Override
    public void shutdown() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
