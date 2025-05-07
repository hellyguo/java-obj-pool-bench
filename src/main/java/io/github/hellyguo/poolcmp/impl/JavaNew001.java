package io.github.hellyguo.poolcmp.impl;

import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;
import org.openjdk.jmh.infra.Blackhole;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-05-07 18:26
 */
public class JavaNew001 implements PoolImplementor {

    @Override
    public void testPool(Blackhole blackhole) {
        blackhole.consume(new DemoPojo());
    }

    @Override
    public void shutdown() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
