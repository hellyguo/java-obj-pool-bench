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

import cn.danielw.fop.ObjectPool;
import cn.danielw.fop.PoolConfig;
import cn.danielw.fop.Poolable;
import io.github.heillyguo.poolcmp.PoolImplementor;
import io.github.heillyguo.poolcmp.domain.DemoPojo;
import io.github.heillyguo.poolcmp.misc.DemoPojoObjectFastPoolFactory;
import org.openjdk.jmh.infra.Blackhole;

import static io.github.heillyguo.poolcmp.CompareConsts.MAX_SIZE;

public class FastPool001 implements PoolImplementor {

    protected static final ObjectPool<DemoPojo> FAST_POOL;

    static {
        PoolConfig config = new cn.danielw.fop.PoolConfig();
        config.setMaxSize(MAX_SIZE);
        FAST_POOL = new ObjectPool<>(config, new DemoPojoObjectFastPoolFactory());
    }

    @Override
    public void testPool(Blackhole blackhole) {
        Poolable<DemoPojo> pojo = null;
        try {
            pojo = FAST_POOL.borrowObject();
            blackhole.consume(pojo);
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

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
