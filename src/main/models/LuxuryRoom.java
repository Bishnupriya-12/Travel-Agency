package main.models;

public class LuxuryRoom extends Room {
    public LuxuryRoom(int costPerNight) {
        super("Luxury", costPerNight);
    }

    @Override
    public String getRoomDetails() {
        return "Luxury Room with premium services.";
    }
}