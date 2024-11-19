package pz.hotelmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int roomNumber;

    private final double price;

    private final int capacity;

    private boolean occupied;

    private List<Guest> guests;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

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

    public double calculateBill(LocalDate checkOutDate) {
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalStateException("Cannot calculate bill without valid check-in or check-out date.");
        }
        long daysStayed = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return daysStayed * price;
    }

    public void checkOut() {
        this.occupied = false;
        this.guests.clear();
        this.checkInDate = null;
        this.checkOutDate = null;
    }



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
