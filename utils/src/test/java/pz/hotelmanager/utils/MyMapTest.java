package pz.hotelmanager.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyMapTest {

    @Test
    void testPutNewKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        assertEquals(100, (int)map.get("Key1"), "Value for 'Key1' should be 100");
        assertEquals(1, (int)map.keys().size(), "Map should contain 1 key");
    }

    @Test
    void testPutExistingKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        map.put("Key1", 200); // Update the value for existing key
        assertEquals(200, (int)map.get("Key1"), "Value for 'Key1' should be updated to 200");
        assertEquals(1, (int)map.keys().size(), "Map should still contain 1 key");
    }

    @Test
    void testPutNullKeyThrowsException() {
        MyMap<String, Integer> map = new MyMap<>();
        assertThrows(IllegalArgumentException.class, () -> map.put(null, 100), "Should throw exception for null key");
    }

    @Test
    void testPutNullValueThrowsException() {
        MyMap<String, Integer> map = new MyMap<>();
        assertThrows(IllegalArgumentException.class, () -> map.put("Key1", null), "Should throw exception for null value");
    }

    @Test
    void testRemoveExistingKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        assertTrue(map.remove("Key1"), "Remove should return true for existing key");
        assertFalse(map.contains("Key1"), "Map should not contain 'Key1' after removal");
        assertNull(map.get("Key1"), "Value for 'Key1' should be null after removal");
    }

    @Test
    void testRemoveNonExistingKey() {
        MyMap<String, Integer> map = new MyMap<>();
        assertFalse(map.remove("Key1"), "Remove should return false for non-existing key");
    }

    @Test
    void testGetExistingKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        assertEquals(100, (int)map.get("Key1"), "Should return correct value for 'Key1'");
    }

    @Test
    void testGetNonExistingKey() {
        MyMap<String, Integer> map = new MyMap<>();
        assertNull(map.get("Key1"), "Should return null for non-existing key");
    }

    @Test
    void testKeysReturnsAllKeys() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        map.put("Key2", 200);
        List<String> keys = map.keys();
        assertTrue(keys.contains("Key1"), "'Key1' should be in the keys list");
        assertTrue(keys.contains("Key2"), "'Key2' should be in the keys list");
        assertEquals(2, keys.size(), "Map should contain 2 keys");
    }

    @Test
    void testKeysAreImmutable() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        List<String> keys = map.keys();
        keys.add("Key2"); // This should not affect the original map
        assertEquals(1, map.keys().size(), "Original map keys should remain unaffected");
    }

    @Test
    void testContainsKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        assertTrue(map.contains("Key1"), "Map should contain 'Key1'");
        assertFalse(map.contains("Key2"), "Map should not contain 'Key2'");
    }
}
