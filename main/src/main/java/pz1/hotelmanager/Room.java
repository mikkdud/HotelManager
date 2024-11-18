package pz1.hotelmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room in a hotel, with information about its number, price, capacity, occupancy status, and guests.
 */
public class Room {
    /**
     * The number of the room.
     */
    private int roomNumber;

    /**
     * The price per night for the room.
     */
    private double price;

    /**
     * The maximum number of guests the room can accommodate.
     */
    private int capacity;

    /**
     * Indicates whether the room is currently occupied.
     */
    private boolean occupied;

    /**
     * A list of guests currently staying in the room.
     */
    private List<Guest> guests;

    /**
     * The check-in date for the current booking.
     */
    private LocalDate checkInDate;

    /**
     * The check-out date for the current booking.
     */
    private LocalDate checkOutDate;

    /**
     * Constructs a new Room with the specified room number, price, and capacity.
     *
     * @param roomNumber The number of the room.
     * @param price The price per night for the room.
     * @param capacity The maximum number of guests the room can accommodate.
     */
    public Room(int roomNumber, double price, int capacity) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.capacity = capacity;
        this.occupied = false;
        this.guests = new ArrayList<>();
    }

    public void checkIn(List<Guest> guests, LocalDate checkInDate, int duration) {
        if (occupied) {
            throw new IllegalStateException("Room is already occupied.");
        }
        if (guests.size() > capacity) {
            throw new IllegalArgumentException("Too many guests for this room.");
        }

        this.guests = new ArrayList<>(guests);
        this.checkInDate = checkInDate;
        this.checkOutDate = checkInDate.plusDays(duration);
        this.occupied = true;
    }


    /**
     * Returns the room number.
     *
     * @return The number of the room.
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Returns the price per night for the room.
     *
     * @return The price of the room.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Indicates whether the room is currently occupied.
     *
     * @return {@code true} if the room is occupied, {@code false} otherwise.
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Returns the maximum number of guests the room can accommodate.
     *
     * @return The capacity of the room.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the list of guests currently staying in the room.
     *
     * @return A list of guests.
     */
    public List<Guest> getGuests() {
        return guests;
    }

    /**
     * Returns the check-in date for the current booking.
     *
     * @return The check-in date.
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    /**
     * Returns the check-out date for the current booking.
     *
     * @return The check-out date.
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Returns a string representation of the room, including its number, price, capacity,
     * occupancy status, guests, check-in date, and check-out date.
     *
     * @return A string representation of the room.
     */
    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", price=" + price +
                ", capacity=" + capacity +
                ", occupied=" + occupied +
                ", guests=" + guests +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
