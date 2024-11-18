package pz1.hotelmanager;

import pz1.hotelmanager.utils.MyMap;

import java.time.LocalDate;
import java.util.List;

public class Hotel {
    private MyMap<Integer, Room> rooms = new MyMap<>();


    public void addRoom(int roomNumber, Room room) {
        rooms.put(roomNumber, room);
    }

    void checkIn(int roomNumber, List<Guest> guests, LocalDate checkInDate, int duration) {
        Room room = rooms.get(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room does not exist.");
        }
        if (room.isOccupied()) {
            throw new IllegalStateException("Room is already occupied.");
        }
        room.checkIn(guests, checkInDate, duration);
    }

    public Room getRoom(int roomNumber) {
        return rooms.get(roomNumber);
    }

    public List<Integer> getAllRoomNumbers() {
        return rooms.keys();
    }
}
