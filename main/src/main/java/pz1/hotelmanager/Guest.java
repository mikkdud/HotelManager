package pz1.hotelmanager;

public class Guest {
    private final String name;
    private final int id;
    static int idIterator = 1;


    public Guest(String name) {
        this.name = name;
        this.id = idIterator;
        idIterator ++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Guest: " +
                name +
                " (ID:" + id + ')';
    }
}
