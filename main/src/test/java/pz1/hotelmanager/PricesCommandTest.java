package pz1.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pz1.hotelmanager.commands.PricesCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PricesCommandTest {
    private Hotel hotel;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.addRoom(101, new Room(101, 200.0, 2));
        hotel.addRoom(102, new Room(102, 150.0, 1));

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testExecuteWithRooms() {
        PricesCommand command = new PricesCommand(hotel);
        command.execute(new String[]{});

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Room 101: $200,00 per night"));
        assertTrue(output.contains("Room 102: $150,00 per night"));
    }

    @Test
    void testExecuteWithoutRooms() {
        hotel = new Hotel(); // No rooms
        PricesCommand command = new PricesCommand(hotel);
        command.execute(new String[]{});

        String output = outputStream.toString().trim();
        assertEquals("No rooms available in the hotel.", output);
    }
}
