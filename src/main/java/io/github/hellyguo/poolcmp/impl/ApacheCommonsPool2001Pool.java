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

import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;
import io.github.hellyguo.poolcmp.misc.DemoPojoCommonsPooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openjdk.jmh.infra.Blackhole;

import static io.github.hellyguo.poolcmp.CompareConsts.INITIAL_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class ApacheCommonsPool2001Pool implements PoolImplementor {

    protected static final GenericObjectPool<DemoPojo> COMMONS_2_POOL;

    static {
        try {
            GenericObjectPoolConfig<DemoPojo> config = new GenericObjectPoolConfig<>();
            config.setMaxTotal(MAX_SIZE);
            config.setMaxIdle(MAX_SIZE);
            config.setMinIdle(INITIAL_SIZE);
            COMMONS_2_POOL = new GenericObjectPool<>(new DemoPojoCommonsPooledObjectFactory(), config);
            COMMONS_2_POOL.preparePool();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void testPool(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = COMMONS_2_POOL.borrowObject();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            try {
                COMMONS_2_POOL.returnObject(pojo);
            } catch (Exception e) {
                //
            }
        }
    }

    @Override
    public void shutdown() {
        try {
            COMMONS_2_POOL.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
