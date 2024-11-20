package pz.hotelmanager;

import org.junit.jupiter.api.Test;
import pz.hotelmanager.xlsxutils.HotelFileReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link HotelFileReader} class.
 */
class HotelFileReaderTest {

    /**
     * Tests the {@link HotelFileReader#readFromXLSX(String)} method
     * with an invalid file path.
     * Ensures that an {@link IOException} is thrown when attempting to read a non-existent file.
     */
    @Test
    void testReadFromXLSXInvalidFile() {
        assertThrows(IOException.class, () -> {
            HotelFileReader.readFromXLSX("invalid_file.xlsx");
        }, "Expected IOException when reading from an invalid file path.");
    }
}
