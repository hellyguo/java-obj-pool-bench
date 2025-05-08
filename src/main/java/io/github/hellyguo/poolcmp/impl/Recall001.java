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

import com.aitusoftware.recall.store.BufferStore;
import com.aitusoftware.recall.store.ByteBufferOps;
import com.aitusoftware.recall.store.SingleTypeStore;
import io.github.hellyguo.poolcmp.PojoCustomer;
import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;
import io.github.hellyguo.poolcmp.misc.DemoPojoDecoder;
import io.github.hellyguo.poolcmp.misc.DemoPojoEncoder;
import io.github.hellyguo.poolcmp.misc.DemoPojoIdAccessor;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

import static io.github.hellyguo.poolcmp.CompareConsts.ARRAY_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.INITIAL_SIZE;

public class Recall001 implements PoolImplementor {

    protected static final SingleTypeStore<ByteBuffer, DemoPojo> POJO_STORE;

    static {
        BufferStore<ByteBuffer> store =
                new BufferStore<>(64, INITIAL_SIZE, ByteBuffer::allocateDirect, new ByteBufferOps());
        POJO_STORE =
                new SingleTypeStore<>(store, new DemoPojoDecoder(), new DemoPojoEncoder(), new DemoPojoIdAccessor());
    }

    private static final AtomicLong WALKER = new AtomicLong(0);

    static {
        for (int i = 0; i < INITIAL_SIZE; i++) {
            DemoPojo pojo = new DemoPojo();
            pojo.setVal1(i);
            pojo.setVal2(i);
            pojo.setVal3(i);
            pojo.setVal4(Integer.toString(i));
            POJO_STORE.store(pojo);
        }
    }

    private static final ThreadLocal<DemoPojo> POJO_LOCAL = ThreadLocal.withInitial(DemoPojo::new);
    private static final ThreadLocal<DemoPojo[]> ARRAY_LOCAL = ThreadLocal.withInitial(() -> {
        DemoPojo[] array = new DemoPojo[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = new DemoPojo();
        }
        return array;
    });

    @Override
    public void testPool(PojoCustomer customer) {
        DemoPojo pojo = POJO_LOCAL.get();
        try {
            POJO_STORE.load(WALKER.getAndIncrement() % INITIAL_SIZE, pojo);
            customer.consume(pojo);
        } catch (Exception e) {
            //
        }
    }

    @Override
    public void testPoolBatch(PojoCustomer customer, DemoPojo[] pojoArray, int batchSize) {
        DemoPojo[] array = ARRAY_LOCAL.get();
        try {
            for (int i = 0; i < batchSize; i++) {
                POJO_STORE.load(WALKER.getAndIncrement() % INITIAL_SIZE, array[i]);
                pojoArray[i] = array[i];
            }
            customer.consume(pojoArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void shutdown() {
        POJO_STORE.clear();
    }

}
