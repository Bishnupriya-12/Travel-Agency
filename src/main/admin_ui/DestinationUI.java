package main.admin_ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DestinationUI {
    private JFrame frame;
    private JComboBox<String> destinationComboBox;
    private List<String> destinations;
    private static final String DESTINATION_FILE = "src/resources/destination.txt";

    public DestinationUI(JFrame existingFrame) {
        this.frame = existingFrame;
        frame.getContentPane().removeAll();
        initializeUI();
        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Select Destination");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(300, 20, 200, 30);
        panel.add(titleLabel);

        destinations = loadDestinations();
        destinationComboBox = new JComboBox<>(destinations.toArray(new String[0]));
        destinationComboBox.setBounds(300, 80, 200, 25);
        panel.add(destinationComboBox);

        JButton addButton = new JButton("Add Destination");
        addButton.setBounds(100, 120, 150, 30);
        panel.add(addButton);

        JButton deleteButton = new JButton("Delete Destination");
        deleteButton.setBounds(260, 120, 180, 30);
        panel.add(deleteButton);

        JButton updateButton = new JButton("Update Destination");
        updateButton.setBounds(450, 120, 180, 30);
        panel.add(updateButton);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(300, 170, 200, 30);
        panel.add(nextButton);

        frame.add(panel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newDestination = JOptionPane.showInputDialog(frame, "Enter new destination:");
                if (newDestination != null && !newDestination.trim().isEmpty() && !destinations.contains(newDestination)) {
                    destinations.add(newDestination);
                    destinationComboBox.addItem(newDestination);
                    saveDestinations();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDestination = (String) destinationComboBox.getSelectedItem();
                if (selectedDestination != null) {
                    int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this destination?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        destinations.remove(selectedDestination);
                        destinationComboBox.removeItem(selectedDestination);
                        saveDestinations();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a destination to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDestination = (String) destinationComboBox.getSelectedItem();
                if (selectedDestination != null) {
                    String newDestination = JOptionPane.showInputDialog(frame, "Update destination:", selectedDestination);
                    if (newDestination != null && !newDestination.trim().isEmpty() && !destinations.contains(newDestination)) {
                        int index = destinations.indexOf(selectedDestination);
                        destinations.set(index, newDestination);
                        destinationComboBox.insertItemAt(newDestination, index);
                        destinationComboBox.removeItem(selectedDestination);
                        destinationComboBox.setSelectedItem(newDestination);
                        saveDestinations();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a destination to update.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDestination = (String) destinationComboBox.getSelectedItem();
                if (selectedDestination != null && !selectedDestination.isEmpty()) {
                    frame.getContentPane().removeAll();
                    new HotelUI(frame, selectedDestination);
                    frame.revalidate();
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a destination first.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private List<String> loadDestinations() {
        List<String> list = new ArrayList<>();
        File file = new File(DESTINATION_FILE);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveDestinations() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DESTINATION_FILE))) {
            for (String destination : destinations) {
                bw.write(destination);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}