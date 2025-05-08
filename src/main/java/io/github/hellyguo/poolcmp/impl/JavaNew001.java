package io.github.hellyguo.poolcmp.impl;

import io.github.hellyguo.poolcmp.PojoCustomer;
import io.github.hellyguo.poolcmp.PoolImplementor;
import io.github.hellyguo.poolcmp.domain.DemoPojo;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-05-07 18:26
 */
public class JavaNew001 implements PoolImplementor {

    @Override
    public void testPool(PojoCustomer customer) {
        customer.consume(new DemoPojo());
    }

    @Override
    public void testPoolBatch(PojoCustomer customer, DemoPojo[] pojoArray, int batchSize) {
        for (int i = 0; i < batchSize; i++) {
            pojoArray[i] = new DemoPojo();
        }
        customer.consume(pojoArray);
    }

    @Override
    public void shutdown() {
    }
}
