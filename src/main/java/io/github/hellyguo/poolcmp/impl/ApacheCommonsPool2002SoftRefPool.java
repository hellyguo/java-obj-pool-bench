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
import io.github.hellyguo.poolcmp.misc.DemoPojo2BasePoolableObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.commons.pool2.impl.SoftReferenceObjectPool;

import static io.github.hellyguo.poolcmp.CompareConsts.INITIAL_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class ApacheCommonsPool2002SoftRefPool implements PoolImplementor {

    protected static final SoftReferenceObjectPool<DemoPojo> COMMONS_2_POOL_SOFT_REF;

    static {
        try {
            GenericObjectPoolConfig<DemoPojo> config = new GenericObjectPoolConfig<>();
            config.setMaxTotal(MAX_SIZE);
            config.setMaxIdle(MAX_SIZE);
            config.setMinIdle(INITIAL_SIZE);
            COMMONS_2_POOL_SOFT_REF = new SoftReferenceObjectPool<>(new DemoPojo2BasePoolableObjectFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void testPool(PojoCustomer customer) {
        DemoPojo pojo = null;
        try {
            pojo = COMMONS_2_POOL_SOFT_REF.borrowObject();
            customer.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            try {
                COMMONS_2_POOL_SOFT_REF.returnObject(pojo);
            } catch (Exception e) {
                //
            }
        }
    }

    @Override
    public void shutdown() {
        try {
            COMMONS_2_POOL_SOFT_REF.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
