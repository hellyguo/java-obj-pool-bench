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

import cn.beeop.BeeObjectHandle;
import cn.beeop.BeeObjectSource;
import cn.beeop.BeeObjectSourceConfig;
import io.github.hellyguo.poolcmp.PojoCustomer;
import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;
import io.github.hellyguo.poolcmp.misc.DemoPojoBeeObjectFactory;
import io.github.hellyguo.poolcmp.misc.ValPojo;

import java.util.Arrays;
import java.util.function.Consumer;

import static io.github.hellyguo.poolcmp.CompareConsts.ARRAY_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.INITIAL_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class BeeOp002ObjectSource implements PoolImplementor {

    protected static final BeeObjectSource BEEOP_POOL;

    static {
        BeeObjectSourceConfig config = new BeeObjectSourceConfig();
        config.setObjectInterfaces(new Class[]{ValPojo.class});
        config.setMaxActive(MAX_SIZE);
        config.setMaxWait(INITIAL_SIZE);
        config.setObjectFactoryClass(DemoPojoBeeObjectFactory.class);
        BEEOP_POOL = new BeeObjectSource(config);
    }

    private static final Consumer<BeeObjectHandle> RELEASER = handle -> {
        try {
            if (handle != null) {
                handle.close();
            }
        } catch (Exception e) {
            //
        }
    };

    private static final ThreadLocal<BeeObjectHandle[]> HANDLE_ARRAY_LOCAL =
            ThreadLocal.withInitial(() -> new BeeObjectHandle[ARRAY_SIZE]);

    @Override
    public void testPool(PojoCustomer customer) {
        BeeObjectHandle handle = null;
        try {
            handle = BEEOP_POOL.getObject();
            customer.consume(handle.getObjectProxy());
        } catch (Exception e) {
            //
        } finally {
            if (handle != null) {
                try {
                    handle.close();
                } catch (Exception e) {
                    //
                }
            }
        }
    }

    @Override
    public void testPoolBatch(PojoCustomer customer, DemoPojo[] pojoArray, int batchSize) {
        BeeObjectHandle[] handles = HANDLE_ARRAY_LOCAL.get();
        try {
            for (int i = 0; i < batchSize; i++) {
                handles[i] = BEEOP_POOL.getObject();
                pojoArray[i] = (DemoPojo) handles[i].getObjectProxy();
            }
            customer.consume(pojoArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Arrays.stream(handles).forEach(RELEASER);
        }
    }

    @Override
    public void shutdown() {
        BEEOP_POOL.close();
    }

}
