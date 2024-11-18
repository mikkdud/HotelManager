package pz1.hotelmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

class CheckInCommandTest {

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.addRoom(101, new Room(101, 150.0, 2));
    }

    @Test
    void testExecuteCheckInSuccessfully() {
        String input = "101\n2024-11-20\n3\nJohn Doe\nyes\nJane Doe\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckInCommand command = new CheckInCommand(hotel);
        command.execute(new String[]{});

        Room room = hotel.getRoom(101);
        assertTrue(room.isOccupied());
        assertEquals(2, room.getGuests().size());
        assertEquals(LocalDate.of(2024, 11, 20), room.getCheckInDate());
    }

    @Test
    void testExecuteInvalidRoomNumber() {
        String input = "999\n2024-11-20\n3\nJohn Doe\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckInCommand command = new CheckInCommand(hotel);
        command.execute(new String[]{});

        Room room = hotel.getRoom(999);
        assertNull(room);
    }

    @Test
    void testExecuteInvalidDateFormat() {
        String input = "101\ninvalid-date\n3\nJohn Doe\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CheckInCommand command = new CheckInCommand(hotel);
        command.execute(new String[]{});

        Room room = hotel.getRoom(101);
        assertFalse(room.isOccupied());
    }
}
