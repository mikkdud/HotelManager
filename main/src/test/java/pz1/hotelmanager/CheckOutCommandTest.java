package pz1.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckOutCommandTest {

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.addRoom(101, new Room(101, 200.0, 2));
        hotel.addRoom(102, new Room(102, 150.0, 1));

        // Zameldowanie gości w pokoju 101
        hotel.checkIn(101, List.of(new Guest("John Doe")), LocalDate.of(2024, 11, 10), 5);
    }

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

    @Test
    void testCheckOutNonExistentRoom() {
        String input = "999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});

        Room room = hotel.getRoom(999);
        assertNull(room); // Nie istnieje
    }

    @Test
    void testCheckOutUnoccupiedRoom() {
        String input = "102\n"; // Pokój 102 nigdy nie był zajęty
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});

        Room room = hotel.getRoom(102);
        assertFalse(room.isOccupied());
    }

    @Test
    void testHotelStateUnchangedForNonExistentRoom() {
        String input = "999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});

        assertEquals(2, hotel.getAllRoomNumbers().size()); // Hotel nadal ma 2 pokoje
    }

    @Test
    void testEmptyInput() {
        String input = "\n"; // Użytkownik nic nie wpisał
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});

        // Sprawdź, czy stan pokoju 101 się nie zmienił
        Room room = hotel.getRoom(101);
        assertTrue(room.isOccupied());
        assertFalse(room.getGuests().isEmpty());
    }

    @Test
    void testInvalidRoomNumberFormat() {
        String input = "abc\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckOutCommand command = new CheckOutCommand(hotel);
        command.execute(new String[]{});

        // Żaden pokój nie powinien być zmodyfikowany
        Room room = hotel.getRoom(101);
        assertTrue(room.isOccupied());
    }

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
