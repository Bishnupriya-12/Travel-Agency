package main.ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class HotelSelectionUI {
    private JFrame frame;
    private String username;
    private String destination;
    private String selectedBudget;
    private String selectedTravelType;
    private String startDate;
    private String endDate;
    private String selectedTransport;
    private HashSet<String> selectedSeats;
    private HashMap<String, Integer> hotels; // Hotel -> Cost per night
    private HashMap<String, Integer> roomTypeCosts; // Room Type -> Cost per night

    private JLabel totalCostLabel;
    //private JLabel costLabel;
    private JLabel hotelImageLabel;
    private JLabel roomTypeImageLabel;

    public HotelSelectionUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType, String startDate, String endDate, String selectedTransport, HashSet<String> selectedSeats) {
        this.frame = existingFrame;
        this.username = username;
        this.destination = destination;
        this.selectedBudget = selectedBudget;
        this.selectedTravelType = selectedTravelType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.selectedTransport = selectedTransport;
        this.selectedSeats = selectedSeats;

        loadHotels(); // Load hotels from destinations.txt

        frame.getContentPane().removeAll();
        initializeUI();
        frame.revalidate();
        frame.repaint();
    }

    private void loadHotels() {
        hotels = new HashMap<>();
        roomTypeCosts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/destinations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 4) continue;

                String dest = parts[0].trim();
                String budg = parts[1].trim();

                if (dest.equalsIgnoreCase(destination) && budg.equalsIgnoreCase(selectedBudget)) {
                    String[] hotelData = parts[2].split(", ");
                    for (String hotelInfo : hotelData) {
                        String[] hotelParts = hotelInfo.split("-");
                        String hotelName = hotelParts[0].trim();
                        int cost = Integer.parseInt(hotelParts[1].trim());
                        hotels.put(hotelName, cost);
                    }

                    String[] roomData = parts[3].split(", ");
                    for (String roomInfo : roomData) {
                        String[] roomParts = roomInfo.split("-");
                        String roomType = roomParts[0].trim();
                        int cost = Integer.parseInt(roomParts[1].trim());
                        roomTypeCosts.put(roomType, cost);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading hotels from file.");
        }
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 250, 255));

        JLabel titleLabel = new JLabel("Hotel Selection", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Hotel Selection
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Select a Hotel:"), gbc);

        JComboBox<String> hotelDropdown = new JComboBox<>(hotels.keySet().toArray(new String[0]));
        gbc.gridx = 1;
        contentPanel.add(hotelDropdown, gbc);

        hotelImageLabel = new JLabel();
        hotelImageLabel.setPreferredSize(new Dimension(150, 100));
        gbc.gridx = 2;
        contentPanel.add(hotelImageLabel, gbc);

        // Room Type Selection
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(new JLabel("Select Room Type:"), gbc);

        JComboBox<String> roomTypeDropdown = new JComboBox<>(roomTypeCosts.keySet().toArray(new String[0]));
        gbc.gridx = 1;
        contentPanel.add(roomTypeDropdown, gbc);

        roomTypeImageLabel = new JLabel();
        roomTypeImageLabel.setPreferredSize(new Dimension(150, 100));
        gbc.gridx = 2;
        contentPanel.add(roomTypeImageLabel, gbc);

        // Number of Rooms
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(new JLabel("Number of Rooms:"), gbc);

        JTextField numRoomsField = new JTextField();
        gbc.gridx = 1;
        contentPanel.add(numRoomsField, gbc);

        // Total Cost
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(new JLabel("Total Cost:"), gbc);

        totalCostLabel = new JLabel("$");
        totalCostLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        contentPanel.add(totalCostLabel, gbc);

        // Confirm Button
        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        confirmButton.addActionListener(_ -> {
            String selectedHotel = (String) hotelDropdown.getSelectedItem();
            String roomType = (String) roomTypeDropdown.getSelectedItem();
            String numRoomsText = numRoomsField.getText();

            if (selectedHotel == null || roomType == null || numRoomsText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields before confirming.");
            } else {
                try {
                    int numRooms = Integer.parseInt(numRoomsText);
                    int totalCost = numRooms * (hotels.get(selectedHotel) + roomTypeCosts.get(roomType));
                    JOptionPane.showMessageDialog(frame, "Hotel booked successfully!\nTotal Cost: $" + totalCost);
                    new ReturnTransportSelectionUI(frame,username, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats, selectedHotel, roomType, numRooms, totalCost);
                    //new ReturnTransportSelectionUI(frame,username, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats, selectedHotel, roomType, Integer.parseInt(numberOfRooms), Integer.parseInt(totalcost));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid number of rooms.");
                }
            }
        });
        contentPanel.add(confirmButton, gbc);

        // Listeners to update images and cost
        hotelDropdown.addActionListener(_ -> updateHotelImage((String) hotelDropdown.getSelectedItem()));
        roomTypeDropdown.addActionListener(_ -> updateRoomTypeImage((String) roomTypeDropdown.getSelectedItem()));
        numRoomsField.getDocument().addDocumentListener((SimpleDocumentListener) _ -> updateTotalCost(hotelDropdown, roomTypeDropdown, numRoomsField));

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
    }

    private void updateHotelImage(String selectedHotel) {
        if (selectedHotel != null) {
            // Replace with actual image paths for hotels
            // hotelImageLabel.setIcon(new ImageIcon("src/resources/hotels/" + selectedHotel + ".jpg"));
            hotelImageLabel.setIcon(new ImageIcon("src/resources/Sylhet.jpg"));
        }
    }

    private void updateRoomTypeImage(String selectedRoomType) {
        if (selectedRoomType != null) {
            // Replace with actual image paths for room types
            //roomTypeImageLabel.setIcon(new ImageIcon("src/resources/rooms/" + selectedRoomType + ".jpg"));
            roomTypeImageLabel.setIcon(new ImageIcon("src/resources/Dhaka.jpg"));
        }
    }

    private void updateTotalCost(JComboBox<String> hotelDropdown, JComboBox<String> roomTypeDropdown, JTextField numRoomsField) {
        String selectedHotel = (String) hotelDropdown.getSelectedItem();
        String selectedRoomType = (String) roomTypeDropdown.getSelectedItem();
        String numRoomsText = numRoomsField.getText();

        if (selectedHotel != null && selectedRoomType != null && !numRoomsText.isEmpty()) {
            try {
                int numRooms = Integer.parseInt(numRoomsText);
                int hotelCost = hotels.getOrDefault(selectedHotel, 0);
                int roomCost = roomTypeCosts.getOrDefault(selectedRoomType, 0);
                int totalCost = numRooms * (hotelCost + roomCost);
                totalCostLabel.setText("$" + totalCost);
            } catch (NumberFormatException e) {
                totalCostLabel.setText("Invalid input");
            }
        } else {
            totalCostLabel.setText("$0");
        }
    }

    private interface SimpleDocumentListener extends javax.swing.event.DocumentListener {
        void update(javax.swing.event.DocumentEvent e);

        @Override
        default void insertUpdate(javax.swing.event.DocumentEvent e) {
            update(e);
        }

        @Override
        default void removeUpdate(javax.swing.event.DocumentEvent e) {
            update(e);
        }

        @Override
        default void changedUpdate(javax.swing.event.DocumentEvent e) {
            update(e);
        }
    }
}