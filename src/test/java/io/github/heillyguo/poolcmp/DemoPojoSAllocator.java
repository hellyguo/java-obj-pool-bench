package io.github.heillyguo.poolcmp;

import stormpot.Allocator;
import stormpot.Slot;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-04-10 14:51
 */
public class DemoPojoSAllocator implements Allocator<PooledSlotDemoPojo> {
    @Override
    public PooledSlotDemoPojo allocate(Slot slot) throws Exception {
        return new PooledSlotDemoPojo(slot);
    }

    @Override
    public void deallocate(PooledSlotDemoPojo poolable) throws Exception {
    }
}
