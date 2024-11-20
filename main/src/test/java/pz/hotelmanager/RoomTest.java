package pz.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Room} class.
 */
class RoomTest {

    private Room room;

    /**
     * Sets up a new {@link Room} instance before each test.
     */
    @BeforeEach
    void setUp() {
        room = new Room(101, 200.0, 2);
    }

    /**
     * Tests that a successful check-in updates the room's state correctly.
     */
    @Test
    void testCheckInSuccessfully() {
        List<Guest> guests = List.of(new Guest("John Doe"), new Guest("Jane Doe"));
        LocalDate checkInDate = LocalDate.of(2024, 11, 10);
        int duration = 5;

        room.checkIn(guests, checkInDate, duration);

        assertTrue(room.isOccupied());
        assertEquals(guests, room.getGuests());
        assertEquals(checkInDate, room.getCheckInDate());
        assertEquals(checkInDate.plusDays(duration), room.getCheckOutDate());
    }

    /**
     * Tests that attempting to check in a room that's already occupied throws an exception.
     */
    @Test
    void testCheckInRoomAlreadyOccupied() {
        room.checkIn(List.of(new Guest("John Doe")), LocalDate.of(2024, 11, 10), 3);

        assertThrows(IllegalStateException.class, () -> attemptCheckIn(room));
    }

    /**
     * Helper method to attempt a check-in on an occupied room.
     */
    private void attemptCheckIn(Room room) {
        room.checkIn(List.of(new Guest("Jane Doe")), LocalDate.of(2024, 11, 15), 3);
    }

    /**
     * Tests that checking in more guests than the room's capacity throws an exception.
     */
    @Test
    void testCheckInTooManyGuests() {
        List<Guest> guests = List.of(new Guest("John Doe"), new Guest("Jane Doe"), new Guest("Extra Guest"));

        assertThrows(IllegalArgumentException.class, () -> attemptTooManyGuestsCheckIn(room, guests));
    }

    /**
     * Helper method to attempt a check-in with too many guests.
     */
    private void attemptTooManyGuestsCheckIn(Room room, List<Guest> guests) {
        room.checkIn(guests, LocalDate.of(2024, 11, 10), 3);
    }

    /**
     * Tests that calculating the bill after a valid stay returns the correct amount.
     */
    @Test
    void testCalculateBillSuccessfully() {
        room.checkIn(List.of(new Guest("John Doe")), LocalDate.of(2024, 11, 10), 5);
        LocalDate checkOutDate = LocalDate.of(2024, 11, 15);

        double bill = room.calculateBill(checkOutDate);
        assertEquals(1000.0, bill); // 5 days * 200.0 per night
    }

    /**
     * Tests that attempting to calculate a bill without a valid check-in throws an exception.
     */
    @Test
    void testCalculateBillWithoutCheckIn() {
        assertThrows(IllegalStateException.class, () -> attemptCalculateBill(room));
    }

    /**
     * Helper method to attempt bill calculation without check-in.
     */
    private void attemptCalculateBill(Room room) {
        room.calculateBill(LocalDate.of(2024, 11, 15));
    }

    /**
     * Tests that checking out a room updates its state correctly.
     */
    @Test
    void testCheckOutSuccessfully() {
        room.checkIn(List.of(new Guest("John Doe")), LocalDate.of(2024, 11, 10), 3);

        room.checkOut();

        assertFalse(room.isOccupied());
        assertTrue(room.getGuests().isEmpty());
        assertNull(room.getCheckInDate());
        assertNull(room.getCheckOutDate());
    }

    /**
     * Tests the {@code toString} method for correct formatting.
     */
    @Test
    void testToString() {
        room.checkIn(List.of(new Guest("John Doe")), LocalDate.of(2024, 11, 10), 3);

        String roomString = room.toString();

        assertTrue(roomString.contains("Room 101:"));
        assertTrue(roomString.contains("price: 200.0"));
        assertTrue(roomString.contains("capacity: 2"));
        assertTrue(roomString.contains("occupied: true"));
        assertTrue(roomString.contains("checkInDate: 2024-11-10"));
        assertTrue(roomString.contains("checkOutDate: 2024-11-13"));
    }
}
