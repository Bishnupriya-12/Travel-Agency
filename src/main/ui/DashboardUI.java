package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardUI {
    private JFrame frame;

    public DashboardUI(JFrame existingFrame) {
        this.frame = existingFrame; // Reuse the existing frame
        frame.getContentPane().removeAll(); // Clear the frame
        initializeUI(); // Initialize the Dashboard UI
        frame.revalidate(); // Refresh the frame
        frame.repaint(); // Update the changes
    }

    private void initializeUI() {
        // Panel for the dashboard UI
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(null);
        dashboardPanel.setBackground(new Color(230, 240, 250)); // Light blue background

        JLabel titleLabel = new JLabel("Travel Agency Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(300, 50, 350, 30);
        dashboardPanel.add(titleLabel);

        // Buttons for dashboard options
        JButton destinationButton = new JButton("Choose Destination");
        destinationButton.setBounds(400, 150, 200, 30);
        destinationButton.setBackground(new Color(100, 200, 100));
        destinationButton.setForeground(Color.WHITE);
        destinationButton.setFont(new Font("Arial", Font.BOLD, 14));
        dashboardPanel.add(destinationButton);

        JButton packageButton = new JButton("Choose Package");
        packageButton.setBounds(400, 200, 200, 30);
        packageButton.setBackground(new Color(100, 200, 100));
        packageButton.setForeground(Color.WHITE);
        packageButton.setFont(new Font("Arial", Font.BOLD, 14));
        dashboardPanel.add(packageButton);

        JButton bookingButton = new JButton("View Bookings");
        bookingButton.setBounds(400, 250, 200, 30);
        bookingButton.setBackground(new Color(100, 200, 100));
        bookingButton.setForeground(Color.WHITE);
        bookingButton.setFont(new Font("Arial", Font.BOLD, 14));
        dashboardPanel.add(bookingButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(400, 300, 200, 30);
        logoutButton.setBackground(new Color(200, 100, 100));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        dashboardPanel.add(logoutButton);

        // Add the dashboard panel to the frame
        frame.add(dashboardPanel);

        // Action listeners for buttons
        destinationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Choose Destination clicked!");
                // Implement the logic to choose destination
            }
        });

        packageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Choose Package clicked!");
                // Implement the logic to choose a package
            }
        });

        bookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "View Bookings clicked!");
                // Implement the logic to view bookings
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginUI(frame); // Redirect back to login screen
            }
        });
    }
}
