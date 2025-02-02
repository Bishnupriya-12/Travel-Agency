package main.app;

import main.ui.LoginUI;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Travel Agency!");

        JFrame frame = new JFrame("Travel Agency");
        frame.setSize(800, 500); // Set the window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application on close

        
        new LoginUI(frame);
        
        frame.setVisible(true); // Make the frame visible
    }
}
