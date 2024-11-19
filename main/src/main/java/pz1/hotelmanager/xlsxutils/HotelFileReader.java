package pz1.hotelmanager.xlsxutils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pz1.hotelmanager.*;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.InputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelFileReader {

    public static Hotel readFromXLSX(InputStream inputStream) throws IOException {
        Hotel hotel = new Hotel();

        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Pomijanie nagłówków

                int roomNumber = (int) getNumericValue(row.getCell(0));
                double price = getNumericValue(row.getCell(1));
                int capacity = (int) getNumericValue(row.getCell(2));
                boolean occupied = row.getCell(3).getStringCellValue().equalsIgnoreCase("yes");

                Room room = new Room(roomNumber, price, capacity);

                if (occupied) {
                    List<Guest> guests = new ArrayList<>();
                    String guestNames = row.getCell(4).getStringCellValue();
                    for (String guestName : guestNames.split(",")) {
                        guests.add(new Guest(guestName.trim()));
                    }

                    LocalDate checkInDate = getValidDate(row.getCell(5));
                    int duration = (int) getNumericValue(row.getCell(6));

                    room.checkIn(guests, checkInDate, duration);
                }

                hotel.addRoom(roomNumber, room);
            }
        }

        return hotel;
    }

    private static double getNumericValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                throw new IllegalStateException("Expected numeric value but got: " + cell.getStringCellValue());
            }
        } else {
            throw new IllegalStateException("Unexpected cell type: " + cell.getCellType());
        }
    }

    private static LocalDate getValidDate(Cell cell) {
        if (cell == null) {
            return LocalDate.now(); // Domyślnie dzisiejsza data, jeśli komórka jest pusta
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getLocalDateTimeCellValue().toLocalDate();
            } else {
                return LocalDate.of(1900, 1, 1).plusDays((long) cell.getNumericCellValue() - 2);
            }
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return LocalDate.parse(cell.getStringCellValue().trim());
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid date format: " + cell.getStringCellValue());
            }
        } else {
            throw new IllegalArgumentException("Unexpected cell type for date: " + cell.getCellType());
        }
    }
}
