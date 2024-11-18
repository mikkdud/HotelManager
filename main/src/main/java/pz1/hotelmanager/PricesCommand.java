package pz1.hotelmanager;

import java.util.List;

public class PricesCommand implements Command {
    private final Hotel hotel;

    public PricesCommand(Hotel hotel) {
        this.hotel = hotel;
    }

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
