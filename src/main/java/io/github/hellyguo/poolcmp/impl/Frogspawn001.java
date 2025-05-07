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

import cn.itcraft.frogspawn.ObjectsMemoryPool;
import cn.itcraft.frogspawn.ObjectsMemoryPoolFactory;
import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;
import io.github.hellyguo.poolcmp.misc.DemoPojoCreator;
import org.openjdk.jmh.infra.Blackhole;

import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class Frogspawn001 implements PoolImplementor {

    protected static final ObjectsMemoryPool<DemoPojo> FS_POOL =
            ObjectsMemoryPoolFactory.newPool(new DemoPojoCreator(), MAX_SIZE);

    @Override
    public void testPool(Blackhole blackhole) {
        DemoPojo pojo = null;
        try {
            pojo = FS_POOL.fetch();
            blackhole.consume(pojo);
        } catch (Exception e) {
            //
        } finally {
            FS_POOL.release(pojo);
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
