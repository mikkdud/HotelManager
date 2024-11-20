package pz.hotelmanager.commands;

import pz.hotelmanager.Hotel;
import pz.hotelmanager.xlsxutils.HotelFileWriter;

/**
 * Command to save the current state of the hotel to a specified file.
 */
public class SaveCommand implements Command {
    private final Hotel hotel;
    private final String filePath;

    /**
     * Constructs a SaveCommand with the specified hotel and file path.
     *
     * @param hotel    the hotel whose data will be saved
     * @param filePath the file path where the hotel data will be saved
     */
    public SaveCommand(Hotel hotel, String filePath) {
        this.hotel = hotel;
        this.filePath = filePath;
    }

    /**
     * Executes the command to save the hotel's state to the specified file.
     * If an error occurs during the save process, an error message is printed.
     *
     * @param args not used in this command
     */
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
