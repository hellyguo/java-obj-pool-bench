package io.github.heillyguo.poolcmp.misc;

import io.github.heillyguo.poolcmp.domain.DemoPojo;
import org.apache.commons.pool.BasePoolableObjectFactory;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-04-10 14:44
 */
public class DemoPojoBasePoolableObjectFactory extends BasePoolableObjectFactory<DemoPojo> {
    @Override
    public DemoPojo makeObject() throws Exception {
        return new DemoPojo();
    }
}
