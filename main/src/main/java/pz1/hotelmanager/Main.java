package pz1.hotelmanager;

import pz1.hotelmanager.commands.*;
import pz1.hotelmanager.xlsxutils.HotelFileReader;

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

            Command command = null;

            switch (input) {
                case "checkin":
                    command = new CheckInCommand(hotel);
                    break;
                case "checkout":
                    command = new CheckOutCommand(hotel);
                    break;
                case "view":
                    command = new ViewCommand(hotel);
                    break;
                case "prices":
                    command = new PricesCommand(hotel);
                    break;
                case "list":
                    command = new ListCommand(hotel);
                    break;
                case "save":
                    command = new SaveCommand(hotel, filePath);
                    break;
                case "exit":
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Unknown command. Please try again.");
                    continue;
            }

            if (command != null) {
                command.execute(new String[]{});
            }
        }
    }
}
