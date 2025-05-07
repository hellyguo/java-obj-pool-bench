package io.github.heillyguo.poolcmp.misc;

import io.github.heillyguo.poolcmp.domain.DemoPojo;
import nf.fr.eraasoft.pool.PoolException;
import nf.fr.eraasoft.pool.PoolableObjectBase;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-05-07 15:00
 */
public class DemoPojoPoolableObjectBase extends PoolableObjectBase<DemoPojo> {
    @Override
    public DemoPojo make() throws PoolException {
        return new DemoPojo();
    }

    @Override
    public void activate(DemoPojo testObject) throws PoolException {

    }
}
