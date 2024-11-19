package pz.hotelmanager.commands;

import pz.hotelmanager.Hotel;
import pz.hotelmanager.Room;

import java.time.LocalDate;
import java.util.Scanner;

public class CheckOutCommand implements Command {
    private final Hotel hotel;

    public CheckOutCommand(Hotel hotel) {
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
