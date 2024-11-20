package pz.hotelmanager.xlsxutils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pz.hotelmanager.Hotel;
import pz.hotelmanager.Room;
import pz.hotelmanager.Guest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Utility class for writing hotel data to an Excel file.
 */
public class HotelFileWriter {

    /**
     * Private constructor to prevent instantiation.
     * This class is intended to be used as a utility.
     */
    private HotelFileWriter() {
        throw new UnsupportedOperationException("HotelFileWriter is a utility class and cannot be instantiated.");
    }

    /**
     * Writes hotel data to an Excel file at the specified file path.
     *
     * @param hotel    the {@link Hotel} object containing the data to write
     * @param filePath the path to the Excel file where the data will be written
     * @throws IOException if an I/O error occurs during file writing
     */
    public static void writeToXLSX(Hotel hotel, String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Hotel Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Room Number");
            headerRow.createCell(1).setCellValue("Price");
            headerRow.createCell(2).setCellValue("Capacity");
            headerRow.createCell(3).setCellValue("Occupied");
            headerRow.createCell(4).setCellValue("Guests");
            headerRow.createCell(5).setCellValue("Check-In Date");
            headerRow.createCell(6).setCellValue("Duration");

            // Fill data rows with room details
            List<Integer> roomNumbers = hotel.getAllRoomNumbers();
            int rowNum = 1;

            for (Integer roomNumber : roomNumbers) {
                Room room = hotel.getRoom(roomNumber);
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(room.getRoomNumber());
                row.createCell(1).setCellValue(room.getPrice());
                row.createCell(2).setCellValue(room.getCapacity());
                row.createCell(3).setCellValue(room.isOccupied() ? "yes" : "no");

                if (room.isOccupied()) {
                    row.createCell(4).setCellValue(String.join(", ",
                            room.getGuests().stream().map(Guest::getName).toArray(String[]::new)));

                    row.createCell(5).setCellValue(room.getCheckInDate().toString());

                    long duration = ChronoUnit.DAYS.between(room.getCheckInDate(), room.getCheckOutDate());
                    row.createCell(6).setCellValue(duration);
                } else {
                    row.createCell(4).setCellValue("N/A");
                    row.createCell(5).setCellValue("N/A");
                    row.createCell(6).setCellValue("N/A");
                }
            }

            // Write the workbook to the file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
    }
}
