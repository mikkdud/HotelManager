package pz.hotelmanager;

import org.junit.jupiter.api.Test;
import pz.hotelmanager.commands.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final Hotel testHotel = new Hotel();
    private final String testFilePath = "test_hotel_data.xlsx";

    @Test
    void testGetCommand_CheckIn() {
        Command command = Main.getCommand("checkin", testHotel, testFilePath);
        assertNotNull(command);
        assertTrue(command instanceof CheckInCommand);
    }

    @Test
    void testGetCommand_CheckOut() {
        Command command = Main.getCommand("checkout", testHotel, testFilePath);
        assertNotNull(command);
        assertTrue(command instanceof CheckOutCommand);
    }

    @Test
    void testGetCommand_View() {
        Command command = Main.getCommand("view", testHotel, testFilePath);
        assertNotNull(command);
        assertTrue(command instanceof ViewCommand);
    }

    @Test
    void testGetCommand_Prices() {
        Command command = Main.getCommand("prices", testHotel, testFilePath);
        assertNotNull(command);
        assertTrue(command instanceof PricesCommand);
    }

    @Test
    void testGetCommand_List() {
        Command command = Main.getCommand("list", testHotel, testFilePath);
        assertNotNull(command);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void testGetCommand_Save() {
        Command command = Main.getCommand("save", testHotel, testFilePath);
        assertNotNull(command);
        assertTrue(command instanceof SaveCommand);
    }

    @Test
    void testGetCommand_Unknown() {
        Command command = Main.getCommand("unknown", testHotel, testFilePath);
        assertNull(command);
    }

    @Test
    void testGetCommand_Exit() {
        Command command = Main.getCommand("exit", testHotel, testFilePath);
        assertNull(command); // As per logic, "exit" returns null
    }
}
