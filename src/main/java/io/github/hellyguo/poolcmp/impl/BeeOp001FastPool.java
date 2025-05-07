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
import cn.beeop.BeeObjectSourceConfig;
import cn.beeop.pool.FastObjectPool;
import cn.beeop.pool.ObjectPool;
import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.misc.DemoPojoBeeObjectFactory;
import org.openjdk.jmh.infra.Blackhole;

import static io.github.hellyguo.poolcmp.CompareConsts.INITIAL_SIZE;
import static io.github.hellyguo.poolcmp.CompareConsts.MAX_SIZE;

public class BeeOp001FastPool implements PoolImplementor {

    protected static final ObjectPool BEEOP_FAST_POOL;

    static {
        try {
            BeeObjectSourceConfig config = new BeeObjectSourceConfig();
            config.setObjectFactoryClass(DemoPojoBeeObjectFactory.class);
            config.setInitialSize(INITIAL_SIZE);
            config.setMaxActive(MAX_SIZE);
            BEEOP_FAST_POOL = new FastObjectPool();
            BEEOP_FAST_POOL.init(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void testPool(Blackhole blackhole) {
        BeeObjectHandle handle = null;
        try {
            handle = BEEOP_FAST_POOL.getObject();
            blackhole.consume(handle.getObjectProxy());
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
    public void shutdown() {
        BEEOP_FAST_POOL.close();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
