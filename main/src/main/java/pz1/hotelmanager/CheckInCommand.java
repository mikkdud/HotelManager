package pz1.hotelmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckInCommand implements Command {
    private Hotel hotel;

    public CheckInCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter room number:");
            int roomNumber = getValidRoomNumber(scanner);

            Room room = hotel.getRoom(roomNumber);
            if (room == null) {
                System.out.println("Room number " + roomNumber + " does not exist.");
                return;
            }

            System.out.println("Enter check-in date (format: YYYY-MM-DD):");
            LocalDate checkInDate = getValidDate(scanner);

            System.out.println("Enter stay duration:");
            int duration = getValidInteger(scanner, "Stay duration must be a positive integer.");

            System.out.println("Room capacity: " + room.getCapacity());
            List<Guest> guests = getGuestsFromInput(scanner, room.getCapacity());

            hotel.checkIn(roomNumber, guests, checkInDate, duration);
            System.out.println("Guest(s) checked in successfully.");

        } catch (Exception e) {
            System.out.println("Error during check-in: " + e.getMessage());
        }
    }

    private int getValidRoomNumber(Scanner scanner) {
        while (true) {
            try {
                int roomNumber = Integer.parseInt(scanner.nextLine());
                return roomNumber;
            } catch (NumberFormatException e) {
                System.out.println("Invalid room number. Please enter a valid integer:");
            }
        }
    }

    private LocalDate getValidDate(Scanner scanner) {
        while (true) {
            try {
                String dateInput = scanner.nextLine();
                return LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD:");
            }
        }
    }

    private int getValidInteger(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private List<Guest> getGuestsFromInput(Scanner scanner, int maxCapacity) {
        List<Guest> guests = new ArrayList<>();
        System.out.println("Enter guest details (max capacity: " + maxCapacity + "):");

        for (int i = 0; i < maxCapacity; i++) {
            System.out.println("Enter guest name for person " + (i + 1) + ":");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name:");
                i--;
                continue;
            }

            guests.add(new Guest(name));

            if (i < maxCapacity - 1) {
                System.out.println("Add another guest? (yes/no):");
                String response = scanner.nextLine().trim().toLowerCase();

                if (response.equals("no")) {
                    break;
                } else if (!response.equals("yes")) {
                    System.out.println("Invalid input. Assuming 'yes'.");
                }
            }
        }

        return guests;
    }
}
