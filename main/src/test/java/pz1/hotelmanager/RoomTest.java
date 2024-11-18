package pz1.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(101, 150.0, 2);
    }

    @Test
    void testGetters() {
        assertEquals(101, room.getRoomNumber());
        assertEquals(150.0, room.getPrice());
        assertEquals(2, room.getCapacity());
        assertFalse(room.isOccupied());
        assertTrue(room.getGuests().isEmpty());
        assertNull(room.getCheckInDate());
        assertNull(room.getCheckOutDate());
    }


    @Test
    void testRoomInitialization() {
        assertEquals(101, room.getRoomNumber());
        assertEquals(150.0, room.getPrice());
        assertEquals(2, room.getCapacity());
        assertFalse(room.isOccupied());
        assertTrue(room.getGuests().isEmpty());
        assertNull(room.getCheckInDate());
        assertNull(room.getCheckOutDate());
    }

    @Test
    void testCheckInSuccessfully() {
        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest("John Doe"));
        guests.add(new Guest("Jane Doe"));

        LocalDate checkInDate = LocalDate.of(2024, 11, 20);
        int duration = 3;

        room.checkIn(guests, checkInDate, duration);

        assertTrue(room.isOccupied());
        assertEquals(guests, room.getGuests());
        assertEquals(checkInDate, room.getCheckInDate());
        assertEquals(checkInDate.plusDays(duration), room.getCheckOutDate());
    }

    @Test
    void testCheckInTooManyGuests() {
        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest("John Doe"));
        guests.add(new Guest("Jane Doe"));
        guests.add(new Guest("Extra Guest"));

        LocalDate checkInDate = LocalDate.of(2024, 11, 20);
        int duration = 3;

        assertThrows(IllegalArgumentException.class, () -> room.checkIn(guests, checkInDate, duration));
    }

    @Test
    void testCheckInWhenOccupied() {
        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest("John Doe"));

        LocalDate checkInDate = LocalDate.of(2024, 11, 20);
        int duration = 3;

        room.checkIn(guests, checkInDate, duration);

        List<Guest> newGuests = new ArrayList<>();
        newGuests.add(new Guest("New Guest"));

        assertThrows(IllegalStateException.class, () -> room.checkIn(newGuests, checkInDate.plusDays(4), 2));
    }

    @Test
    void testToString() {
        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest("John Doe"));
        guests.add(new Guest("Jane Doe"));

        LocalDate checkInDate = LocalDate.of(2024, 11, 20);
        int duration = 3;

        room.checkIn(guests, checkInDate, duration);

        String expectedString = "Room{" +
                "roomNumber=101" +
                ", price=150.0" +
                ", capacity=2" +
                ", occupied=true" +
                ", guests=" + guests +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkInDate.plusDays(duration) +
                '}';

        assertEquals(expectedString, room.toString());
    }
}
