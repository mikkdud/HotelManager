package pz1.hotelmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckInCommand implements Command {
    private Hotel hotel;

    public CheckInCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter room number:");
            int roomNumber = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter check-in date (YYYY-MM-DD):");
            String dateInput = scanner.nextLine();
            LocalDate checkInDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);

            System.out.println("Enter stay duration (in days):");
            int duration = Integer.parseInt(scanner.nextLine());

            List<Guest> guests = getGuestsFromInput(scanner);

            hotel.checkIn(roomNumber, guests, checkInDate, duration);
            System.out.println("Guest(s) checked in successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please try again.");
        } catch (Exception e) {
            System.out.println("Error during check-in: " + e.getMessage());
        }
    }

    private List<Guest> getGuestsFromInput(Scanner scanner) {
        List<Guest> guests = new ArrayList<>();

        System.out.println("Enter guest details:");
        while (true) {
            System.out.println("Enter guest name:");
            String name = scanner.nextLine();

            guests.add(new Guest(name)); // ID jest nadawane automatycznie

            System.out.println("Add another guest? (yes/no):");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                break;
            }
        }

        return guests;
    }
}
