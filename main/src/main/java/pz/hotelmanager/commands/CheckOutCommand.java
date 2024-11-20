package pz.hotelmanager.commands;

import pz.hotelmanager.Hotel;
import pz.hotelmanager.Room;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Command class to handle the check-out process for a hotel.
 * It prompts the user for the room number, calculates the bill,
 * and marks the room as checked out.
 */
public class CheckOutCommand implements Command {
    private final Hotel hotel;

    /**
     * Constructs a CheckOutCommand with the given hotel.
     *
     * @param hotel the hotel instance where the check-out operation will be performed
     */
    public CheckOutCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Executes the check-out operation by prompting the user for a room number,
     * calculating the bill, and updating the room status.
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

            if (!room.isOccupied()) {
                System.out.println("Room " + roomNumber + " is not currently occupied.");
                return;
            }

            LocalDate checkOutDate = LocalDate.now();
            double bill = room.calculateBill(checkOutDate);

            room.checkOut();

            System.out.println("Room " + roomNumber + " checked out successfully.");
            System.out.println("Total bill for the stay: $" + bill);
        } catch (Exception e) {
            System.out.println("Error during check-out: " + e.getMessage());
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
}
