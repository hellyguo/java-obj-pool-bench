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
package io.github.heillyguo.poolcmp;

import cn.beeop.BeeObjectSource;
import cn.beeop.BeeObjectSourceConfig;
import cn.itcraft.frogspawn.ObjectsMemoryPool;
import cn.itcraft.frogspawn.ObjectsMemoryPoolFactory;
import cn.itcraft.frogspawn.strategy.FetchFailStrategy;
import cn.itcraft.frogspawn.strategy.FetchStrategy;
import cn.itcraft.frogspawn.strategy.PoolStrategy;
import cn.nextop.lite.pool.Pool;
import cn.nextop.lite.pool.PoolBuilder;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.bbottema.genericobjectpool.PoolConfig;
import org.bbottema.genericobjectpool.expirypolicies.TimeoutSinceLastAllocationExpirationPolicy;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vibur.objectpool.ConcurrentPool;
import org.vibur.objectpool.PoolService;
import org.vibur.objectpool.util.ConcurrentLinkedQueueCollection;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class CompareBase {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CompareBase.class);

    protected static final int ARRAY_SIZE = 32;

    protected static final GenericObjectPool<DemoPojo> COMMONS_POOL;
    protected static final PoolService<DemoPojo> VIBUR_POOL = new ConcurrentPool<>(
            new ConcurrentLinkedQueueCollection<>(), new DemoPojoViburPoolObjectFactory(), 2000, 3000, false);
    protected static final Pool<DemoPojo> LITE_POOL;
    protected static final BeeObjectSource BEEOP_POOL;
    protected static final org.bbottema.genericobjectpool.GenericObjectPool<DemoPojo> G_O_POOL;
    protected static final ObjectsMemoryPool<DemoPojo> FS_POOL
            = ObjectsMemoryPoolFactory.newPool(new DemoPojoCreator(), 3000,
                                               new PoolStrategy(FetchStrategy.MUST_FETCH_IN_POOL,
                                                                FetchFailStrategy.CALL_CREATOR, false));

    static {
        try {
            GenericObjectPoolConfig<DemoPojo> CONFIG = new GenericObjectPoolConfig<>();
            CONFIG.setMaxTotal(3000);
            CONFIG.setMaxIdle(3000);
            CONFIG.setMinIdle(2000);
            COMMONS_POOL = new GenericObjectPool<>(new DemoPojoCommonsPooledObjectFactory(), CONFIG);
            COMMONS_POOL.preparePool();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    static {
        PoolBuilder<DemoPojo> builder = new PoolBuilder<>();
        LITE_POOL = builder.local(true)
                           .supplier(DemoPojo::new)
                           .consumer(null)
                           .interval(15000)
                           .minimum(2000)
                           .maximum(3000)
                           .timeout(5000)
                           .tenancy(30000)
                           .ttl(3000)
                           .tti(3000)
                           .verbose(false)
                           .build("object pool");
    }

    static {
        BeeObjectSourceConfig config = new BeeObjectSourceConfig();
        config.setObjectInterfaces(new Class[]{ValPojo.class});
        config.setMaxActive(3000);
        config.setMaxWait(2000);
        config.setObjectFactoryClass(DemoPojoBeeObjectFactory.class);
        BEEOP_POOL = new BeeObjectSource(config);
    }

    static {
        PoolConfig<DemoPojo> poolConfig = PoolConfig.<DemoPojo>builder()
                                                    // keeps 2000 objects eagerly allocated at all times
                                                    .corePoolsize(2000)
                                                    .maxPoolsize(3000)
                                                    // deallocate after 1 hour, but every time an object is claimed the expiry timeout is reset
                                                    .expirationPolicy(
                                                            new TimeoutSinceLastAllocationExpirationPolicy<>(1,
                                                                                                             TimeUnit.HOURS))
                                                    .build();

        G_O_POOL = new org.bbottema.genericobjectpool.GenericObjectPool<>(poolConfig, new DemoPojoAllocator());
    }

    @TearDown
    public void tearDown() throws Exception {
        COMMONS_POOL.close();
        VIBUR_POOL.close();
        LITE_POOL.stop(10, TimeUnit.SECONDS);
        BEEOP_POOL.close();
        Future<?> shutdownSequence = G_O_POOL.shutdown();
        // wait for shutdown to complete
        shutdownSequence.get();
        // until timeout
        shutdownSequence.get(10, TimeUnit.SECONDS);
    }

}
