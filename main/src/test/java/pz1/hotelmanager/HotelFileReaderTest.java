package pz1.hotelmanager;

import org.junit.jupiter.api.Test;
import pz1.hotelmanager.xlsxutils.HotelFileReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class HotelFileReaderTest {

    @Test
    void testReadFromXLSXSuccessfully() {
        try (InputStream inputStream = getClass().getResourceAsStream("/hotel_data.xlsx")) {
            assertNotNull(inputStream, "Test file not found in resources.");

            Hotel hotel = HotelFileReader.readFromXLSX(inputStream);

            assertEquals(3, hotel.getAllRoomNumbers().size());

            Room room101 = hotel.getRoom(101);
            assertNotNull(room101);
            assertEquals(200.0, room101.getPrice());
            assertEquals(2, room101.getCapacity());
            assertTrue(room101.isOccupied());
            assertEquals(2, room101.getGuests().size());

            Room room102 = hotel.getRoom(102);
            assertNotNull(room102);
            assertFalse(room102.isOccupied());

            Room room103 = hotel.getRoom(103);
            assertNotNull(room103);
            assertTrue(room103.isOccupied());
            assertEquals(2, room103.getGuests().size());

        } catch (Exception e) {
            fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test
    void testReadFromXLSXInvalidFile() throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream("Invalid content".getBytes())) {
            assertThrows(org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException.class,
                    () -> HotelFileReader.readFromXLSX(inputStream));
        }
    }

}
