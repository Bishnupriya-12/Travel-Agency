package main.models;

import java.util.ArrayList;
import java.util.List;

// Hotel Class
public class Hotel {
    private String name;
    private String location;
    private int baseCostPerNight;
    private List<Room> availableRooms;

    public Hotel(String name, String location, int baseCostPerNight) {
        this.name = name;
        this.location = location;
        this.baseCostPerNight = baseCostPerNight;
        this.availableRooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        availableRooms.add(room);
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getBaseCostPerNight() {
        return baseCostPerNight;
    }

    public List<Room> getAvailableRooms() {
        return availableRooms;
    }
}