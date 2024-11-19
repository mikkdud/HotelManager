package pz.hotelmanager;

import org.junit.jupiter.api.Test;
import pz.hotelmanager.xlsxutils.HotelFileReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HotelFileReaderTest {
    @Test
    void testReadFromXLSXInvalidFile() {
        assertThrows(IOException.class, () -> {
            HotelFileReader.readFromXLSX("invalid_file.xlsx");
        });
    }
}
