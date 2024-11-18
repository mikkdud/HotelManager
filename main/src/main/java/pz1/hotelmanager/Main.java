package pz1.hotelmanager;

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
                    command = new CheckOutCommand(transylvania); // Zakładamy, że jest zaimplementowana
                    break;
                case "view":
                    command = new ViewCommand(transylvania); // Zakładamy, że jest zaimplementowana
                    break;
                case "prices":
                    command = new PricesCommand(transylvania); // Zakładamy, że jest zaimplementowana
                    break;
                case "list":
                    command = new ListCommand(transylvania);
                    break;
                case "exit":
                    System.out.println("Exiting program.");
                    return; // Kończy program
                default:
                    System.out.println("Unknown command. Please try again.");
                    continue; // Pętla przechodzi do kolejnej iteracji
            }

            if (command != null) {
                command.execute(new String[]{}); // Pusta tablica argumentów
            }
        }
    }
}
