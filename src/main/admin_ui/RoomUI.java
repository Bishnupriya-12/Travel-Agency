package main.admin_ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class RoomUI {
    private JFrame frame;
    private String selectedDestination;
    private String selectedHotel;
    private String budgetType;
    private List<String> rooms;
    private JComboBox<String> roomComboBox;
    private JTextField costField, customRoomField;
    private DefaultListModel<String> roomListModel;
    private JList<String> roomList;
    
    private static final String HOTEL_FILE = "src/resources/hotel.txt";

    public RoomUI(JFrame existingFrame, String destination, String hotel, String budget) {
        this.frame = existingFrame;
        this.selectedDestination = destination;
        this.selectedHotel = hotel;
        this.budgetType = budget;
        this.rooms = new ArrayList<>();
        
        frame.getContentPane().removeAll();
        initializeUI();
        loadRooms();
        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(250, 240, 250));

        JLabel titleLabel = new JLabel("Manage Room Types for " + selectedHotel);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(200, 20, 400, 30);
        panel.add(titleLabel);

        JLabel roomLabel = new JLabel("Choose Room Type:");
        roomLabel.setBounds(100, 70, 150, 25);
        panel.add(roomLabel);

        String[] predefinedRooms = {"Luxury", "Moderate", "Budget", "Custom"};
        roomComboBox = new JComboBox<>(predefinedRooms);
        roomComboBox.setBounds(250, 70, 150, 25);
        panel.add(roomComboBox);

        JLabel costLabel = new JLabel("Cost per Night:");
        costLabel.setBounds(100, 110, 150, 25);
        panel.add(costLabel);

        costField = new JTextField();
        costField.setBounds(250, 110, 150, 25);
        panel.add(costField);

        JLabel customRoomLabel = new JLabel("Room Name (if Custom):");
        customRoomLabel.setBounds(100, 150, 150, 25);
        customRoomLabel.setVisible(false);
        panel.add(customRoomLabel);

        customRoomField = new JTextField();
        customRoomField.setBounds(250, 150, 150, 25);
        customRoomField.setVisible(false);
        panel.add(customRoomField);

        roomComboBox.addActionListener(e -> {
            boolean isCustom = roomComboBox.getSelectedItem().equals("Custom");
            customRoomLabel.setVisible(isCustom);
            customRoomField.setVisible(isCustom);
        });

        JButton addRoomButton = new JButton("Add Room");
        addRoomButton.setBounds(100, 200, 150, 30);
        panel.add(addRoomButton);

        JButton deleteRoomButton = new JButton("Delete Room");
        deleteRoomButton.setBounds(270, 200, 150, 30);
        panel.add(deleteRoomButton);

        roomListModel = new DefaultListModel<>();
        roomList = new JList<>(roomListModel);
        JScrollPane roomScrollPane = new JScrollPane(roomList);
        roomScrollPane.setBounds(100, 250, 320, 150);
        panel.add(roomScrollPane);

        JButton finishButton = new JButton("Finish");
        finishButton.setBounds(100, 420, 150, 30);
        panel.add(finishButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(270, 420, 150, 30);
        panel.add(backButton);

        addRoomButton.addActionListener(e -> addRoom());
        deleteRoomButton.addActionListener(e -> deleteRoom());
        finishButton.addActionListener(e -> saveRoomData());
        backButton.addActionListener(e -> new HotelUI(frame, selectedDestination));

        frame.add(panel);
    }

    private void addRoom() {
        String roomType = customRoomField.isVisible() ? customRoomField.getText().trim() : (String) roomComboBox.getSelectedItem();
        String cost = costField.getText().trim();
        
        if (roomType.isEmpty() || cost.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter valid room details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String roomEntry = roomType + "-" + cost;
        roomListModel.addElement(roomEntry);
    }

    private void deleteRoom() {
        String selectedRoom = roomList.getSelectedValue();
        if (selectedRoom != null) {
            rooms.remove(selectedRoom);
            roomListModel.removeElement(selectedRoom);
        }
    }

    private void loadRooms() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HOTEL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(selectedDestination + ";" + budgetType + ";")) {
                    String[] parts = line.split(";", 3);
                    if (parts.length == 3) {
                        for (String hotelData : parts[2].split(", ")) {
                            if (hotelData.startsWith(selectedHotel)) {
                                String[] hotelParts = hotelData.split("\\.");
                                for (int i = 1; i < hotelParts.length; i++) {
                                    roomListModel.addElement(hotelParts[i]);
                                }
                                return;
                            }
                        }
                    }
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveRoomData() {
        List<String> lines = new ArrayList<>();
        boolean updated = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(HOTEL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(selectedDestination + ";" + budgetType + ";")) {
                    String[] parts = line.split(";", 3);
                    if (parts.length == 3) {
                        List<String> hotelList = new ArrayList<>();
                        for (String hotelData : parts[2].split(",")) {  // Ensure no extra spaces
                            if (hotelData.trim().startsWith(selectedHotel)) {
                                // Convert roomListModel to List<String>
                                List<String> rooms = new ArrayList<>();
                                for (int i = 0; i < roomListModel.size(); i++) {
                                    rooms.add(roomListModel.get(i));
                                }
    
                                // Update hotelData with room details
                                hotelData = selectedHotel + "." + String.join(".", rooms);
                                updated = true;
                            }
                            hotelList.add(hotelData.trim());
                        }
                        line = selectedDestination + ";" + budgetType + ";" + String.join(", ", hotelList);
                    }
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // If no existing entry was updated, add a new one
        if (!updated) {
            List<String> rooms = new ArrayList<>();
            for (int i = 0; i < roomListModel.size(); i++) {
                rooms.add(roomListModel.get(i));
            }
            lines.add(selectedDestination + ";" + budgetType + ";" + selectedHotel + "." + String.join(".", rooms));
        }
    
        // Write updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HOTEL_FILE))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}