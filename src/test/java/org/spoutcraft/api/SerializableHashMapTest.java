package org.spoutcraft.api;

import org.junit.Test;
import org.spoutcraft.api.util.map.SerializableHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SerializableHashMapTest {
    @Test
    public void test() {
        final SerializableHashMap<String, String> test1 = new SerializableHashMap<>();
        test1.put("Test1", "Test2");
        byte[] data = null;
        try {
            data = test1.serialize();
        } catch (Exception e) {
            fail("SerializableHashMap failed to serialize: " + e.toString());
        }
        if (data != null) {
            SerializableHashMap<String, String> test2 = null;
            try {
                test2 = new SerializableHashMap<>(data);
            } catch (Exception e) {
                fail("SerializableHashMap failed to read in serialized data: " + e.toString());
            }
            assertTrue(test1.get("Test1").equals(test2.get("Test1")));
            assertEquals(test1, test2);
        }
    }
}
