package io.github.hellyguo.poolcmp.misc;

import com.aitusoftware.recall.persistence.Decoder;
import io.github.hellyguo.poolcmp.domain.DemoPojo;

import java.nio.ByteBuffer;

/**
 * @author Helly Guo
 * <p>
 * Created on 2025-05-08 15:09
 */
public class DemoPojoDecoder implements Decoder<ByteBuffer, DemoPojo> {
    @Override
    public void load(ByteBuffer buffer, int offset, DemoPojo value) {
        value.setVal1(buffer.getInt(offset));
        value.setVal2(buffer.getLong(offset + Integer.BYTES));
        value.setVal3(buffer.getDouble(offset + Integer.BYTES + Long.BYTES));
        int val4FieldOffset = Integer.BYTES + Long.BYTES + Double.BYTES;
        byte[] array = buffer.array();
        value.setVal4(new String(array, offset + val4FieldOffset, array.length - val4FieldOffset));
    }
}
