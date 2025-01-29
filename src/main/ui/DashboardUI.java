// package main.ui;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class DashboardUI {
//     private JFrame frame;
//     private String username;

//     public DashboardUI(JFrame existingFrame, String username) {
//         this.frame = existingFrame; // Reuse the existing frame
//         this.username = username;
//         frame.getContentPane().removeAll(); // Clear the frame
//         initializeUI(); // Initialize the Dashboard UI
//         frame.revalidate(); // Refresh the frame
//         frame.repaint(); // Update the changes
//     }

//     public DashboardUI(JFrame existingFrame) {
//         this.frame = existingFrame; // Reuse the existing frame
//         this.username = null;
//         frame.getContentPane().removeAll(); // Clear the frame
//         initializeUI(); // Initialize the Dashboard UI
//         frame.revalidate(); // Refresh the frame
//         frame.repaint(); // Update the changes
//     }

//     private void initializeUI() {
//         // Panel for the dashboard UI
//         JPanel dashboardPanel = new JPanel();
//         dashboardPanel.setLayout(null);
//         dashboardPanel.setBackground(new Color(230, 240, 250)); // Light blue background

//         JLabel titleLabel = new JLabel("Travel Agency Dashboard " + username);
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
//         titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//         titleLabel.setBounds(300, 50, 350, 30);
//         dashboardPanel.add(titleLabel);

//         // Buttons for dashboard options
//         JButton destinationButton = new JButton("Choose Destination");
//         destinationButton.setBounds(400, 150, 200, 30);
//         destinationButton.setBackground(new Color(100, 200, 100));
//         destinationButton.setForeground(Color.WHITE);
//         destinationButton.setFont(new Font("Arial", Font.BOLD, 14));
//         dashboardPanel.add(destinationButton);

//         JButton bookingButton = new JButton("View Bookings");
//         bookingButton.setBounds(400, 250, 200, 30);
//         bookingButton.setBackground(new Color(100, 200, 100));
//         bookingButton.setForeground(Color.WHITE);
//         bookingButton.setFont(new Font("Arial", Font.BOLD, 14));
//         dashboardPanel.add(bookingButton);

//         JButton profileButton = new JButton("View profiles");
//         profileButton.setBounds(400, 300, 200, 30);
//         profileButton.setBackground(new Color(100, 200, 100));
//         profileButton.setForeground(Color.WHITE);
//         profileButton.setFont(new Font("Arial", Font.BOLD, 14));
//         dashboardPanel.add(profileButton);

//         JButton logoutButton = new JButton("Logout");
//         logoutButton.setBounds(400, 350, 200, 30);
//         logoutButton.setBackground(new Color(200, 100, 100));
//         logoutButton.setForeground(Color.WHITE);
//         logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
//         dashboardPanel.add(logoutButton);

//         // Add the dashboard panel to the frame
//         frame.add(dashboardPanel);

//         // Action listeners for buttons
//         destinationButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 JOptionPane.showMessageDialog(frame, "Choose Destination clicked!");
//                 // Implement the logic to choose destination
//                 new ChooseDestinationUI(frame,username);
//             }
//         });

//         bookingButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 JOptionPane.showMessageDialog(frame, "View Bookings clicked!");
//                 // Implement the logic to view bookings
//                 new ViewBookingsUI(frame,username);
//             }
//         });

//         profileButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                new ProfileUI(frame, username);
//             }
//         });

//         logoutButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
//                 if (confirm == JOptionPane.YES_OPTION) {
//                     new LoginUI(frame); // Redirect to Login UI
//                 }
//             }
//         });
//     }
// }

package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardUI {
    private JFrame frame;
    private String username;
    private Timer imageSliderTimer;
    private int currentImageIndex = 0;

    public DashboardUI(JFrame existingFrame, String username) {
        this.frame = existingFrame; // Reuse the existing frame
        this.username = username;
        frame.getContentPane().removeAll(); // Clear the frame
        initializeUI(); // Initialize the Dashboard UI
        frame.revalidate(); // Refresh the frame
        frame.repaint(); // Update the changes
    }

    private void initializeUI() {
        frame.setLayout(new BorderLayout());

        // Side menu for navigation
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new GridLayout(6, 1, 20, 10));
        sideMenu.setBackground(new Color(70, 130, 180)); // Steel Blue
        sideMenu.setPreferredSize(new Dimension(200, frame.getHeight()));

        JButton destinationButton = createMenuButton("Choose Destination");
        JButton bookingButton = createMenuButton("View Bookings");
        JButton profileButton = createMenuButton("View Profiles");
        JButton logoutButton = createMenuButton("Logout");

        sideMenu.add(Box.createVerticalGlue());
        sideMenu.add(destinationButton);
        sideMenu.add(bookingButton);
        sideMenu.add(profileButton);
        //sideMenu.add(Box.createVerticalGlue());
        sideMenu.add(logoutButton);
        sideMenu.add(Box.createVerticalGlue());

        

        // Main content area
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        mainPanel.add(sideMenu, BorderLayout.WEST);
        // Sliding image panel
        JPanel imageSliderPanel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageSliderPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(imageSliderPanel, BorderLayout.CENTER);

        // Load and display images
        String[] imagePaths = {
            "src/resources/images/Sylhet.jpg",
            "src/resources/images/Dhaka.jpg",
            "src/resources/images/Coxbazar.jpg"
        };
        startImageSlider(imageLabel, imagePaths);

        frame.add(mainPanel, BorderLayout.CENTER);

        // Action listeners for buttons
        destinationButton.addActionListener(e -> new ChooseDestinationUI(frame, username));
        bookingButton.addActionListener(e -> new ViewBookingsUI(frame, username));
        profileButton.addActionListener(e -> new ProfileUI(frame, username));
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                stopImageSlider(); // Stop the slider when logging out
                new LoginUI(frame);
            }
        });
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return button;
    }

    private void startImageSlider(JLabel imageLabel, String[] imagePaths) {
        imageSliderTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon icon = new ImageIcon(imagePaths[currentImageIndex]);
                Image scaledImage = icon.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
            }
        });
        imageSliderTimer.start();
    }

    private void stopImageSlider() {
        if (imageSliderTimer != null && imageSliderTimer.isRunning()) {
            imageSliderTimer.stop();
        }
    }
}
