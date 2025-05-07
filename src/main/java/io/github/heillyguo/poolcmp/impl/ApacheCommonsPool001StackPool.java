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
package io.github.heillyguo.poolcmp.impl;

import io.github.heillyguo.poolcmp.PoolImplementor;
import io.github.heillyguo.poolcmp.domain.DemoPojo;
import io.github.heillyguo.poolcmp.misc.DemoPojoBasePoolableObjectFactory;
import org.apache.commons.pool.impl.StackObjectPool;
import org.openjdk.jmh.infra.Blackhole;

import static io.github.heillyguo.poolcmp.CompareConsts.INITIAL_SIZE;
import static io.github.heillyguo.poolcmp.CompareConsts.MAX_SIZE;

public class ApacheCommonsPool001StackPool implements PoolImplementor {

    protected static final StackObjectPool<DemoPojo> COMMONS_1_POOL_STACK;

    static {
        COMMONS_1_POOL_STACK = new StackObjectPool<>(new DemoPojoBasePoolableObjectFactory(), INITIAL_SIZE, MAX_SIZE);
    }

    @Override
    public void testPool(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = COMMONS_1_POOL_STACK.borrowObject();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            try {
                COMMONS_1_POOL_STACK.returnObject(pojo);
            } catch (Exception e) {
                //
            }
        }
    }

    @Override
    public void shutdown() {
        try {
            COMMONS_1_POOL_STACK.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
