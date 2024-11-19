package pz.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.addRoom(101, new Room(101, 150.0, 2));
        hotel.addRoom(102, new Room(102, 200.0, 3));
    }

    @Test
    void testAddRoomAndRetrieve() {
        Room room = hotel.getRoom(101);
        assertNotNull(room);
        assertEquals(101, room.getRoomNumber());
        assertEquals(150.0, room.getPrice());

        Room nonExistentRoom = hotel.getRoom(999);
        assertNull(nonExistentRoom);
    }

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

    @Test
    void testCheckInNonExistentRoom() {
        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest("John Doe"));

        LocalDate checkInDate = LocalDate.of(2024, 11, 20);
        int duration = 3;

        assertThrows(IllegalArgumentException.class, () -> hotel.checkIn(999, guests, checkInDate, duration));
    }

    @Test
    void testCheckInOccupiedRoom() {
        List<Guest> guests1 = new ArrayList<>();
        guests1.add(new Guest("John Doe"));

        List<Guest> guests2 = new ArrayList<>();
        guests2.add(new Guest("Jane Doe"));

        LocalDate checkInDate = LocalDate.of(2024, 11, 20);
        int duration = 3;

        hotel.checkIn(101, guests1, checkInDate, duration);

        assertThrows(IllegalStateException.class, () -> hotel.checkIn(101, guests2, checkInDate.plusDays(1), 2));
    }

    @Test
    void testGetAllRoomNumbers() {
        List<Integer> roomNumbers = hotel.getAllRoomNumbers();
        assertEquals(2, roomNumbers.size());
        assertTrue(roomNumbers.contains(101));
        assertTrue(roomNumbers.contains(102));
    }
}
