package pz1.hotelmanager.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A custom implementation of a generic Map-like data structure.
 * This class stores key-value pairs using two separate lists:
 * one for keys and one for values.
 *
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public class MyMap<K, V> {

    // List to store keys
    private List<K> keys;

    // List to store values
    private List<V> values;

    /**
     * Constructor to initialize the keys and values lists.
     */
    public MyMap() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    /**
     * Adds a key-value pair to the map or updates the value if the key already exists.
     *
     * @param key   the key (must not be null)
     * @param value the value associated with the key (must not be null)
     * @throws IllegalArgumentException if key or value is null
     */
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value must not be null");
        }

        int index = this.keys.indexOf(key);
        if (index != -1) {
            // Key already exists, update the value
            values.set(index, value);
        } else {
            // New key-value pair
            keys.add(key);
            values.add(value);
        }
    }


    /**
     * Removes a key-value pair from the map by the given key.
     *
     * @param key the key to remove
     * @return true if the key was found and removed, false otherwise
     */
    public boolean remove(K key) {
        int index = this.keys.indexOf(key);
        if (index != -1) {
            keys.remove(index);
            values.remove(index);
            return true;
        }
        return false; // Key not found
    }

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key the key whose value is to be returned
     * @return the value associated with the key, or null if the key is not found
     */
    public V get(K key) {
        int index = keys.indexOf(key);
        if (index != -1) {
            return values.get(index);
        }
        return null; // Key not found
    }

    /**
     * Returns a list of all keys in the map.
     *
     * @return a list of keys
     */
    public List<K> keys() {
        return new ArrayList<>(keys); // Return a copy to prevent external modifications
    }

    /**
     * Checks if the map contains the given key.
     *
     * @param key the key to check
     * @return true if the key exists in the map, false otherwise
     */
    public boolean contains(K key) {
        return keys.contains(key);
    }
}
