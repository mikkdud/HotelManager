package pz.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Hotel} class.
 */
class HotelTest {

    private Hotel hotel;

    /**
     * Sets up a new {@link Hotel} instance with predefined rooms before each test.
     */
    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.addRoom(101, new Room(101, 150.0, 2));
        hotel.addRoom(102, new Room(102, 200.0, 3));
    }

    /**
     * Tests adding and retrieving rooms from the hotel.
     * Verifies that an added room can be correctly retrieved, and non-existent rooms return null.
     */
    @Test
    void testAddRoomAndRetrieve() {
        Room room = hotel.getRoom(101);
        assertNotNull(room);
        assertEquals(101, room.getRoomNumber());
        assertEquals(150.0, room.getPrice());

        Room nonExistentRoom = hotel.getRoom(999);
        assertNull(nonExistentRoom);
    }

    /**
     * Tests checking in guests into a room.
     * Verifies that the room is marked as occupied and all guest details are correctly recorded.
     */
    @Test
    void testCheckInSuccessfully() {
        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest("John Doe"));
        guests.add(new Guest("Jane Doe"));

        LocalDate checkInDate = LocalDate.of(2024, 11, 20);
        int duration = 3;

        hotel.checkIn(101, guests, checkInDate, duration);

        Room room = hotel.getRoom(101);
        assertTrue(room.isOccupied());
        assertEquals(guests, room.getGuests());
        assertEquals(checkInDate, room.getCheckInDate());
        assertEquals(checkInDate.plusDays(duration), room.getCheckOutDate());
    }

    /**
     * Tests attempting to check in guests to a non-existent room.
     * Verifies that an {@link IllegalArgumentException} is thrown.
     */
    @Test
    void testCheckInNonExistentRoom() {
        assertThrows(IllegalArgumentException.class, () -> attemptCheckInNonExistentRoom());
    }

    private void attemptCheckInNonExistentRoom() {
        hotel.checkIn(999, List.of(new Guest("John Doe")), LocalDate.of(2024, 11, 20), 3);
    }

    /**
     * Tests attempting to check in guests to an already occupied room.
     * Verifies that an {@link IllegalStateException} is thrown.
     */
    @Test
    void testCheckInOccupiedRoom() {
        List<Guest> guests1 = new ArrayList<>();
        guests1.add(new Guest("John Doe"));

        List<Guest> guests2 = new ArrayList<>();
        guests2.add(new Guest("Jane Doe"));

        hotel.checkIn(101, guests1, LocalDate.of(2024, 11, 20), 3);

        assertThrows(IllegalStateException.class, () -> attemptCheckInOccupiedRoom(guests2));
    }

    private void attemptCheckInOccupiedRoom(List<Guest> guests) {
        hotel.checkIn(101, guests, LocalDate.of(2024, 11, 21), 2);
    }

    /**
     * Tests retrieving all room numbers in the hotel.
     * Verifies that the returned list contains the correct room numbers.
     */
    @Test
    void testGetAllRoomNumbers() {
        List<Integer> roomNumbers = hotel.getAllRoomNumbers();
        assertEquals(2, roomNumbers.size());
        assertTrue(roomNumbers.contains(101));
        assertTrue(roomNumbers.contains(102));
    }
}
