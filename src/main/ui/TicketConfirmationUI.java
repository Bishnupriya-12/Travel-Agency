package main.ui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class TicketConfirmationUI {
    private JFrame frame;
    private String username;
    private String destination;
    private String selectedBudget;
    private String selectedTravelType;
    private String startDate; // Tour date
    private String endDate;
    private String selectedTransport;
    private HashSet<String> selectedSeats;

    public TicketConfirmationUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType, String startDate, String endDate, String selectedTransport, int numPeople, HashSet<String> selectedSeats) {
        this.frame = existingFrame;
        this.username = username;
        this.destination = destination;
        this.selectedBudget = selectedBudget;
        this.selectedTravelType = selectedTravelType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.selectedTransport = selectedTransport;
        this.selectedSeats = selectedSeats;

        frame.getContentPane().removeAll();
        initializeUI();
        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 245, 250));

        JLabel titleLabel = new JLabel("Ticket Confirmation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel cardPanel = createCardPanel();
        mainPanel.add(cardPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(240, 245, 250));

        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.setPreferredSize(new Dimension(150, 30));
        confirmButton.addActionListener(e -> {
            saveTicketDetails();
            JOptionPane.showMessageDialog(frame, "Ticket booked successfully!");
            new HotelSelectionUI(frame, username, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats);
        });
        buttonPanel.add(confirmButton);

        JButton backButton = new JButton("Go Back");
        backButton.setPreferredSize(new Dimension(150, 30));
        backButton.addActionListener(e -> {
            new SeatSelectionUI(frame, username, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats.size());
        });
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel);

        frame.add(mainPanel);
    }

    private JPanel createCardPanel() {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(6, 2, 10, 10));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font valueFont = new Font("Arial", Font.BOLD, 14);

        addCardContent(cardPanel, "Transport:", selectedTransport, labelFont, valueFont);
        addCardContent(cardPanel, "Destination:", destination, labelFont, valueFont);
        addCardContent(cardPanel, "Start Date:", startDate, labelFont, valueFont);
        addCardContent(cardPanel, "End Date:", endDate, labelFont, valueFont);
        addCardContent(cardPanel, "Seats:", String.join(", ", selectedSeats), labelFont, valueFont);
        addCardContent(cardPanel, "Budget:", selectedBudget, labelFont, valueFont);

        return cardPanel;
    }

    private void addCardContent(JPanel cardPanel, String fieldName, String value, Font labelFont, Font valueFont) {
        JLabel fieldLabel = new JLabel(fieldName);
        fieldLabel.setFont(labelFont);
        fieldLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(valueFont);
        valueLabel.setHorizontalAlignment(SwingConstants.LEFT);

        cardPanel.add(fieldLabel);
        cardPanel.add(valueLabel);
    }

    private void saveTicketDetails() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/booked_seats.txt", true))) {
            writer.write("Date: " + startDate + "\n");
            writer.write("Destination: " + destination + "\n");
            writer.write("Transport: " + selectedTransport + "\n");
            writer.write("Seats: " + String.join(", ", selectedSeats) + "\n");
            writer.write("----------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 
