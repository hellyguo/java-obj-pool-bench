package io.github.hellyguo.poolcmp.misc;

import com.aitusoftware.recall.persistence.IdAccessor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-05-08 15:09
 */
public class DemoPojoIdAccessor implements IdAccessor<DemoPojo> {
    @Override
    public long getId(DemoPojo value) {
        return value.getVal2();
    }
}
