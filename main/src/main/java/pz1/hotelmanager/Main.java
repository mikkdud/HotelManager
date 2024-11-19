package pz1.hotelmanager;

import pz1.hotelmanager.commands.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel transylvania = new Hotel();
        Scanner scanner = new Scanner(System.in);

        transylvania.addRoom(101, new Room(101, 220.0, 2));
        transylvania.addRoom(102, new Room(102, 150.0, 1));

        while (true) {
            System.out.println("Enter command (checkin, checkout, view, prices, list, exit):");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.contains(" ")) {
                System.out.println("Error: Please enter a single word command only.");
                continue;
            }

            Command command = null;

            switch (input) {
                case "checkin":
                    command = new CheckInCommand(transylvania);
                    break;
                case "checkout":
                    command = new CheckOutCommand(transylvania);
                    break;
                case "view":
                    command = new ViewCommand(transylvania);
                    break;
                case "prices":
                    command = new PricesCommand(transylvania);
                    break;
                case "list":
                    command = new ListCommand(transylvania);
                    break;
                case "exit":
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Unknown command. Please try again.");
                    continue;
            }

            if (command != null) {
                command.execute(new String[]{}); // Pusta tablica argumentów
            }
        }
    }
}
