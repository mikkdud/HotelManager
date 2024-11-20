package pz.hotelmanager;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pz.hotelmanager.xlsxutils.HotelFileWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link HotelFileWriter} class.
 */
class HotelFileWriterTest {

    private final String testFilePath = "test_hotel_data.xlsx";

    /**
     * Cleans up the test file after each test execution.
     * Ensures that the test environment is reset by deleting the generated file.
     */
    @AfterEach
    void cleanup() {
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            assertTrue(testFile.delete(), "Failed to clean up test file.");
        }
    }

    /**
     * Tests the {@link HotelFileWriter#writeToXLSX(Hotel, String)} method.
     * Verifies that the hotel data is correctly written to an XLSX file and
     * that the output file can be opened as a valid workbook.
     *
     * @throws IOException if an I/O error occurs while reading the test file.
     */
    @Test
    void testWriteToXLSX() throws IOException {
        // Arrange
        Hotel hotel = new Hotel();
        Room room1 = new Room(101, 200.0, 2);
        Room room2 = new Room(102, 150.0, 3);
        hotel.addRoom(101, room1);
        hotel.addRoom(102, room2);

        room1.checkIn(
                Arrays.asList(new Guest("Alice"), new Guest("Bob")),
                LocalDate.of(2024, 11, 15),
                5
        );

        // Act
        HotelFileWriter.writeToXLSX(hotel, testFilePath);

        // Assert
        File testFile = new File(testFilePath);
        assertTrue(testFile.exists(), "Output file was not created.");

        try (FileInputStream fis = new FileInputStream(testFilePath)) {
            assertNotNull(WorkbookFactory.create(fis), "Workbook could not be opened.");
        }
    }
}
