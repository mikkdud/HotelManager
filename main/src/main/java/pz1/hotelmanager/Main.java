package pz1.hotelmanager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel transylvania = new Hotel();
        Scanner scanner = new Scanner(System.in);

        transylvania.addRoom(101, new Room(101, 200.0, 2));
        transylvania.addRoom(102, new Room(102, 150.0, 1));

        while (true) {
            System.out.println("Enter command (checkin, checkout, view, prices, list, exit):");
            String input = scanner.nextLine().trim().toLowerCase();

            String[] inputParts = input.split(" ");
            String commandName = inputParts[0];
            String[] commandArgs = new String[inputParts.length - 1];
            if (inputParts.length > 1) {
                System.arraycopy(inputParts, 1, commandArgs, 0, inputParts.length - 1);
            }

            Command command = null;

            switch (commandName) {
                case "checkin":
                    command = new CheckInCommand(transylvania);
                    break;
//                case "checkout":
//                    command = new CheckOutCommand(transylvania); // Zakładamy, że jest zaimplementowana
//                    break;
//                case "view":
//                    command = new ViewCommand(transylvania); // Zakładamy, że jest zaimplementowana
//                    break;
//                case "prices":
//                    command = new PricesCommand(transylvania); // Zakładamy, że jest zaimplementowana
//                    break;
                case "list":
                    command = new ListCommand(transylvania); // Zakładamy, że jest zaimplementowana
                    break;
                case "exit":
                    System.out.println("Exiting program.");
                    return; // Kończy program
                default:
                    System.out.println("Unknown command. Please try again.");
                    continue; // Pętla przechodzi do kolejnej iteracji
            }

            if (command != null) {
                command.execute(commandArgs);
            }
        }

    }
}