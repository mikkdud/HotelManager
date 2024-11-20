package pz.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pz.hotelmanager.commands.CheckOutCommand;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CheckOutCommand} class.
 */
class CheckOutCommandTest {

    private Hotel hotel;

    /**
     * Sets up the test environment by creating a hotel instance with predefined rooms and occupants.
     */
    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.addRoom(101, new Room(101, 200.0, 2));
        hotel.addRoom(102, new Room(102, 150.0, 1));

        // Pre-check-in guest to room 101 for testing
        hotel.checkIn(101, List.of(new Guest("John Doe")), LocalDate.of(2024, 11, 10), 5);
    }

    /**
     * Tests the successful check-out of a room.
     * Ensures that room status and guest details are cleared after check-out.
     */
    @Test
    void testSuccessfulCheckOut() {
        String input = "101\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});

        Room room = hotel.getRoom(101);
        assertFalse(room.isOccupied());
        assertTrue(room.getGuests().isEmpty());
        assertNull(room.getCheckInDate());
        assertNull(room.getCheckOutDate());
    }

    /**
     * Tests attempting to check out a non-existent room.
     * Ensures no changes occur to the hotel state.
     */
    @Test
    void testCheckOutNonExistentRoom() {
        String input = "999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});

        Room room = hotel.getRoom(999);
        assertNull(room); // Room does not exist
    }

    /**
     * Tests checking out an unoccupied room.
     * Ensures that no actions are performed on the room.
     */
    @Test
    void testCheckOutUnoccupiedRoom() {
        String input = "102\n"; // Room 102 was never occupied
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});

        Room room = hotel.getRoom(102);
        assertFalse(room.isOccupied());
    }

    /**
     * Verifies that the hotel state remains unchanged when a non-existent room is checked out.
     */
    @Test
    void testHotelStateUnchangedForNonExistentRoom() {
        String input = "999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});

        assertEquals(2, hotel.getAllRoomNumbers().size()); // Hotel still has 2 rooms
    }

    /**
     * Tests behavior when the user provides empty input for a room number.
     * Ensures the room remains occupied.
     */
    @Test
    void testEmptyInput() {
        String input = "\n"; // User entered nothing
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});

        // Verify that room 101 remains occupied
        Room room = hotel.getRoom(101);
        assertTrue(room.isOccupied());
        assertFalse(room.getGuests().isEmpty());
    }

    /**
     * Tests behavior when an invalid room number format is provided (non-numeric).
     * Ensures no room state changes.
     */
    @Test
    void testInvalidRoomNumberFormat() {
        String input = "abc\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});

        // Verify no changes to room 101
        Room room = hotel.getRoom(101);
        assertTrue(room.isOccupied());
    }

    /**
     * Tests multiple check-out attempts for the same room.
     * Ensures that once checked out, further attempts do not alter the state.
     */
    @Test
    void testMultipleCheckOutAttempts() {
        String input = "101\n101\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});
        command.execute(new String[]{});

        Room room = hotel.getRoom(101);
        assertFalse(room.isOccupied());
        assertTrue(room.getGuests().isEmpty());
    }
}
