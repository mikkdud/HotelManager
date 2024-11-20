package pz.hotelmanager.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link MyMap} class.
 */
class MyMapTest {

    /**
     * Tests the {@link MyMap#put(Object, Object)} method for adding a new key-value pair.
     * Ensures that the key-value pair is added successfully.
     */
    @Test
    void testPutNewKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        assertEquals(100, (int) map.get("Key1"), "Value for 'Key1' should be 100");
        assertEquals(1, map.keys().size(), "Map should contain 1 key");
    }

    /**
     * Tests the {@link MyMap#put(Object, Object)} method for updating an existing key.
     * Ensures that the value is updated correctly without adding a new key.
     */
    @Test
    void testPutExistingKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        map.put("Key1", 200); // Update the value for existing key
        assertEquals(200, (int) map.get("Key1"), "Value for 'Key1' should be updated to 200");
        assertEquals(1, map.keys().size(), "Map should still contain 1 key");
    }

    /**
     * Tests that adding a null key using {@link MyMap#put(Object, Object)} throws an exception.
     */
    @Test
    void testPutNullKeyThrowsException() {
        MyMap<String, Integer> map = new MyMap<>();
        assertThrows(IllegalArgumentException.class, () -> map.put(null, 100), "Should throw exception for null key");
    }

    /**
     * Tests that adding a null value using {@link MyMap#put(Object, Object)} throws an exception.
     */
    @Test
    void testPutNullValueThrowsException() {
        MyMap<String, Integer> map = new MyMap<>();
        assertThrows(IllegalArgumentException.class, () -> map.put("Key1", null), "Should throw exception for null value");
    }

    /**
     * Tests the {@link MyMap#remove(Object)} method for removing an existing key.
     * Ensures that the key and its value are removed successfully.
     */
    @Test
    void testRemoveExistingKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        assertTrue(map.remove("Key1"), "Remove should return true for existing key");
        assertFalse(map.contains("Key1"), "Map should not contain 'Key1' after removal");
        assertNull(map.get("Key1"), "Value for 'Key1' should be null after removal");
    }

    /**
     * Tests the {@link MyMap#remove(Object)} method for a non-existing key.
     * Ensures that it returns false without affecting the map.
     */
    @Test
    void testRemoveNonExistingKey() {
        MyMap<String, Integer> map = new MyMap<>();
        assertFalse(map.remove("Key1"), "Remove should return false for non-existing key");
    }

    /**
     * Tests the {@link MyMap#get(Object)} method for retrieving a value for an existing key.
     * Ensures that the correct value is returned.
     */
    @Test
    void testGetExistingKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        assertEquals(100, (int) map.get("Key1"), "Should return correct value for 'Key1'");
    }

    /**
     * Tests the {@link MyMap#get(Object)} method for a non-existing key.
     * Ensures that it returns null.
     */
    @Test
    void testGetNonExistingKey() {
        MyMap<String, Integer> map = new MyMap<>();
        assertNull(map.get("Key1"), "Should return null for non-existing key");
    }

    /**
     * Tests the {@link MyMap#keys()} method to ensure it returns all keys in the map.
     */
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

    /**
     * Tests the immutability of the keys list returned by {@link MyMap#keys()}.
     * Ensures that external modifications do not affect the map.
     */
    @Test
    void testKeysAreImmutable() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        List<String> keys = map.keys();
        keys.add("Key2"); // This should not affect the original map
        assertEquals(1, map.keys().size(), "Original map keys should remain unaffected");
    }

    /**
     * Tests the {@link MyMap#contains(Object)} method for checking if a key exists in the map.
     */
    @Test
    void testContainsKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("Key1", 100);
        assertTrue(map.contains("Key1"), "Map should contain 'Key1'");
        assertFalse(map.contains("Key2"), "Map should not contain 'Key2'");
    }
}
