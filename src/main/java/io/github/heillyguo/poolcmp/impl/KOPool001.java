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
import io.github.heillyguo.poolcmp.misc.DemoPojoObjectFactory;
import org.openjdk.jmh.infra.Blackhole;
import org.pacesys.kbop.IKeyedObjectPool;
import org.pacesys.kbop.IPooledObject;
import org.pacesys.kbop.Pools;

import static io.github.heillyguo.poolcmp.CompareConsts.MAX_SIZE;

public class KOPool001 implements PoolImplementor {

    protected static final IKeyedObjectPool.Multi<String, DemoPojo> KOP_POOL =
            Pools.createMultiPool(new DemoPojoObjectFactory(), MAX_SIZE);

    @Override
    public void testPool(Blackhole blackhole) {
        IPooledObject<DemoPojo> pojo = null;
        try {
            pojo = KOP_POOL.borrow("example1");
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            if (pojo != null) {
                pojo.release();
                KOP_POOL.release(pojo);
            }
        }
    }

    @Override
    public void shutdown() {
        KOP_POOL.shutdown();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
