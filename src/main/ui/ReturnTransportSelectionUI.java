// package main.ui;
// import java.util.List;

// import java.awt.Color;
// import java.awt.Component;
// import java.awt.Font;
// import java.util.HashMap;
// import java.util.HashSet;

// import java.util.Map;

// import javax.swing.BoxLayout;
// import javax.swing.ButtonGroup;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.JRadioButton;

// public class ReturnTransportSelectionUI {

//     private JFrame frame;
//     private String currentUsername;
//     private String destination;
//     private String selectedBudget;
//     private String selectedTravelType;
//     private String startDate;
//     private String endDate;
//     private String selectedTransport;
//     private HashSet<String> selectedSeats;
//     private String selectedHotel;
//     private String roomType;
//     private int numberOfRooms;
//     private int totalCost;

//     private String selectedReturnTransport;
    
//     private static Map<String, List<String>> transportOptions = new HashMap<>();

//     static {
//         transportOptions.put("Cheap", List.of("Bus", "Train (Economy)", "Shared Van"));
//         transportOptions.put("Moderate", List.of("Train (AC)", "Economy Flight", "Private Van"));
//         transportOptions.put("Luxury", List.of("Business Flight", "Private Cab", "Luxury Train"));
//     }
    

//     public ReturnTransportSelectionUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType, String startDate, String endDate, String selectedTransport, HashSet<String> selectedSeats, String selectedHotel, String roomType, int numberOfRooms, int totalCost) {
//         this.frame = existingFrame;
//         this.currentUsername = username;
//         this.destination = destination;
//         this.selectedBudget = selectedBudget;
//         this.selectedTravelType = selectedTravelType;
//         this.startDate = startDate;
//         this.endDate = endDate;
//         this.selectedTransport = selectedTransport;
//         this.selectedSeats = selectedSeats;
//         this.selectedHotel = selectedHotel;
//         this.roomType = roomType;
//         this.numberOfRooms = numberOfRooms;
//         this.totalCost = totalCost;


//         frame.getContentPane().removeAll(); // Clear frame

//         // Load user details from the file
//         //loadUserDetails();

//         if (destination != null && selectedBudget != null && selectedTravelType != null && startDate != null && endDate != null) {
//             initializeUI(); // Initialize transport selection UI
//         } else {
//             JOptionPane.showMessageDialog(frame, "Error: Unable to retrieve user details. Please start over.");
//         }

//         frame.revalidate();
//         frame.repaint();
//     }

//     private void initializeUI() {
//         JPanel mainPanel = new JPanel();
//         mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//         mainPanel.setBackground(new Color(230, 240, 250));

//         JLabel titleLabel = new JLabel("Choose Your Transport");
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
//         titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//         mainPanel.add(titleLabel);

//         // Display transport options based on the selected budget
//         List<String> options = transportOptions.getOrDefault(selectedBudget, List.of("No options available"));
//         ButtonGroup transportGroup = new ButtonGroup();
//         for (String option : options) {
//             JRadioButton button = new JRadioButton(option);
//             button.setAlignmentX(Component.CENTER_ALIGNMENT);
//             transportGroup.add(button);
//             mainPanel.add(button);

//             button.addActionListener(e -> selectedReturnTransport = option);
//         }

//         JButton confirmButton = new JButton("Confirm Transport");
//         confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//         confirmButton.addActionListener(e -> {
//             if (selectedReturnTransport != null) {
//                 //saveTransportDetails(currentUsername);
//                 String input = JOptionPane.showInputDialog(frame, "Enter the number of travelers:");
//                 try {
//                     int numPeople = Integer.parseInt(input);
//                     new ReturnSeatSelectionUI(frame, currentUsername, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats, selectedHotel, roomType, numberOfRooms, numPeople, totalCost, selectedReturnTransport);

//                 } catch (NumberFormatException ex) {
//                     JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number.");
//                 }
//             } else {
//                 JOptionPane.showMessageDialog(frame, "Please select a transport option.");
//             }
//         });
        
    
//         mainPanel.add(confirmButton);

//         frame.add(mainPanel);
//     }
    
// }

package main.ui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ReturnTransportSelectionUI {
    private JFrame frame;
    private String currentUsername;
    private String destination;
    private String selectedBudget;
    private String selectedTravelType;
    private String startDate;
    private String endDate;
    private String selectedTransport;
    private HashSet<String> selectedSeats;
    private String selectedHotel;
    private String roomType;
    private int numberOfRooms;
    private int totalCost;
    private String selectedReturnTransport;

    private static final Map<String, List<String>> transportOptions = new HashMap<>();

    static {
        transportOptions.put("Cheap", List.of("Bus", "Train (Economy)", "Shared Van"));
        transportOptions.put("Moderate", List.of("Train (AC)", "Economy Flight", "Private Van"));
        transportOptions.put("Luxury", List.of("Business Flight", "Private Cab", "Luxury Train"));
    }

    public ReturnTransportSelectionUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType, String startDate, String endDate, String selectedTransport, HashSet<String> selectedSeats, String selectedHotel, String roomType, int numberOfRooms, int totalCost) {
        this.frame = existingFrame;
        this.currentUsername = username;
        this.destination = destination;
        this.selectedBudget = selectedBudget;
        this.selectedTravelType = selectedTravelType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.selectedTransport = selectedTransport;
        this.selectedSeats = selectedSeats;
        this.selectedHotel = selectedHotel;
        this.roomType = roomType;
        this.numberOfRooms = numberOfRooms;
        this.totalCost = totalCost;

        frame.getContentPane().removeAll();
        
        if (destination != null && selectedBudget != null && selectedTravelType != null && startDate != null && endDate != null) {
            initializeUI();
        } else {
            JOptionPane.showMessageDialog(frame, "Error: Unable to retrieve user details. Please start over.");
        }

        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(230, 240, 250));

        JLabel titleLabel = new JLabel("Choose Your Return Transport", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel transportPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        transportPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        transportPanel.setBackground(new Color(230, 240, 250));

        List<String> options = transportOptions.getOrDefault(selectedBudget, List.of("No options available"));
        for (String option : options) {
            JPanel optionPanel = new JPanel();
            optionPanel.setLayout(new BorderLayout());
            optionPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            optionPanel.setBackground(Color.WHITE);
            optionPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

            JLabel transportLabel = new JLabel(option, SwingConstants.CENTER);
            transportLabel.setFont(new Font("Arial", Font.BOLD, 16));
            transportLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            optionPanel.add(transportLabel, BorderLayout.CENTER);

            optionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    selectedReturnTransport = option;
                    highlightSelected(optionPanel, transportPanel);
                }
            });

            transportPanel.add(optionPanel);
        }
        mainPanel.add(transportPanel, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm Transport");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBackground(new Color(80, 170, 200));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        confirmButton.addActionListener(e -> {
            if (selectedReturnTransport != null) {
                String input = JOptionPane.showInputDialog(frame, "Enter the number of travelers:");
                try {
                    int numPeople = Integer.parseInt(input);
                    new ReturnSeatSelectionUI(frame, currentUsername, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats, selectedHotel, roomType, numberOfRooms, numPeople, totalCost, selectedReturnTransport);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a transport option.");
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(230, 240, 250));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(confirmButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
    }

    private void highlightSelected(JPanel selectedPanel, JPanel transportPanel) {
        for (Component comp : transportPanel.getComponents()) {
            if (comp instanceof JPanel) {
                comp.setBackground(Color.WHITE);
            }
        }
        selectedPanel.setBackground(new Color(173, 216, 230));
    }
}
