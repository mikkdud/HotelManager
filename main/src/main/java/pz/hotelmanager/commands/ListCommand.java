package pz.hotelmanager.commands;

import pz.hotelmanager.Guest;
import pz.hotelmanager.Hotel;
import pz.hotelmanager.Room;

import java.util.List;

public class ListCommand implements Command {
    private final Hotel hotel;

    public ListCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] args) {
        List<Integer> roomNumbers = hotel.getAllRoomNumbers();

        if (roomNumbers.isEmpty()) {
            System.out.println("No rooms available in the hotel.");
            return;
        }

        for (int roomNumber : roomNumbers) {
            Room room = hotel.getRoom(roomNumber);
            System.out.println("Room Number: " + room.getRoomNumber());
            System.out.println("Price per Night: " + room.getPrice());
            System.out.println("Capacity: " + room.getCapacity());
            System.out.println("Occupied: " + (room.isOccupied() ? "Yes" : "No"));

            if (room.isOccupied()) {
                System.out.println("Guests:");
                for (Guest guest : room.getGuests()) {
                    System.out.println("- " + guest);
                }
                System.out.println("Check-in Date: " + room.getCheckInDate());
                System.out.println("Check-out Date: " + room.getCheckOutDate());
            }

            System.out.println("-------------------------------");
        }
    }
}
