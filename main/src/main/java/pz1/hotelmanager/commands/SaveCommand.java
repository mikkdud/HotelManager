package pz1.hotelmanager.commands;

import pz1.hotelmanager.Hotel;
import pz1.hotelmanager.xlsxutils.HotelFileWriter;

public class SaveCommand implements Command {
    private final Hotel hotel;
    private final String filePath;

    public SaveCommand(Hotel hotel, String filePath) {
        this.hotel = hotel;
        this.filePath = filePath;
    }

    @Override
    public void execute(String[] args) {
        try {
            HotelFileWriter.writeToXLSX(hotel, filePath);
            System.out.println("Hotel data saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving hotel data: " + e.getMessage());
        }
    }
}
