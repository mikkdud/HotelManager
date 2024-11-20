package pz.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pz.hotelmanager.commands.PricesCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link PricesCommand} class.
 */
class PricesCommandTest {
    private Hotel hotel;
    private ByteArrayOutputStream outputStream;

    /**
     * Sets up the test environment by initializing the {@link Hotel} object and capturing system output.
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
     * Tests the {@code execute} method of {@link PricesCommand} when rooms are present in the hotel.
     * Verifies that the correct room prices are printed.
     */
    @Test
    void testExecuteWithRooms() {
        PricesCommand command = new PricesCommand(hotel);
        command.execute(new String[]{});

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Room 101: $200,00 per night"));
        assertTrue(output.contains("Room 102: $150,00 per night"));
    }

    /**
     * Tests the {@code execute} method of {@link PricesCommand} when no rooms are available in the hotel.
     * Verifies that the appropriate message is printed.
     */
    @Test
    void testExecuteWithoutRooms() {
        hotel = new Hotel(); // No rooms
        PricesCommand command = new PricesCommand(hotel);
        command.execute(new String[]{});

        String output = outputStream.toString().trim();
        assertEquals("No rooms available in the hotel.", output);
    }
}
