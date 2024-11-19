package pz1.hotelmanager;

import org.junit.jupiter.api.Test;
import pz1.hotelmanager.xlsxutils.HotelFileReader;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class HotelFileReaderTest {
    @Test
    void testReadFromXLSXInvalidFile() {
        assertThrows(IOException.class, () -> {
            HotelFileReader.readFromXLSX("invalid_file.xlsx");
        });
    }
}
