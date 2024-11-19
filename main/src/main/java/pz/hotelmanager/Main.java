package pz.hotelmanager;

import pz.hotelmanager.commands.*;
import pz.hotelmanager.xlsxutils.HotelFileReader;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        // Bezpośrednia ścieżka do pliku
        String filePath = "hotel_data.xlsx";

        try {
            hotel = HotelFileReader.readFromXLSX(filePath);
            System.out.println("Hotel data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading hotel data: " + e.getMessage());
        }

        while (true) {
            System.out.println("Enter command (checkin, checkout, view, prices, list, save, exit):");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.contains(" ")) {
                System.out.println("Error: Please enter a single word command only.");
                continue;
            }

            Command command = getCommand(input, hotel, filePath);

            if (command == null) {
                if ("exit".equals(input)) {
                    System.out.println("Exiting program.");
                    return;
                } else {
                    System.out.println("Unknown command. Please try again.");
                }
            } else {
                command.execute(new String[]{});
            }
        }
    }

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
                return null;
        }
    }
}
