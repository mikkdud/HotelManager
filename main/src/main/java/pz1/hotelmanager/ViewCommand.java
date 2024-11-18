package pz1.hotelmanager;

import java.util.Scanner;

public class ViewCommand implements Command {
    private final Hotel hotel;

    public ViewCommand(Hotel hotel) {
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

            System.out.println("Room details:");
            System.out.println(room.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private int getValidRoomNumber(Scanner scanner) {
        while (true) {
            try {
                int roomNumber = Integer.parseInt(scanner.nextLine());
                return roomNumber;
            } catch (NumberFormatException e) {
                System.out.println("Invalid room number. Please enter a valid integer:");
            }
        }
    }
}
