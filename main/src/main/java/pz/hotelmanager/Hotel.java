package pz.hotelmanager;

import pz.hotelmanager.utils.MyMap;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a hotel that manages multiple rooms.
 * Provides functionality to add rooms, check guests into rooms, and retrieve room details.
 */
public class Hotel {
    private final MyMap<Integer, Room> rooms = new MyMap<>();

    /**
     * Adds a room to the hotel.
     *
     * @param roomNumber the unique number identifying the room
     * @param room the Room object to be added
     */
    public void addRoom(int roomNumber, Room room) {
        rooms.put(roomNumber, room);
    }

    /**
     * Checks in a group of guests into a specified room for a given duration.
     *
     * @param roomNumber the number of the room to check into
     * @param guests the list of guests to check in
     * @param checkInDate the date of check-in
     * @param duration the duration of the stay in days
     * @throws IllegalArgumentException if the room does not exist
     * @throws IllegalStateException if the room is already occupied
     */
    public void checkIn(int roomNumber, List<Guest> guests, LocalDate checkInDate, int duration) {
        Room room = rooms.get(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room does not exist.");
        }
        if (room.isOccupied()) {
            throw new IllegalStateException("Room is already occupied.");
        }
        room.checkIn(guests, checkInDate, duration);
    }

    /**
     * Retrieves a room by its number.
     *
     * @param roomNumber the number of the room to retrieve
     * @return the Room object if found, or null if the room does not exist
     */
    public Room getRoom(int roomNumber) {
        return rooms.get(roomNumber);
    }

    /**
     * Retrieves a list of all room numbers in the hotel.
     *
     * @return a list of integers representing the room numbers
     */
    public List<Integer> getAllRoomNumbers() {
        return rooms.keys();
    }
}
