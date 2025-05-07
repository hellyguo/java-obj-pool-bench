package io.github.heillyguo.poolcmp.misc;

import cn.danielw.fop.ObjectFactory;
import io.github.heillyguo.poolcmp.domain.DemoPojo;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-05-07 14:49
 */
public class DemoPojoObjectFastPoolFactory implements ObjectFactory<DemoPojo> {
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
}
