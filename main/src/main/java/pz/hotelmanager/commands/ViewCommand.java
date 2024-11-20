package pz.hotelmanager.commands;

import pz.hotelmanager.Hotel;
import pz.hotelmanager.Room;

import java.util.Scanner;

/**
 * Command to view the details of a specific room in the hotel.
 */
public class ViewCommand implements Command {
    private final Hotel hotel;

    /**
     * Constructs a ViewCommand with the specified hotel.
     *
     * @param hotel the hotel whose room details will be viewed
     */
    public ViewCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Executes the command to view the details of a specified room.
     * Prompts the user for a room number, retrieves the room details,
     * and displays them. If the room does not exist, an error message is displayed.
     *
     * @param args not used in this command
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

            System.out.println("Room details:");
            System.out.println(room.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prompts the user until a valid room number is provided.
     *
     * @param scanner the scanner to read user input
     * @return a valid room number
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
