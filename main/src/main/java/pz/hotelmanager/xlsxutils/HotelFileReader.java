package pz.hotelmanager.xlsxutils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pz.hotelmanager.Hotel;
import pz.hotelmanager.Room;
import pz.hotelmanager.Guest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HotelFileReader {

    private HotelFileReader() {
        throw new UnsupportedOperationException("HotelFileReader is a utility class and cannot be instantiated.");
    }

    public static Hotel readFromXLSX(String filePath) throws IOException {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            return readFromXLSX(inputStream);
        }
    }

    public static Hotel readFromXLSX(InputStream inputStream) throws IOException {
        Hotel hotel = new Hotel();

        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                int roomNumber = (int) getNumericValue(row.getCell(0));
                double price = getNumericValue(row.getCell(1));
                int capacity = (int) getNumericValue(row.getCell(2));
                boolean occupied = "yes".equalsIgnoreCase(row.getCell(3).getStringCellValue().trim());

                Room room = new Room(roomNumber, price, capacity);

                if (occupied) {
                    List<Guest> guests = new ArrayList<>();
                    String[] guestNames = getStringValue(row.getCell(4)).split(",");
                    for (String guestName : guestNames) {
                        guests.add(new Guest(guestName.trim()));
                    }

                    LocalDate checkInDate = parseDate(row.getCell(5));
                    int duration = (int) getNumericValue(row.getCell(6));
                    room.checkIn(guests, checkInDate, duration);
                }

                hotel.addRoom(roomNumber, room);
            }
        }

        return hotel;
    }

    private static double getNumericValue(Cell cell) {
        if (cell == null) {
            throw new IllegalStateException("Cell is null and cannot be parsed.");
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    throw new IllegalStateException("Expected numeric value but got invalid string: " + cell.getStringCellValue(), e);
                }
            default:
                throw new IllegalStateException("Unexpected cell type: " + cell.getCellType());
        }
    }

    private static String getStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                throw new IllegalStateException("Unexpected cell type: " + cell.getCellType());
        }
    }

    private static LocalDate parseDate(Cell cell) {
        if (cell == null) {
            throw new IllegalStateException("Date cell is null.");
        }

        switch (cell.getCellType()) {
            case STRING:
                return LocalDate.parse(cell.getStringCellValue().trim(), DateTimeFormatter.ISO_DATE);
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toLocalDate();
                } else {
                    throw new IllegalStateException("Numeric cell is not a valid date.");
                }
            default:
                throw new IllegalStateException("Unexpected cell type for date: " + cell.getCellType());
        }
    }
}
