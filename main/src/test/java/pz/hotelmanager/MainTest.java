package pz.hotelmanager;

import org.junit.jupiter.api.Test;
import pz.hotelmanager.commands.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Main} class.
 * Focuses on testing the behavior of the {@link Main#getCommand(String, Hotel, String)} method.
 */
class MainTest {

    private final Hotel testHotel = new Hotel();
    private final String testFilePath = "test_hotel_data.xlsx";

    /**
     * Verifies that the "checkin" command returns an instance of {@link CheckInCommand}.
     */
    @Test
    void testGetCommand_CheckIn() {
        Command command = Main.getCommand("checkin", testHotel, testFilePath);
        assertNotNull(command, "Expected a non-null CheckInCommand instance.");
        assertTrue(command instanceof CheckInCommand, "Expected instance of CheckInCommand.");
    }

    /**
     * Verifies that the "checkout" command returns an instance of {@link CheckOutCommand}.
     */
    @Test
    void testGetCommand_CheckOut() {
        Command command = Main.getCommand("checkout", testHotel, testFilePath);
        assertNotNull(command, "Expected a non-null CheckOutCommand instance.");
        assertTrue(command instanceof CheckOutCommand, "Expected instance of CheckOutCommand.");
    }

    /**
     * Verifies that the "view" command returns an instance of {@link ViewCommand}.
     */
    @Test
    void testGetCommand_View() {
        Command command = Main.getCommand("view", testHotel, testFilePath);
        assertNotNull(command, "Expected a non-null ViewCommand instance.");
        assertTrue(command instanceof ViewCommand, "Expected instance of ViewCommand.");
    }

    /**
     * Verifies that the "prices" command returns an instance of {@link PricesCommand}.
     */
    @Test
    void testGetCommand_Prices() {
        Command command = Main.getCommand("prices", testHotel, testFilePath);
        assertNotNull(command, "Expected a non-null PricesCommand instance.");
        assertTrue(command instanceof PricesCommand, "Expected instance of PricesCommand.");
    }

    /**
     * Verifies that the "list" command returns an instance of {@link ListCommand}.
     */
    @Test
    void testGetCommand_List() {
        Command command = Main.getCommand("list", testHotel, testFilePath);
        assertNotNull(command, "Expected a non-null ListCommand instance.");
        assertTrue(command instanceof ListCommand, "Expected instance of ListCommand.");
    }

    /**
     * Verifies that the "save" command returns an instance of {@link SaveCommand}.
     */
    @Test
    void testGetCommand_Save() {
        Command command = Main.getCommand("save", testHotel, testFilePath);
        assertNotNull(command, "Expected a non-null SaveCommand instance.");
        assertTrue(command instanceof SaveCommand, "Expected instance of SaveCommand.");
    }

    /**
     * Verifies that an unknown command returns null.
     */
    @Test
    void testGetCommand_Unknown() {
        Command command = Main.getCommand("unknown", testHotel, testFilePath);
        assertNull(command, "Expected null for an unknown command.");
    }

    /**
     * Verifies that the "exit" command returns null.
     * The "exit" command is handled separately in the main loop logic.
     */
    @Test
    void testGetCommand_Exit() {
        Command command = Main.getCommand("exit", testHotel, testFilePath);
        assertNull(command, "Expected null for the 'exit' command.");
    }
}
