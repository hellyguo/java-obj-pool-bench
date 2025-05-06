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
import cn.beeop.pool.FastObjectPool;
import cn.danielw.fop.ObjectFactory;
import cn.itcraft.frogspawn.ObjectsMemoryPool;
import cn.itcraft.frogspawn.ObjectsMemoryPoolFactory;
import cn.nextop.lite.pool.Pool;
import cn.nextop.lite.pool.PoolBuilder;
import com.coralblocks.coralpool.ArrayObjectPool;
import com.coralblocks.coralpool.LinkedObjectPool;
import nf.fr.eraasoft.pool.PoolException;
import nf.fr.eraasoft.pool.PoolSettings;
import nf.fr.eraasoft.pool.PoolableObjectBase;
import nf.fr.eraasoft.pool.impl.PoolControler;
import org.apache.commons.pool.impl.SoftReferenceObjectPool;
import org.apache.commons.pool.impl.StackObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.bbottema.genericobjectpool.expirypolicies.TimeoutSinceLastAllocationExpirationPolicy;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.pacesys.kbop.IKeyedObjectPool;
import org.pacesys.kbop.Pools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vibur.objectpool.ConcurrentPool;
import org.vibur.objectpool.PoolService;
import org.vibur.objectpool.util.ConcurrentLinkedQueueCollection;
import stormpot.BlazePool;
import stormpot.Config;
import stormpot.QueuePool;
import stormpot.Timeout;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class CompareBase {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CompareBase.class);

    protected static final int ARRAY_SIZE = 32;

    private static final int INITIAL_SIZE = 2000;
    private static final int MAX_SIZE = 3000;

    protected static final cn.beeop.pool.ObjectPool BEEOP_FAST_POOL;
    protected static final BeeObjectSource BEEOP_POOL;
    protected static final StackObjectPool<DemoPojo> COMMONS_1_POOL_STACK;
    protected static final org.apache.commons.pool.impl.GenericObjectPool<DemoPojo> COMMONS_1_POOL;
    protected static final org.apache.commons.pool.impl.SoftReferenceObjectPool<DemoPojo> COMMONS_1_POOL_SOFT_REF;
    protected static final org.apache.commons.pool2.impl.GenericObjectPool<DemoPojo> COMMONS_2_POOL;
    protected static final org.apache.commons.pool2.impl.SoftReferenceObjectPool<DemoPojo> COMMONS_2_POOL_SOFT_REF;
    protected static final com.coralblocks.coralpool.ObjectPool<DemoPojo> CORAL_ARRAY_POOL;
    protected static final com.coralblocks.coralpool.ObjectPool<DemoPojo> CORAL_LINKED_POOL;
    protected static final nf.fr.eraasoft.pool.ObjectPool<DemoPojo> ERASOFT_POOL;
    protected static final ObjectsMemoryPool<DemoPojo> FS_POOL =
            ObjectsMemoryPoolFactory.newPool(new DemoPojoCreator(), MAX_SIZE);
    protected static final org.bbottema.genericobjectpool.GenericObjectPool<DemoPojo> G_O_POOL;
    protected static final IKeyedObjectPool.Multi<String, DemoPojo> KOP_POOL =
            Pools.createMultiPool(new DemoPojoObjectFactory(), MAX_SIZE);
    protected static final Pool<DemoPojo> LITE_POOL;
    protected static final BlazePool<PooledSlotDemoPojo> S_BLAZE_POOL;
    protected static final QueuePool<PooledSlotDemoPojo> S_QUEUE_POOL;
    protected static final PoolService<DemoPojo> VIBUR_POOL =
            new ConcurrentPool<>(new ConcurrentLinkedQueueCollection<>(),
                                 new DemoPojoViburPoolObjectFactory(),
                                 INITIAL_SIZE,
                                 MAX_SIZE,
                                 false);

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

    static {
        BeeObjectSourceConfig config = new BeeObjectSourceConfig();
        config.setObjectInterfaces(new Class[]{ValPojo.class});
        config.setMaxActive(MAX_SIZE);
        config.setMaxWait(INITIAL_SIZE);
        config.setObjectFactoryClass(DemoPojoBeeObjectFactory.class);
        BEEOP_POOL = new BeeObjectSource(config);
    }

    static {
        COMMONS_1_POOL_STACK =
                new StackObjectPool<>(
                        new DemoPojoBasePoolableObjectFactory(),
                        INITIAL_SIZE,
                        MAX_SIZE);
    }

    static {
        COMMONS_1_POOL = new org.apache.commons.pool.impl.GenericObjectPool<>(
                new DemoPojoBasePoolableObjectFactory(),
                MAX_SIZE);
    }

    static {
        COMMONS_1_POOL_SOFT_REF = new SoftReferenceObjectPool<>(new DemoPojoBasePoolableObjectFactory());
    }

    static {
        try {
            GenericObjectPoolConfig<DemoPojo> config = new GenericObjectPoolConfig<>();
            config.setMaxTotal(MAX_SIZE);
            config.setMaxIdle(MAX_SIZE);
            config.setMinIdle(INITIAL_SIZE);
            COMMONS_2_POOL =
                    new org.apache.commons.pool2.impl.GenericObjectPool<>(
                            new DemoPojoCommonsPooledObjectFactory(), config);
            COMMONS_2_POOL.preparePool();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            GenericObjectPoolConfig<DemoPojo> config = new GenericObjectPoolConfig<>();
            config.setMaxTotal(MAX_SIZE);
            config.setMaxIdle(MAX_SIZE);
            config.setMinIdle(INITIAL_SIZE);
            COMMONS_2_POOL_SOFT_REF = new org.apache.commons.pool2.impl.SoftReferenceObjectPool<>(
                    new DemoPojo2BasePoolableObjectFactory());
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    static {
        // the pool can allocate more instances later, but it will start with 50 instances
        final int preloadCount = 50;
        // the pool can use a Builder, but it can also take a Class for creating instances through the default constructor
        final Class<DemoPojo> klass = DemoPojo.class;
        // Create your object pool
        CORAL_ARRAY_POOL = new ArrayObjectPool<>(INITIAL_SIZE, preloadCount, klass);
    }

    static {
        // the pool can allocate more instances later, but it will start with 50 instances
        final int preloadCount = 50;
        // the pool can use a Builder, but it can also take a Class for creating instances through the default constructor
        final Class<DemoPojo> klass = DemoPojo.class;
        // Create your object pool
        CORAL_LINKED_POOL = new LinkedObjectPool<>(INITIAL_SIZE, preloadCount, klass);
    }

    static {
        PoolSettings<DemoPojo> settings = new PoolSettings<>(
                new PoolableObjectBase<DemoPojo>() {
                    @Override
                    public DemoPojo make() throws PoolException {
                        return new DemoPojo();
                    }

                    @Override
                    public void activate(DemoPojo testObject) throws PoolException {

                    }
                });
        settings.min(INITIAL_SIZE).max(MAX_SIZE);
        ERASOFT_POOL = settings.pool();
    }

    protected static final cn.danielw.fop.ObjectPool<DemoPojo> FAST_POOL;

    static {
        cn.danielw.fop.PoolConfig config = new cn.danielw.fop.PoolConfig();
        config.setMaxSize(MAX_SIZE);
        FAST_POOL = new cn.danielw.fop.ObjectPool<>(config, new ObjectFactory<DemoPojo>() {
            @Override
            public DemoPojo create() {
                return new DemoPojo();
            }

            @Override
            public void destroy(DemoPojo o) {
            }

            @Override
            public boolean validate(DemoPojo o) {
                return true;
            }
        });
    }

    static {
        // deallocate after 1 hour, but every time an object is claimed the expiry timeout is reset
        TimeoutSinceLastAllocationExpirationPolicy<DemoPojo> expirationPolicy =
                new TimeoutSinceLastAllocationExpirationPolicy<>(1, TimeUnit.HOURS);
        org.bbottema.genericobjectpool.PoolConfig<DemoPojo> poolConfig =
                org.bbottema.genericobjectpool.PoolConfig.<DemoPojo>builder()
                                                         // keeps 2000 objects eagerly allocated at all times
                                                         .corePoolsize(INITIAL_SIZE)
                                                         .maxPoolsize(MAX_SIZE)
                                                         // deallocate after 1 hour, but every time an object is claimed the expiry timeout is reset
                                                         .expirationPolicy(expirationPolicy)
                                                         .build();
        G_O_POOL = new org.bbottema.genericobjectpool.GenericObjectPool<>(poolConfig, new DemoPojoAllocator());
    }

    static {
        PoolBuilder<DemoPojo> builder = new PoolBuilder<>();
        LITE_POOL = builder.local(true)
                           .supplier(DemoPojo::new)
                           .consumer(null)
                           .interval(15000)
                           .minimum(INITIAL_SIZE)
                           .maximum(MAX_SIZE)
                           .timeout(5000)
                           .tenancy(30000)
                           .ttl(MAX_SIZE)
                           .tti(MAX_SIZE)
                           .verbose(false)
                           .build("object pool");
    }

    static {
        Config<PooledSlotDemoPojo> config = new Config<PooledSlotDemoPojo>().setAllocator(new DemoPojoSAllocator());
        config.setSize(MAX_SIZE);
        S_BLAZE_POOL = new BlazePool<>(config);
    }

    static {
        Config<PooledSlotDemoPojo> config = new Config<PooledSlotDemoPojo>().setAllocator(new DemoPojoSAllocator());
        config.setSize(MAX_SIZE);
        S_QUEUE_POOL = new QueuePool<>(config);
    }

    @TearDown
    public void tearDown() throws Exception {
        BEEOP_FAST_POOL.close();
        BEEOP_POOL.close();
        COMMONS_1_POOL_STACK.close();
        COMMONS_1_POOL.close();
        COMMONS_1_POOL_SOFT_REF.close();
        COMMONS_2_POOL.close();
        COMMONS_2_POOL_SOFT_REF.close();
        // ERASOFT_POOL shutdown
        PoolControler.shutdown();
        FAST_POOL.shutdown();
        Future<?> shutdownSequence = G_O_POOL.shutdown();
        // wait for shutdown to complete
        shutdownSequence.get();
        // until timeout
        shutdownSequence.get(10, TimeUnit.SECONDS);
        KOP_POOL.shutdown();
        LITE_POOL.stop(10, TimeUnit.SECONDS);
        S_BLAZE_POOL.shutdown().await(new Timeout(10, TimeUnit.SECONDS));
        S_QUEUE_POOL.shutdown().await(new Timeout(10, TimeUnit.SECONDS));
        VIBUR_POOL.close();
    }

}
