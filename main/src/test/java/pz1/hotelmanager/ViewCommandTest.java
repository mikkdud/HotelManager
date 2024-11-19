package pz1.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pz1.hotelmanager.commands.ViewCommand;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ViewCommandTest {
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

    @Test
    void testViewNonExistentRoom() {
        String input = "999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ViewCommand command = new ViewCommand(hotel);
        command.execute(new String[]{});

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Room number 999 does not exist."));
    }


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
