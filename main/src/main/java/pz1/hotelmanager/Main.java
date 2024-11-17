package pz1.hotelmanager;

public class Main {
    public static void main(String[] args) {
        Room room = new Room(101, 60, 3);
        System.out.println(room.toString());
        Guest Maciek = new Guest("Maciek");
        System.out.println(Maciek.toString());

    }
}