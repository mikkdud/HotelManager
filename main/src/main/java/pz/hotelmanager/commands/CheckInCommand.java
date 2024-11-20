package pz.hotelmanager.commands;

import pz.hotelmanager.Guest;
import pz.hotelmanager.Hotel;
import pz.hotelmanager.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Command class to handle the check-in process for guests in a hotel.
 * It prompts the user for necessary information such as room number, check-in date, stay duration,
 * and guest details, and performs the check-in operation.
 */
public class CheckInCommand implements Command {
    private final Hotel hotel;

    /**
     * Constructs a CheckInCommand with the given hotel.
     *
     * @param hotel the hotel instance where the check-in operation will be performed
     */
    public CheckInCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Executes the check-in operation by gathering input from the user.
     *
     * @param args optional arguments (not used in this command)
     */
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

            System.out.println("Enter check-in date (format: YYYY-MM-DD) or press Enter for today:");
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

    /**
     * Prompts the user for a valid room number until a valid integer is provided.
     *
     * @param scanner the scanner for reading user input
     * @return a valid room number as an integer
     */
    private int getValidRoomNumber(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid room number. Please enter a valid integer:");
            }
        }
    }

    /**
     * Prompts the user for a valid check-in date in the format YYYY-MM-DD.
     * Defaults to the current date if no input is provided.
     *
     * @param scanner the scanner for reading user input
     * @return a valid LocalDate object
     */
    private LocalDate getValidDate(Scanner scanner) {
        while (true) {
            try {
                String dateInput = scanner.nextLine().trim();

                if (dateInput.isEmpty()) {
                    System.out.println("No date entered. Using today's date: " + LocalDate.now());
                    return LocalDate.now();
                }

                return LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD:");
            }
        }
    }

    /**
     * Prompts the user for a positive integer value.
     *
     * @param scanner      the scanner for reading user input
     * @param errorMessage the message to display for invalid input
     * @return a valid positive integer
     */
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

    /**
     * Prompts the user to input guest details, ensuring the input does not exceed the room capacity.
     *
     * @param scanner    the scanner for reading user input
     * @param maxCapacity the maximum number of guests allowed in the room
     * @return a list of guests for the room
     */
    private List<Guest> getGuestsFromInput(Scanner scanner, int maxCapacity) {
        List<Guest> guests = new ArrayList<>();
        System.out.println("Enter guest details (max capacity: " + maxCapacity + "):");

        for (int i = 0; i < maxCapacity; i++) {
            System.out.println("Enter guest name for person " + (i + 1) + ":");
            String name = scanner.nextLine().trim();

            while (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name:");
                name = scanner.nextLine().trim();
            }

            guests.add(new Guest(name));

            if (i < maxCapacity - 1) {
                System.out.println("Add another guest? (yes/no):");
                String response = scanner.nextLine().trim().toLowerCase();

                if ("no".equals(response)) {
                    break; // Exit the loop if the user does not want to add more guests
                } else if (!"yes".equals(response)) {
                    System.out.println("Invalid input. Assuming 'yes'.");
                }
            }
        }

        return guests;
    }
}
