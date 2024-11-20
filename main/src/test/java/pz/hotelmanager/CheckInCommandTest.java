package pz.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pz.hotelmanager.commands.CheckInCommand;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CheckInCommand} class.
 * These tests validate the behavior of the command under different scenarios.
 */
class CheckInCommandTest {

    private Hotel hotel;

    /**
     * Sets up a new {@link Hotel} instance with a sample room before each test.
     */
    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.addRoom(101, new Room(101, 150.0, 2));
    }

    /**
     * Tests the successful execution of the {@link CheckInCommand}.
     * Validates that guests are correctly checked into the room.
     */
    @Test
    void testExecuteCheckInSuccessfully() {
        String input = "101\n2024-11-20\n3\nJohn Doe\nyes\nJane Doe\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckInCommand command = new CheckInCommand(hotel);
        command.execute(new String[]{});

        Room room = hotel.getRoom(101);
        assertTrue(room.isOccupied(), "Room should be marked as occupied after check-in.");
        assertEquals(2, room.getGuests().size(), "Room should have 2 guests after check-in.");
        assertEquals(LocalDate.of(2024, 11, 20), room.getCheckInDate(), "Check-in date should match input.");
    }

    /**
     * Tests the {@link CheckInCommand} with an invalid room number.
     * Verifies that no check-in occurs for non-existent rooms.
     */
    @Test
    void testExecuteInvalidRoomNumber() {
        String input = "999\n2024-11-20\n3\nJohn Doe\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckInCommand command = new CheckInCommand(hotel);
        command.execute(new String[]{});

        Room room = hotel.getRoom(999);
        assertNull(room, "Non-existent room should not exist in the hotel.");
    }

    /**
     * Tests the {@link CheckInCommand} with an invalid date format.
     * Ensures that the command handles invalid date input gracefully without checking in.
     */
    @Test
    void testExecuteInvalidDateFormat() {
        String input = "101\ninvalid-date\n3\nJohn Doe\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckInCommand command = new CheckInCommand(hotel);
        command.execute(new String[]{});

        Room room = hotel.getRoom(101);
        assertFalse(room.isOccupied(), "Room should remain unoccupied after invalid date input.");
    }
}
