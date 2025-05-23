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
import io.github.hellyguo.poolcmp.misc.DemoPojoViburPoolObjectFactory;
import org.vibur.objectpool.ConcurrentPool;
import org.vibur.objectpool.PoolService;
import org.vibur.objectpool.util.ConcurrentLinkedQueueCollection;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static io.github.hellyguo.poolcmp.CompareConsts.INITIAL_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class ViburPool001 implements PoolImplementor {

    protected static final PoolService<DemoPojo> VIBUR_POOL =
            new ConcurrentPool<>(new ConcurrentLinkedQueueCollection<>(), new DemoPojoViburPoolObjectFactory(),
                                 INITIAL_SIZE, MAX_SIZE, false);

    private static final Consumer<DemoPojo> RELEASER = pojo -> {
        try {
            if (pojo != null) {
                VIBUR_POOL.restore(pojo);
            }
        } catch (Exception e) {
            //
        }
    };

    @Override
    public void testPool(PojoCustomer customer) {
        DemoPojo pojo = null;
        try {
            pojo = VIBUR_POOL.tryTake(100, TimeUnit.MILLISECONDS);
            customer.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            VIBUR_POOL.restore(pojo);
        }
    }

    @Override
    public void testPoolBatch(PojoCustomer customer, DemoPojo[] pojoArray, int batchSize) {
        try {
            for (int i = 0; i < batchSize; i++) {
                pojoArray[i] = VIBUR_POOL.tryTake(100, TimeUnit.MILLISECONDS);
            }
            customer.consume(pojoArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Arrays.stream(pojoArray).forEach(RELEASER);
        }
    }

    @Override
    public void shutdown() {
        VIBUR_POOL.close();
    }

}
