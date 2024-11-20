package pz.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pz.hotelmanager.commands.ViewCommand;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link ViewCommand} class.
 */
class ViewCommandTest {

    private Hotel hotel;
    private ByteArrayOutputStream outputStream;

    /**
     * Sets up the test environment by initializing a {@link Hotel} with sample rooms
     * and redirecting system output to capture console messages.
     */
    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.addRoom(101, new Room(101, 200.0, 2));
        hotel.addRoom(102, new Room(102, 150.0, 1));

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Tests the {@link ViewCommand#execute(String[])} method for an existing room.
     * Verifies that the correct room details are displayed.
     */
    @Test
    void testViewExistingRoom() {
        String input = "101\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ViewCommand command = new ViewCommand(hotel);
        command.execute(new String[]{});

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Room details:"));
        assertTrue(output.contains("Room 101:"));
        assertTrue(output.contains("price: 200.0"));
    }

    /**
     * Tests the {@link ViewCommand#execute(String[])} method for a non-existent room.
     * Verifies that an appropriate message is displayed.
     */
    @Test
    void testViewNonExistentRoom() {
        String input = "999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ViewCommand command = new ViewCommand(hotel);
        command.execute(new String[]{});

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Room number 999 does not exist."));
    }

    /**
     * Tests the {@link ViewCommand#execute(String[])} method for an invalid room number input.
     * Verifies that the user is prompted to enter a valid integer.
     */
    @Test
    void testInvalidRoomNumber() {
        String input = "abc\n101\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ViewCommand command = new ViewCommand(hotel);
        command.execute(new String[]{});

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Invalid room number. Please enter a valid integer:"));
        assertTrue(output.contains("Room details:"));
    }
}
