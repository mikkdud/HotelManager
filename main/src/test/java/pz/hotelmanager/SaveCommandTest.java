package pz.hotelmanager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pz.hotelmanager.commands.SaveCommand;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link SaveCommand} class.
 */
class SaveCommandTest {

    private final String testFilePath = "test_hotel_data_save.xlsx";

    /**
     * Cleans up the test file after each test.
     * Ensures the file created during testing is deleted.
     */
    @AfterEach
    void cleanup() {
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            assertTrue(testFile.delete(), "Failed to clean up test file.");
        }
    }

    /**
     * Tests that the {@link SaveCommand#execute(String[])} method successfully creates
     * the expected file.
     * Ensures that the command generates a file at the specified location.
     */
    @Test
    void testExecute() {
        // Arrange
        Hotel hotel = new Hotel();
        hotel.addRoom(101, new Room(101, 200.0, 2));
        SaveCommand saveCommand = new SaveCommand(hotel, testFilePath);

        // Act
        saveCommand.execute(new String[]{});

        // Assert
        File testFile = new File(testFilePath);
        assertTrue(testFile.exists(), "File should be created by SaveCommand.");
    }
}
