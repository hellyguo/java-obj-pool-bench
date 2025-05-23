package io.github.hellyguo.poolcmp.misc;

import io.github.hellyguo.poolcmp.domain.DemoPojo;
import stormpot.Poolable;
import stormpot.Slot;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-04-10 14:52
 */
public class PooledSlotDemoPojo extends DemoPojo implements Poolable {
    private final Slot slot;

    public PooledSlotDemoPojo(Slot slot) {
        super();
        this.slot = slot;
    }

    @Override
    public void release() {
        slot.release(this);
    }
}
