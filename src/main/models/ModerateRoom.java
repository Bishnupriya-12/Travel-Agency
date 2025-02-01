package main.models;

public class ModerateRoom extends Room {
    public ModerateRoom(int costPerNight) {
        super("Moderate", costPerNight);
    }

    @Override
    public String getRoomDetails() {
        return "Moderate Room with essential amenities.";
    }
}