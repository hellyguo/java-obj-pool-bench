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
import io.github.hellyguo.poolcmp.misc.DemoPojoObjectFactory;
import org.pacesys.kbop.IKeyedObjectPool;
import org.pacesys.kbop.IPooledObject;
import org.pacesys.kbop.Pools;

import java.util.Arrays;
import java.util.function.Consumer;

import static io.github.hellyguo.poolcmp.CompareConsts.ARRAY_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class KOPool001 implements PoolImplementor {

    protected static final IKeyedObjectPool.Multi<String, DemoPojo> KOP_POOL =
            Pools.createMultiPool(new DemoPojoObjectFactory(), MAX_SIZE);

    private static final Consumer<IPooledObject<DemoPojo>>
            RELEASER = pojo -> {
        try {
            if (pojo != null) {
                pojo.release();
                KOP_POOL.release(pojo);
            }
        } catch (Exception e) {
            //
        }
    };

    @SuppressWarnings("rawtypes")
    private static final ThreadLocal<IPooledObject[]> WRAP_LOCAL =
            ThreadLocal.withInitial(() -> new IPooledObject[ARRAY_SIZE]);

    @Override
    public void testPool(PojoCustomer customer) {
        IPooledObject<DemoPojo> pojo = null;
        try {
            pojo = KOP_POOL.borrow("example1");
            customer.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            if (pojo != null) {
                pojo.release();
                KOP_POOL.release(pojo);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void testPoolBatch(PojoCustomer customer, DemoPojo[] pojoArray, int batchSize) {
        IPooledObject<DemoPojo>[] array = WRAP_LOCAL.get();
        try {
            for (int i = 0; i < batchSize; i++) {
                array[i] = KOP_POOL.borrow("example1");
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
        KOP_POOL.shutdown();
    }

}
