// package main.ui;

// import javax.swing.*;
// import java.awt.*;
// import java.util.*;
// import java.util.List;


// public class ChooseTransportUI {
//     private JFrame frame;
//     private String currentUsername;
//     private String destination;
//     private String selectedBudget;
//     private String selectedTravelType;
//     private String startDate;
//     private String endDate;
//     private String selectedTransport;

//     private static Map<String, List<String>> transportOptions = new HashMap<>();

//     static {
//         transportOptions.put("Cheap", List.of("Bus", "Train (Economy)", "Shared Van"));
//         transportOptions.put("Moderate", List.of("Train (AC)", "Economy Flight", "Private Van"));
//         transportOptions.put("Luxury", List.of("Business Flight", "Private Cab", "Luxury Train"));
//     }
    

//     public ChooseTransportUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType, String startDate, String endDate) {
//         this.frame = existingFrame;
//         this.currentUsername = username;
//         this.destination = destination;
//         this.selectedBudget = selectedBudget;
//         this.selectedTravelType = selectedTravelType;
//         this.startDate = startDate;
//         this.endDate = endDate;

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

//             button.addActionListener(e -> selectedTransport = option);
//         }

//         JButton confirmButton = new JButton("Confirm Transport");
//         confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//         confirmButton.addActionListener(e -> {
//             if (selectedTransport != null) {
//                 //saveTransportDetails(currentUsername);
//                 String input = JOptionPane.showInputDialog(frame, "Enter the number of travelers:");
//                 try {
//                     int numPeople = Integer.parseInt(input);
//                     new SeatSelectionUI(frame, currentUsername, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, numPeople);
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

//     // private void loadUserDetails() {
//     //     try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/user_bookings.txt"))) {
//     //         String line;
//     //         boolean userFound = false;

//     //         while ((line = reader.readLine()) != null) {
//     //             if (line.startsWith("Username: ") && line.substring(10).trim().equals(currentUsername)) {
//     //                 userFound = true;
//     //             }

//     //             if (userFound) {
//     //                 if (line.startsWith("Destination: ")) {
//     //                     destination = line.substring(12).trim();
//     //                 } else if (line.startsWith("selectedBudget ")) {
//     //                     selectedBudget = line.substring(15).trim();
//     //                 } else if (line.startsWith("selectedTravelType ")) {
//     //                     selectedTravelType = line.substring(19).trim();
//     //                 } else if (line.startsWith("Start Date: ")) {
//     //                     startDate = line.substring(12).trim();
//     //                 } else if (line.startsWith("End Date: ")) {
//     //                     endDate = line.substring(10).trim();
//     //                 } else if (line.startsWith("----------------------------")) {
//     //                     break; // End of user's details
//     //                 }
//     //             }
//     //         }

//     //         if (!userFound) {
//     //             JOptionPane.showMessageDialog(frame, "Error: User details not found.");
//     //         }
//     //     } catch (IOException e) {
//     //         e.printStackTrace();
//     //         JOptionPane.showMessageDialog(frame, "Error reading user details file.");
//     //     }
//     // }

// //    private void saveTransportDetails(String username) {
// //     File file = new File("src/resources/user_bookings.txt");
// //     StringBuilder updatedContent = new StringBuilder();
// //     boolean isUserSection = false;

// //     try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
// //         String line;
// //         while ((line = reader.readLine()) != null) {
// //             if (line.startsWith("Username: ")) {
// //                 isUserSection = line.equals("Username: " + username);
// //             }

// //             if (isUserSection && line.startsWith("----------------------------")) {
// //                 // Insert transport details before the separator for the specific user
// //                 updatedContent.append("Transport: ").append(selectedTransport).append("\n");
// //                 isUserSection = false; // Stop adding for this user
// //             }

// //             updatedContent.append(line).append("\n");
// //         }
// //     } catch (IOException e) {
// //         e.printStackTrace();
// //     }

// //     // Write the updated content back to the file
// //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
// //         writer.write(updatedContent.toString());
// //     } catch (IOException e) {
// //         e.printStackTrace();
// //     }
// // }

// }

package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class ChooseTransportUI {
    private JFrame frame;
    private String currentUsername;
    private String destination;
    private String selectedBudget;
    private String selectedTravelType;
    private String startDate;
    private String endDate;
    private String selectedTransport;
    private JPanel lastSelectedPanel;

    private static final Map<String, List<String>> transportOptions = new HashMap<>();

    static {
        transportOptions.put("Cheap", List.of("Bus", "Train (Economy)", "Shared Van"));
        transportOptions.put("Moderate", List.of("Train (AC)", "Economy Flight", "Private Van"));
        transportOptions.put("Luxury", List.of("Business Flight", "Private Cab", "Luxury Train"));
    }

    public ChooseTransportUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType, String startDate, String endDate) {
        this.frame = existingFrame;
        this.currentUsername = username;
        this.destination = destination;
        this.selectedBudget = selectedBudget;
        this.selectedTravelType = selectedTravelType;
        this.startDate = startDate;
        this.endDate = endDate;

        frame.getContentPane().removeAll(); // Clear frame

        if (destination != null && selectedBudget != null && selectedTravelType != null && startDate != null && endDate != null) {
            initializeUI(); // Initialize transport selection UI
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

        // Header Section
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(80, 170, 200));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Choose Your Transport");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(titleLabel);

        JLabel subTitleLabel = new JLabel("Destination: " + destination + " | Budget: " + selectedBudget + " | Travel Type: " + selectedTravelType);
        subTitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subTitleLabel.setForeground(Color.WHITE);
        subTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(subTitleLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Transport Options Section
        JPanel transportPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        transportPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        transportPanel.setBackground(new Color(230, 240, 250));

        List<String> options = transportOptions.getOrDefault(selectedBudget, List.of("No options available"));

        for (String option : options) {
            JPanel optionPanel = new JPanel();
            optionPanel.setLayout(new BorderLayout());
            optionPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            optionPanel.setBackground(Color.WHITE);
            optionPanel.setPreferredSize(new Dimension(200, 50));
            
            optionPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (lastSelectedPanel != null) {
                        lastSelectedPanel.setBackground(Color.WHITE);
                    }
                    optionPanel.setBackground(new Color(173, 216, 230)); // Light blue to indicate selection
                    lastSelectedPanel = optionPanel;
                    selectedTransport = option;
                }
            });

            // Transport description
            JLabel transportLabel = new JLabel(option);
            transportLabel.setFont(new Font("Arial", Font.BOLD, 16));
            transportLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            optionPanel.add(transportLabel, BorderLayout.CENTER);

            transportPanel.add(optionPanel);
        }

        mainPanel.add(transportPanel, BorderLayout.CENTER);

        // Footer Section
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.setBackground(new Color(230, 240, 250));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton confirmButton = new JButton("Confirm Transport");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBackground(new Color(80, 170, 200));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        confirmButton.addActionListener(e -> {
            if (selectedTransport != null) {
                String input = JOptionPane.showInputDialog(frame, "Enter the number of travelers:");
                try {
                    int numPeople = Integer.parseInt(input);
                    new SeatSelectionUI(frame, currentUsername, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, numPeople);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a transport option.");
            }
        });
        footerPanel.add(confirmButton);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
    }
}
