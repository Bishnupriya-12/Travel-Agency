// package main.ui;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;

// public class ChooseDestinationUI {
//     private JFrame frame;
//     private ArrayList<String> destinations;
//     private String currentUsername;

//     public ChooseDestinationUI(JFrame existingFrame, String username) {
//         this.frame = existingFrame; // Reuse the existing frame
//         this.currentUsername = username;
//         frame.getContentPane().removeAll(); // Clear the frame
//         initializeUI(); // Initialize the Choose Destination UI
//         frame.revalidate(); // Refresh the frame
//         frame.repaint(); // Update the changes
//     }

//     private void initializeUI() {
//         destinations = loadDestinations(); // Load destinations from the file

//         JPanel panel = new JPanel();
//         panel.setLayout(null);
//         panel.setBackground(new Color(230, 240, 250)); // Light blue background

//         JLabel titleLabel = new JLabel("Choose Your Destination");
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
//         titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//         titleLabel.setBounds(300, 50, 350, 30);
//         panel.add(titleLabel);

//         JComboBox<String> destinationComboBox = new JComboBox<>();
//         destinationComboBox.setBounds(350, 150, 250, 30);
//         for (String destination : destinations) {
//             destinationComboBox.addItem(destination);
//         }
//         panel.add(destinationComboBox);

//         JButton nextButton = new JButton("Next");
//         nextButton.setBounds(400, 220, 150, 30);
//         nextButton.setBackground(new Color(100, 200, 100));
//         nextButton.setForeground(Color.WHITE);
//         nextButton.setFont(new Font("Arial", Font.BOLD, 14));
//         panel.add(nextButton);

//         JButton backButton = new JButton("Back");
//         backButton.setBounds(400, 270, 150, 30);
//         backButton.setBackground(new Color(200, 100, 100));
//         backButton.setForeground(Color.WHITE);
//         backButton.setFont(new Font("Arial", Font.BOLD, 14));
//         panel.add(backButton);

//         frame.add(panel);

//         // Action listener for the "Next" button
//         nextButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String selectedDestination = (String) destinationComboBox.getSelectedItem();
//                 if (selectedDestination != null) {
//                     //JOptionPane.showMessageDialog(frame, "Destination selected: " + selectedDestination);
//                     new ChoosePackageUI(frame, selectedDestination, currentUsername); // Redirect to Choose Package UI
                    
//                 }
//             }
//         });

//         // Action listener for the "Back" button
//         backButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 new DashboardUI(frame,currentUsername); // Go back to the dashboard
//             }
//         });
//     }

//     private ArrayList<String> loadDestinations() {
//         ArrayList<String> destinations = new ArrayList<>();
//         try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/destinations.txt"))) {
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 // Split the line by ';' to extract the destination name
//                 String destination = line.split(";")[0].trim();
//                 if (!destinations.contains(destination)) {
//                     destinations.add(destination);
//                 }
//             }
//         } catch (IOException e) {
//             JOptionPane.showMessageDialog(frame, "Error loading destinations: " + e.getMessage());
//         }
//         return destinations;
//     }
    
// }

package main.ui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ChooseDestinationUI {
    private JFrame frame;
    private Set<String> destinations; // Use a Set to avoid duplicate destinations
    private String currentUsername;

    public ChooseDestinationUI(JFrame existingFrame, String username) {
        this.frame = existingFrame; // Reuse the existing frame
        this.currentUsername = username;
        frame.getContentPane().removeAll(); // Clear the frame
        initializeUI(); // Initialize the Choose Destination UI
        frame.revalidate(); // Refresh the frame
        frame.repaint(); // Update the changes
    }

    private void initializeUI() {
        destinations = loadDestinations(); // Load destinations from the file

        // Main panel
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(new Color(230, 240, 250)); // Light blue background
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Choose Your Destination", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Center panel (Dropdown + Image)
        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
        centerPanel.setBackground(new Color(230, 240, 250));

        // Dropdown menu
        JComboBox<String> destinationComboBox = new JComboBox<>();
        for (String destination : destinations) {
            destinationComboBox.addItem(destination); // Add each unique destination
        }
        destinationComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        destinationComboBox.setPreferredSize(new Dimension(300, 30));
        centerPanel.add(destinationComboBox, BorderLayout.NORTH);

        // Image display
        JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(400, 250));
        centerPanel.add(imageLabel, BorderLayout.CENTER);

        // Show the first destination's image by default
        if (!destinations.isEmpty()) {
            String firstDestination = destinations.iterator().next();
            updateImage(imageLabel, firstDestination);
        }

        // Listener for dropdown selection
        destinationComboBox.addActionListener(e -> {
            String selectedDestination = (String) destinationComboBox.getSelectedItem();
            if (selectedDestination != null) {
                updateImage(imageLabel, selectedDestination);
            }
        });

        panel.add(centerPanel, BorderLayout.CENTER);

        // Bottom panel (Buttons)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(230, 240, 250));

        JButton nextButton = createButton("Next", new Color(100, 200, 100));
        JButton backButton = createButton("Back", new Color(200, 100, 100));

        bottomPanel.add(backButton);
        bottomPanel.add(nextButton);
        
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);

        // Action listener for the "Next" button
        nextButton.addActionListener(e -> {
            String selectedDestination = (String) destinationComboBox.getSelectedItem();
            if (selectedDestination != null) {
                new ChoosePackageUI(frame, selectedDestination, currentUsername); // Redirect to Choose Package UI
            }
        });

        // Action listener for the "Back" button
        backButton.addActionListener(e -> new DashboardUI(frame, currentUsername)); // Go back to the dashboard
    }

    private Set<String> loadDestinations() {
        Set<String> destinationSet = new LinkedHashSet<>(); // Preserve insertion order
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/destinations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";"); // Split the line into parts
                if (parts.length >= 1) {
                    String destination = parts[0].trim(); // Get the destination name
                    destinationSet.add(destination); // Add to the Set
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading destinations: " + e.getMessage());
        }
        return destinationSet;
    }

    private void updateImage(JLabel imageLabel, String destination) {
        String imagePath = "src/resources/images/" + destination + ".jpg";
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(400, 250, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }
}
