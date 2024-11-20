package pz.hotelmanager.commands;

import pz.hotelmanager.Guest;
import pz.hotelmanager.Hotel;
import pz.hotelmanager.Room;

import java.util.List;

/**
 * Command to list all rooms in the hotel along with their details.
 * Displays information such as price, capacity, occupancy status, and guest details.
 */
public class ListCommand implements Command {
    private final Hotel hotel;

    /**
     * Constructs a ListCommand with the specified hotel.
     *
     * @param hotel the hotel whose rooms will be listed
     */
    public ListCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Executes the command to list all rooms in the hotel.
     * Displays details of each room including its occupancy status and guest information if occupied.
     *
     * @param args not used in this command
     */
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
