package pz1.hotelmanager;

import pz1.hotelmanager.utils.MyMap;

public class Hotel {
    private MyMap<Integer, Room> rooms = new MyMap<>();


    public void addRoom(int roomNumber, Room room) {
        rooms.put(roomNumber, room);
    }

    void checkIn() {


    }
}
