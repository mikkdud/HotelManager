package pz.hotelmanager;

import pz.hotelmanager.commands.*;
import pz.hotelmanager.xlsxutils.HotelFileReader;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main entry point for the Hotel Management application.
 * <p>
 * This class handles user interaction via the command line, allowing users
 * to perform operations such as checking in guests, checking out guests,
 * viewing room details, listing all rooms, saving hotel data, and more.
 * </p>
 */
public class Main {

    /**
     * Main method that runs the Hotel Management application.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        // Direct path to the Excel file containing hotel data
        String filePath = "hotel_data.xlsx";

        try {
            // Load hotel data from the specified Excel file
            hotel = HotelFileReader.readFromXLSX(filePath);
            System.out.println("Hotel data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading hotel data: " + e.getMessage());
        }

        // Main loop for user interaction
        while (true) {
            System.out.println("Enter command (checkin, checkout, view, prices, list, save, exit):");
            String input = scanner.nextLine().trim().toLowerCase();

            // Ensure single-word commands
            if (input.contains(" ")) {
                System.out.println("Error: Please enter a single word command only.");
                continue;
            }

            // Retrieve and execute the appropriate command
            Command command = getCommand(input, hotel, filePath);

            if (command == null) {
                if ("exit".equals(input)) {
                    System.out.println("Exiting program.");
                    return; // End the program
                } else {
                    System.out.println("Unknown command. Please try again.");
                }
            } else {
                command.execute(new String[]{}); // Execute the command without arguments
            }
        }
    }

    /**
     * Maps user input to the corresponding command.
     *
     * @param input    the command entered by the user.
     * @param hotel    the {@link Hotel} instance to operate on.
     * @param filePath the file path for saving hotel data.
     * @return a {@link Command} object corresponding to the user input, or {@code null} if the command is unknown.
     */
    static Command getCommand(String input, Hotel hotel, String filePath) {
        switch (input) {
            case "checkin":
                return new CheckInCommand(hotel);
            case "checkout":
                return new CheckOutCommand(hotel);
            case "view":
                return new ViewCommand(hotel);
            case "prices":
                return new PricesCommand(hotel);
            case "list":
                return new ListCommand(hotel);
            case "save":
                return new SaveCommand(hotel, filePath);
            default:
                return null; // Return null if the command is not recognized
        }
    }
}
