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

import cn.danielw.fop.DisruptorObjectPool;
import cn.danielw.fop.PoolConfig;
import cn.danielw.fop.Poolable;
import io.github.hellyguo.poolcmp.PojoCustomer;
import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;
import io.github.hellyguo.poolcmp.misc.DemoPojoObjectFastPoolFactory;

import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class FastPool002Disruptor implements PoolImplementor {

    protected static final DisruptorObjectPool<DemoPojo> FAST_POOL;

    static {
        PoolConfig config = new PoolConfig();
        config.setMaxSize(MAX_SIZE);
        FAST_POOL = new DisruptorObjectPool<>(config, new DemoPojoObjectFastPoolFactory());
    }

    @Override
    public void testPool(PojoCustomer customer) {
        Poolable<DemoPojo> pojo = null;
        try {
            pojo = FAST_POOL.borrowObject();
            customer.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            FAST_POOL.returnObject(pojo);
        }
    }

    @Override
    public void shutdown() {
        try {
            FAST_POOL.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
