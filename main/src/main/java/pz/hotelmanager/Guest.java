package pz.hotelmanager;

/**
 * Represents a guest in the hotel.
 * Each guest has a unique ID and a name.
 */
public class Guest {
    private final String name; // The name of the guest
    private final int id; // A unique ID for the guest
    static int idIterator = 1; // Static iterator for generating unique IDs

    /**
     * Creates a new guest with the specified name.
     * The ID is assigned automatically and incremented for each new guest.
     *
     * @param name the name of the guest
     */
    public Guest(String name) {
        this.name = name;
        this.id = idIterator;
        idIterator++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    /**
     * Returns a string representation of the guest, including their name and ID.
     *
     * @return a string in the format "Guest: [name] (ID:[id])"
     */
    @Override
    public String toString() {
        return "Guest: " +
                name +
                " (ID:" + id + ')';
    }
}
