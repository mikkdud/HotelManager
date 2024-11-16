package pz1.hotelmanager;

public class Guest {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
