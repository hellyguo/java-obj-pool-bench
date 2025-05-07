package io.github.heillyguo.poolcmp.misc;

import io.github.heillyguo.poolcmp.domain.DemoPojo;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-04-10 14:44
 */
public class DemoPojo2BasePoolableObjectFactory extends BasePooledObjectFactory<DemoPojo> {
    @Override
    public DemoPojo create() throws Exception {
        return new DemoPojo();
    }

    @Override
    public PooledObject<DemoPojo> wrap(DemoPojo obj) {
        return null;
    }
}
