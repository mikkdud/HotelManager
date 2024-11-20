package pz.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Guest} class.
 */
class GuestTest {

    /**
     * Resets the static ID iterator before each test.
     * Ensures predictable ID values for each test case.
     */
    @BeforeEach
    void resetIdIterator() throws NoSuchFieldException, IllegalAccessException {
        Guest.class.getDeclaredField("idIterator").setAccessible(true);
        Guest.class.getDeclaredField("idIterator").setInt(null, 1);
    }

    /**
     * Tests the initialization of a new {@link Guest} object.
     * Verifies that the name and ID are correctly set.
     */
    @Test
    void testGuestInitialization() {
        Guest guest = new Guest("John Doe");
        assertEquals("John Doe", guest.getName());
        assertEquals(1, guest.getId());
    }

    /**
     * Verifies that the ID field auto-increments when multiple {@link Guest} objects are created.
     */
    @Test
    void testGuestIdAutoIncrement() {
        Guest guest1 = new Guest("John Doe");
        Guest guest2 = new Guest("Jane Doe");

        assertEquals(1, guest1.getId());
        assertEquals(2, guest2.getId());
    }

    /**
     * Tests the {@link Guest#getName()} method.
     * Ensures it returns the correct name.
     */
    @Test
    void testGetName() {
        Guest guest = new Guest("Alice");
        assertEquals("Alice", guest.getName());
    }

    /**
     * Tests the {@link Guest#getId()} method.
     * Ensures it returns the correct ID.
     */
    @Test
    void testGetId() {
        Guest guest = new Guest("Bob");
        assertEquals(1, guest.getId());
    }

    /**
     * Tests the {@link Guest#toString()} method.
     * Verifies that the string representation includes the name and ID in the expected format.
     */
    @Test
    void testToString() {
        Guest guest = new Guest("Charlie");
        String expected = "Guest: Charlie (ID:1)";
        assertEquals(expected, guest.toString());
    }
}
