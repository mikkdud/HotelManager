package pz1.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @BeforeEach
    void resetIdIterator() throws NoSuchFieldException, IllegalAccessException {

        var field = Guest.class.getDeclaredField("idIterator");
        field.setAccessible(true);
        field.setInt(null, 1);
    }

    @Test
    void testGuestInitialization() {
        Guest guest = new Guest("John Doe");
        assertEquals("John Doe", guest.getName());
        assertEquals(1, guest.getId());
    }

    @Test
    void testGuestIdAutoIncrement() {
        Guest guest1 = new Guest("John Doe");
        Guest guest2 = new Guest("Jane Doe");

        assertEquals(1, guest1.getId());
        assertEquals(2, guest2.getId());
    }

    @Test
    void testGetName() {
        Guest guest = new Guest("Alice");
        assertEquals("Alice", guest.getName());
    }

    @Test
    void testGetId() {
        Guest guest = new Guest("Bob");
        assertEquals(1, guest.getId());
    }

    @Test
    void testToString() {
        Guest guest = new Guest("Charlie");
        String expected = "Guest{name='Charlie', id='1'}";
        assertEquals(expected, guest.toString());
    }
}
