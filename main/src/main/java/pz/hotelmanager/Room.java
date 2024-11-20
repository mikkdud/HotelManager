package pz.hotelmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hotel room with attributes like room number, price, capacity, and occupancy status.
 * Provides functionality for checking in and out guests, calculating bills, and retrieving room details.
 */
public class Room {
    private final int roomNumber;
    private final double price;
    private final int capacity;
    private boolean occupied;
    private List<Guest> guests;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    /**
     * Constructs a Room with the given details.
     *
     * @param roomNumber the room number
     * @param price the price per night for the room
     * @param capacity the maximum number of guests the room can accommodate
     */
    public Room(int roomNumber, double price, int capacity) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.capacity = capacity;
        this.occupied = false;
        this.guests = new ArrayList<>();
    }

    /**
     * Checks in a list of guests into the room for a specified duration.
     *
     * @param guests the list of guests to check in
     * @param checkInDate the date of check-in
     * @param duration the duration of the stay in days
     * @throws IllegalStateException if the room is already occupied
     * @throws IllegalArgumentException if the number of guests exceeds the room's capacity
     */
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
     * Calculates the total bill for the stay based on the given check-out date.
     *
     * @param checkOutDate the actual check-out date
     * @return the total bill amount
     * @throws IllegalStateException if check-in or check-out date is missing
     */
    public double calculateBill(LocalDate checkOutDate) {
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalStateException("Cannot calculate bill without valid check-in or check-out date.");
        }
        long daysStayed = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return daysStayed * price;
    }

    /**
     * Checks out the guests from the room, resetting its state.
     */
    public void checkOut() {
        this.occupied = false;
        this.guests.clear();
        this.checkInDate = null;
        this.checkOutDate = null;
    }

    // Getters for room details
    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return  "---" +
                "\nRoom " + roomNumber + ":" +
                "\nprice: " + price +
                "\ncapacity: " + capacity +
                "\noccupied: " + occupied +
                "\nguests: " + guests +
                "\ncheckInDate: " + checkInDate +
                "\ncheckOutDate: " + checkOutDate
                + "\n---\n";
    }
}
