package main.admin_ui;

import javax.swing.*;

import main.models.Hotel;
import main.models.Room;

import java.awt.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminHotelSetupUI {
    private JFrame frame;
    private HashMap<String, List<Hotel>> destinations;
    
    public AdminHotelSetupUI(JFrame frame) {
        this.frame = frame;
        this.destinations = new HashMap<>();
        showDestinationUI();
    }

    private void showDestinationUI() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel destLabel = new JLabel("Destination Name:");
        JTextField destField = new JTextField();
        JButton uploadButton = new JButton("Upload Image");
        JButton nextButton = new JButton("Next");
        JButton backButton = new JButton("Back");

        panel.add(destLabel);
        panel.add(destField);
        panel.add(uploadButton);
        panel.add(nextButton);
        panel.add(backButton);

        uploadButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Feature to upload image not implemented yet."));
        
        nextButton.addActionListener(e -> {
            String destination = destField.getText();
            if (!destination.isEmpty()) {
                destinations.put(destination, new ArrayList<>());
                showHotelUI(destination);
            }
        });
        
        backButton.addActionListener(e -> frame.dispose());
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showHotelUI(String destination) {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel hotelLabel = new JLabel("Hotel Name:");
        JTextField hotelField = new JTextField();
        JLabel costLabel = new JLabel("Base Cost Per Night:");
        JTextField costField = new JTextField();
        JButton addHotelButton = new JButton("Add Hotel");
        JButton nextButton = new JButton("Next");
        JButton backButton = new JButton("Back");

        panel.add(hotelLabel);
        panel.add(hotelField);
        panel.add(costLabel);
        panel.add(costField);
        panel.add(addHotelButton);
        panel.add(nextButton);
        panel.add(backButton);

        addHotelButton.addActionListener(e -> {
            String hotelName = hotelField.getText();
            String costText = costField.getText();
            if (!hotelName.isEmpty() && !costText.isEmpty()) {
                try {
                    int cost = Integer.parseInt(costText);
                    Hotel hotel = new Hotel(hotelName, destination, cost);
                    destinations.get(destination).add(hotel);
                    hotelField.setText("");
                    costField.setText("");
                    JOptionPane.showMessageDialog(frame, "Hotel Added Successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid number for cost");
                }
            }
        });

        nextButton.addActionListener(e -> {
            if (!destinations.get(destination).isEmpty()) {
                showRoomUI(destination);
            }
        });

        backButton.addActionListener(e -> showDestinationUI());
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showRoomUI(String destination) {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel hotelLabel = new JLabel("Select Hotel:");
        JComboBox<String> hotelDropdown = new JComboBox<>(destinations.get(destination).stream().map(Hotel::getName).toArray(String[]::new));
        JLabel roomTypeLabel = new JLabel("Room Type:");
        JTextField roomTypeField = new JTextField();
        JLabel costLabel = new JLabel("Cost Per Night:");
        JTextField costField = new JTextField();
        JButton addRoomButton = new JButton("Add Room");
        JButton saveButton = new JButton("Save & Exit");
        JButton backButton = new JButton("Back");

        panel.add(hotelLabel);
        panel.add(hotelDropdown);
        panel.add(roomTypeLabel);
        panel.add(roomTypeField);
        panel.add(costLabel);
        panel.add(costField);
        panel.add(addRoomButton);
        panel.add(saveButton);
        panel.add(backButton);

        addRoomButton.addActionListener(e -> {
            String selectedHotel = (String) hotelDropdown.getSelectedItem();
            String roomType = roomTypeField.getText();
            String costText = costField.getText();
            if (selectedHotel != null && !roomType.isEmpty() && !costText.isEmpty()) {
                try {
                    int cost = Integer.parseInt(costText);
                    for (Hotel hotel : destinations.get(destination)) {
                        if (hotel.getName().equals(selectedHotel)) {
                            hotel.addRoom(new Room(roomType, cost) {
                                @Override
                                public String getRoomDetails() {
                                    return "Custom room";
                                }
                            });
                            break;
                        }
                    }
                    roomTypeField.setText("");
                    costField.setText("");
                    JOptionPane.showMessageDialog(frame, "Room Added Successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid number for cost");
                }
            }
        });

        saveButton.addActionListener(e -> saveToFile());
        
        backButton.addActionListener(e -> showHotelUI(destination));
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("hotels.txt"))) {
            for (String destination : destinations.keySet()) {
                writer.write(destination + "\n");
                for (Hotel hotel : destinations.get(destination)) {
                    writer.write(hotel.getName() + "-" + hotel.getBaseCostPerNight() + "\n");
                    for (Room room : hotel.getAvailableRooms()) {
                        writer.write(room.getRoomType() + "-" + room.getCostPerNight() + "\n");
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "Data Saved Successfully");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error Saving Data");
        }
    }
}