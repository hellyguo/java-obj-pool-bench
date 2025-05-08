package io.github.hellyguo.poolcmp.misc;

import com.aitusoftware.recall.persistence.Encoder;
import io.github.hellyguo.poolcmp.domain.DemoPojo;

import java.nio.ByteBuffer;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-05-08 15:09
 */
public class DemoPojoEncoder implements Encoder<ByteBuffer, DemoPojo> {
    @Override
    public void store(ByteBuffer buffer, int offset, DemoPojo value) {
        buffer.putInt(offset, value.getVal1());
        buffer.putLong(offset + Integer.BYTES, value.getVal2());
        buffer.putDouble(offset + Integer.BYTES + Long.BYTES, value.getVal3());
        buffer.put(offset + Integer.BYTES + Long.BYTES + Double.BYTES, value.getVal4().getBytes());
    }
}
