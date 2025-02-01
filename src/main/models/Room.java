package main.models;

public abstract class Room {
    protected String roomType;
    protected int costPerNight;

    public Room(String roomType, int costPerNight) {
        this.roomType = roomType;
        this.costPerNight = costPerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getCostPerNight() {
        return costPerNight;
    }

    public abstract String getRoomDetails(); // Polymorphism (Overridden by subclasses)
}