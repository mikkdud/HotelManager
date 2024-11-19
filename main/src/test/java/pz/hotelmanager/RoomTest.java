package pz.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(101, 200.0, 2);
    }

    @Test
    void testCheckInSuccessfully() {
        List<Guest> guests = List.of(new Guest("John Doe"), new Guest("Jane Doe"));
        LocalDate checkInDate = LocalDate.of(2024, 11, 10);
        int duration = 5;

        room.checkIn(guests, checkInDate, duration);

        assertTrue(room.isOccupied());
        assertEquals(guests, room.getGuests());
        assertEquals(checkInDate, room.getCheckInDate());
        assertEquals(checkInDate.plusDays(duration), room.getCheckOutDate());
    }

    @Test
    void testCheckInRoomAlreadyOccupied() {
        room.checkIn(List.of(new Guest("John Doe")), LocalDate.of(2024, 11, 10), 3);

        assertThrows(IllegalStateException.class, () ->
                room.checkIn(List.of(new Guest("Jane Doe")), LocalDate.of(2024, 11, 15), 3));
    }

    @Test
    void testCheckInTooManyGuests() {
        List<Guest> guests = List.of(new Guest("John Doe"), new Guest("Jane Doe"), new Guest("Extra Guest"));

        assertThrows(IllegalArgumentException.class, () ->
                room.checkIn(guests, LocalDate.of(2024, 11, 10), 3));
    }

    @Test
    void testCalculateBillSuccessfully() {
        room.checkIn(List.of(new Guest("John Doe")), LocalDate.of(2024, 11, 10), 5);
        LocalDate checkOutDate = LocalDate.of(2024, 11, 15);

        double bill = room.calculateBill(checkOutDate);
        assertEquals(1000.0, bill); // 5 dni * 200.0 za dobę
    }

    @Test
    void testCalculateBillWithoutCheckIn() {
        assertThrows(IllegalStateException.class, () ->
                room.calculateBill(LocalDate.of(2024, 11, 15)));
    }

    @Test
    void testCheckOutSuccessfully() {
        room.checkIn(List.of(new Guest("John Doe")), LocalDate.of(2024, 11, 10), 3);

        room.checkOut();

        assertFalse(room.isOccupied());
        assertTrue(room.getGuests().isEmpty());
        assertNull(room.getCheckInDate());
        assertNull(room.getCheckOutDate());
    }

    @Test
    void testToString() {
        room.checkIn(List.of(new Guest("John Doe")), LocalDate.of(2024, 11, 10), 3);

        String roomString = room.toString();

        assertTrue(roomString.contains("Room 101:"));
        assertTrue(roomString.contains("price: 200.0"));
        assertTrue(roomString.contains("capacity: 2"));
        assertTrue(roomString.contains("occupied: true"));
        assertTrue(roomString.contains("checkInDate: 2024-11-10"));
        assertTrue(roomString.contains("checkOutDate: 2024-11-13"));
    }

}
