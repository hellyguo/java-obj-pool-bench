package io.github.heillyguo.poolcmp;

import org.pacesys.kbop.IPoolObjectFactory;
import org.pacesys.kbop.PoolKey;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-04-10 14:21
 */
public class DemoPojoObjectFactory implements IPoolObjectFactory<String, DemoPojo> {
    @Override
    public DemoPojo create(PoolKey<String> key) {
        return new DemoPojo();
    }

    @Override
    public void activate(DemoPojo object) {
    }

    @Override
    public void passivate(DemoPojo object) {
    }

    @Override
    public void destroy(DemoPojo object) {
    }
}
