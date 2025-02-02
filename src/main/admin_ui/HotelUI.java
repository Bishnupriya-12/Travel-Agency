
package main.admin_ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HotelUI {
    private JFrame frame;
    private JComboBox<String> hotelComboBox, budgetComboBox;
    private JTextField hotelNameField, baseCostField;
    private JButton addHotelButton, updateHotelButton, deleteHotelButton, nextButton, backButton, skipButton;
    private String selectedDestination;
    private Map<String, List<String>> budgetHotels; // Stores hotels under each budget
    private static final String HOTEL_FILE = "src/resources/hotel.txt";

    public HotelUI(JFrame existingFrame, String destination) {
        this.frame = existingFrame;
        this.selectedDestination = destination;
        this.budgetHotels = new LinkedHashMap<>(); // Preserves order
        frame.getContentPane().removeAll();
        initializeUI();
        loadHotels();
        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(250, 240, 250));

        JLabel titleLabel = new JLabel("Manage Hotels in " + selectedDestination);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(200, 20, 400, 30);
        panel.add(titleLabel);

        JLabel budgetLabel = new JLabel("Select Budget:");
        budgetLabel.setBounds(200, 60, 100, 25);
        panel.add(budgetLabel);

        budgetComboBox = new JComboBox<>(new String[]{"Luxury", "Moderate", "Cheap"});
        budgetComboBox.setBounds(310, 60, 150, 25);
        budgetComboBox.addActionListener(e -> loadHotelsForBudget());
        panel.add(budgetComboBox);

        hotelComboBox = new JComboBox<>();
        hotelComboBox.setBounds(200, 100, 200, 30);
        hotelComboBox.addActionListener(e -> populateFieldsForSelectedHotel());
        panel.add(hotelComboBox);

        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        hotelNameLabel.setBounds(200, 140, 100, 25);
        panel.add(hotelNameLabel);

        hotelNameField = new JTextField();
        hotelNameField.setBounds(310, 140, 150, 25);
        panel.add(hotelNameField);

        JLabel baseCostLabel = new JLabel("Base Cost:");
        baseCostLabel.setBounds(200, 180, 100, 25);
        panel.add(baseCostLabel);

        baseCostField = new JTextField();
        baseCostField.setBounds(310, 180, 150, 25);
        panel.add(baseCostField);

        addHotelButton = new JButton("Add Hotel");
        addHotelButton.setBounds(50, 230, 120, 30);
        addHotelButton.addActionListener(e -> addHotel());
        panel.add(addHotelButton);

        updateHotelButton = new JButton("Update Hotel");
        updateHotelButton.setBounds(180, 230, 120, 30);
        updateHotelButton.addActionListener(e -> updateHotel());
        panel.add(updateHotelButton);

        deleteHotelButton = new JButton("Delete Hotel");
        deleteHotelButton.setBounds(310, 230, 120, 30);
        deleteHotelButton.addActionListener(e -> deleteHotel());
        panel.add(deleteHotelButton);

        nextButton = new JButton("Next");
        nextButton.setBounds(440, 230, 100, 30);
        nextButton.addActionListener(e -> {
            saveHotelsToFile();
            String selectedHotel = (String) hotelComboBox.getSelectedItem();
            String selectedBudget = (String) budgetComboBox.getSelectedItem();

            if (selectedHotel != null) {
                new RoomUI(frame, selectedDestination, selectedHotel, selectedBudget);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a hotel", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(nextButton);

        backButton = new JButton("Back");
        backButton.setBounds(200, 280, 100, 30);
        backButton.addActionListener(e -> new DestinationUI(frame));
        panel.add(backButton);

        skipButton = new JButton("Skip");
        skipButton.setBounds(310, 280, 100, 30);
        skipButton.addActionListener(e -> new RoomUI(frame, selectedDestination, null, null));
        panel.add(skipButton);

        frame.add(panel);
    }

    private void loadHotels() {
        budgetHotels.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(HOTEL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3 && parts[0].equalsIgnoreCase(selectedDestination)) {
                    budgetHotels.put(parts[1], new ArrayList<>(List.of(parts[2].split(", "))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadHotelsForBudget();
    }

    private void loadHotelsForBudget() {
        hotelComboBox.removeAllItems();
        String selectedBudget = (String) budgetComboBox.getSelectedItem();
        List<String> hotels = budgetHotels.getOrDefault(selectedBudget, new ArrayList<>());
        for (String hotel : hotels) {
            hotelComboBox.addItem(hotel);
        }
    }

    private void populateFieldsForSelectedHotel() {
        String selectedHotel = (String) hotelComboBox.getSelectedItem();
        if (selectedHotel != null) {
            String[] parts = selectedHotel.split("-");
            hotelNameField.setText(parts[0]);
            baseCostField.setText(parts.length > 1 ? parts[1] : "");
        }
    }

    private void addHotel() {
        String hotelName = hotelNameField.getText().trim();
        String baseCost = baseCostField.getText().trim();
        String selectedBudget = (String) budgetComboBox.getSelectedItem();

        if (hotelName.isEmpty() || baseCost.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Hotel name and base cost cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String hotelEntry = hotelName + "-" + baseCost;
        budgetHotels.computeIfAbsent(selectedBudget, k -> new ArrayList<>()).add(hotelEntry);
        loadHotelsForBudget();
    }

    private void updateHotel() {
        String selectedHotel = (String) hotelComboBox.getSelectedItem();
        if (selectedHotel == null) return;

        String newHotelName = hotelNameField.getText().trim();
        String newBaseCost = baseCostField.getText().trim();
        String selectedBudget = (String) budgetComboBox.getSelectedItem();

        if (newHotelName.isEmpty() || newBaseCost.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Fields cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> hotels = budgetHotels.get(selectedBudget);
        if (hotels != null) {
            int index = hotels.indexOf(selectedHotel);
            if (index != -1) {
                hotels.set(index, newHotelName + "-" + newBaseCost);
                loadHotelsForBudget();
            }
        }
    }

    private void deleteHotel() {
        String selectedHotel = (String) hotelComboBox.getSelectedItem();
        String selectedBudget = (String) budgetComboBox.getSelectedItem();

        if (selectedHotel != null) {
            budgetHotels.get(selectedBudget).remove(selectedHotel);
            loadHotelsForBudget();
        }
    }

    private void saveHotelsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HOTEL_FILE))) {
            for (var entry : budgetHotels.entrySet()) {
                writer.write(selectedDestination + ";" + entry.getKey() + ";" + String.join(", ", entry.getValue()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

