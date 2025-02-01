package main.models;

public class BudgetRoom extends Room {
    public BudgetRoom(int costPerNight) {
        super("Budget", costPerNight);
    }

    @Override
    public String getRoomDetails() {
        return "Budget Room with basic facilities.";
    }
}