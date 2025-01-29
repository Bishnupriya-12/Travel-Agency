package main.app;
//import main.ui.DashboardUI;
import main.ui.LoginUI;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Travel Agency!");

        // Create a single frame instance that will be shared across all UI screens
        JFrame frame = new JFrame("Travel Agency");
        frame.setSize(800, 500); // Set the window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application on close

        // Initialize and display the LoginUI in the same frame
        new LoginUI(frame);
        //new DashboardUI(frame,"Arpita");
        //new ChoosePackageUI(frame, null, null);
        //new ChooseDateUI(frame, null, null, null, null);
        //new ChooseTransportUI(frame, "Arpita");
        frame.setVisible(true); // Make the frame visible
    }
}
