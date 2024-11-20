package pz.hotelmanager.commands;

import pz.hotelmanager.Hotel;
import pz.hotelmanager.Room;

import java.util.List;

/**
 * Command to display the prices of all rooms in the hotel.
 */
public class PricesCommand implements Command {
    private final Hotel hotel;

    /**
     * Constructs a PricesCommand with the specified hotel.
     *
     * @param hotel the hotel whose room prices will be displayed
     */
    public PricesCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Executes the command to display the prices of all rooms in the hotel.
     * If there are no rooms available, it notifies the user.
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

        System.out.println("Room prices:");
        for (int roomNumber : roomNumbers) {
            Room room = hotel.getRoom(roomNumber);
            System.out.printf("Room %d: $%.2f per night%n", room.getRoomNumber(), room.getPrice());
        }
    }
}
