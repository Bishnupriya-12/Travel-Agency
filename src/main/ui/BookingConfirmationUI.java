// package main.ui;

// import javax.swing.*;
// import java.awt.*;
// import java.io.BufferedWriter;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.HashSet;

// public class BookingConfirmationUI {

//     private JFrame frame;
//     private String username;
//     private String destination;
//     private String selectedBudget;
//     private String selectedTravelType;
//     private String startDate; // Tour date
//     private String endDate;   // Return date
//     private String selectedTransport;
//     private HashSet<String> selectedSeats;
//     private String selectedHotel;
//     private String roomType;
//     private int numberOfRooms;
//     private int totalCost;
//     private int numPeople;
//     private String selectedReturnTransport;
//     private HashSet<String> returnSelectedSeats;

//     public BookingConfirmationUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType,
//                                   String startDate, String endDate, String selectedTransport, HashSet<String> selectedSeats,
//                                   String selectedHotel, String roomType, int numberOfRooms, int numPeople, int totalCost,
//                                   String selectedReturnTransport, HashSet<String> returnSelectedSeats) {
//         this.frame = existingFrame;
//         this.username = username;
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
//         this.numPeople = numPeople;
//         this.totalCost = totalCost;
//         this.selectedReturnTransport = selectedReturnTransport;
//         this.returnSelectedSeats = returnSelectedSeats;

//         frame.getContentPane().removeAll();
//         initializeUI();
//         frame.revalidate();
//         frame.repaint();
//     }

//     private void initializeUI() {
//         JPanel mainPanel = new JPanel();
//         mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//         mainPanel.setBackground(new Color(245, 245, 255));
//         mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

//         JLabel titleLabel = new JLabel("Booking Confirmation");
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
//         titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//         mainPanel.add(titleLabel);

//         mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

//         // Booking Details
//         addDetail(mainPanel, "Username:", username);
//         addDetail(mainPanel, "Destination:", destination);
//         addDetail(mainPanel, "Budget:", selectedBudget);
//         addDetail(mainPanel, "Travel Type:", selectedTravelType);
//         addDetail(mainPanel, "Start Date:", startDate);
//         addDetail(mainPanel, "End Date (Return):", endDate);
//         addDetail(mainPanel, "Outbound Transport:", selectedTransport);
//         addDetail(mainPanel, "Outbound Seats:", String.join(", ", selectedSeats));
//         addDetail(mainPanel, "Return Transport:", selectedReturnTransport);
//         addDetail(mainPanel, "Return Seats:", String.join(", ", returnSelectedSeats));
//         addDetail(mainPanel, "Hotel:", selectedHotel);
//         addDetail(mainPanel, "Room Type:", roomType);
//         addDetail(mainPanel, "Number of Rooms:", String.valueOf(numberOfRooms));
//         addDetail(mainPanel, "Number of People:", String.valueOf(numPeople));

//         mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

//         // Buttons
//         JPanel buttonPanel = new JPanel();
//         buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
//         buttonPanel.setOpaque(false);

//         JButton saveButton = new JButton("Save Booking");
//         saveButton.addActionListener(e -> 
//         {
//             saveBookingDetails();
//             frame.dispose();
//         });
//         buttonPanel.add(saveButton);

//         buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));

//         JButton backButton = new JButton("Go Back");
//         backButton.addActionListener(e -> new ReturnTicketConfirmationUI(
//                 frame, username, destination, selectedBudget, selectedTravelType, startDate, endDate, 
//                 selectedTransport, selectedSeats, selectedHotel, roomType, numberOfRooms, numPeople, totalCost,
//                 selectedReturnTransport, returnSelectedSeats));
//         buttonPanel.add(backButton);

//         mainPanel.add(buttonPanel);

//         frame.add(mainPanel);
//     }

//     private void addDetail(JPanel panel, String label, String value) {
//         JPanel detailPanel = new JPanel();
//         detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.X_AXIS));
//         detailPanel.setBackground(new Color(245, 245, 255));

//         JLabel labelComponent = new JLabel(label);
//         labelComponent.setFont(new Font("Arial", Font.BOLD, 14));
//         detailPanel.add(labelComponent);

//         detailPanel.add(Box.createRigidArea(new Dimension(10, 0)));

//         JLabel valueComponent = new JLabel(value);
//         valueComponent.setFont(new Font("Arial", Font.PLAIN, 14));
//         detailPanel.add(valueComponent);

//         detailPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//         panel.add(detailPanel);

//         panel.add(Box.createRigidArea(new Dimension(0, 10)));
//     }

//     private void saveBookingDetails() {
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/user_bookings.txt", true))) {
//             writer.write("Username: " + username + "\n");
//             writer.write("Destination: " + destination + "\n");
//             writer.write("Budget: " + selectedBudget + "\n");
//             writer.write("Travel Type: " + selectedTravelType + "\n");
//             writer.write("Start Date: " + startDate + "\n");
//             writer.write("End Date: " + endDate + "\n");
//             writer.write("Transport (Outbound): " + selectedTransport + "\n");
//             writer.write("Seats (Outbound): " + String.join(", ", selectedSeats) + "\n");           
//             writer.write("Hotel: " + selectedHotel + "\n");
//             writer.write("Room Type: " + roomType + "\n");
//             writer.write("Number of Rooms: " + numberOfRooms + "\n");
//             writer.write("Number of People: " + numPeople + "\n");
//             writer.write("Hotel Cost " + totalCost + "\n");
//             writer.write("Transport (Return): " + selectedReturnTransport + "\n");
//             writer.write("Seats (Return): " + String.join(", ", returnSelectedSeats) + "\n");
//             writer.write("----------------------------\n");
//             JOptionPane.showMessageDialog(frame, "Booking saved successfully!");
//         } catch (IOException e) {
//             JOptionPane.showMessageDialog(frame, "Failed to save booking details.", "Error", JOptionPane.ERROR_MESSAGE);
//             e.printStackTrace();
//         }
//     }
// }

package main.ui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class BookingConfirmationUI {

    private JFrame frame;
    private String username;
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
    private int numPeople;
    private String selectedReturnTransport;
    private HashSet<String> returnSelectedSeats;

    public BookingConfirmationUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType,
                                  String startDate, String endDate, String selectedTransport, HashSet<String> selectedSeats,
                                  String selectedHotel, String roomType, int numberOfRooms, int numPeople, int totalCost,
                                  String selectedReturnTransport, HashSet<String> returnSelectedSeats) {
        this.frame = existingFrame;
        this.username = username;
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
        this.numPeople = numPeople;
        this.totalCost = totalCost;
        this.selectedReturnTransport = selectedReturnTransport;
        this.returnSelectedSeats = returnSelectedSeats;

        frame.getContentPane().removeAll();
        initializeUI();
        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        frame.setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 240, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel titleLabel = new JLabel("Booking Confirmation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;

        addDetail(mainPanel, gbc, "Username:", username);
        addDetail(mainPanel, gbc, "Destination:", destination);
        addDetail(mainPanel, gbc, "Budget:", selectedBudget);
        addDetail(mainPanel, gbc, "Travel Type:", selectedTravelType);
        addDetail(mainPanel, gbc, "Start Date:", startDate);
        addDetail(mainPanel, gbc, "Return Date:", endDate);
        addDetail(mainPanel, gbc, "Outbound Transport:", selectedTransport);
        addDetail(mainPanel, gbc, "Outbound Seats:", String.join(", ", selectedSeats));
        addDetail(mainPanel, gbc, "Return Transport:", selectedReturnTransport);
        addDetail(mainPanel, gbc, "Return Seats:", String.join(", ", returnSelectedSeats));
        addDetail(mainPanel, gbc, "Hotel:", selectedHotel);
        addDetail(mainPanel, gbc, "Room Type:", roomType);
        addDetail(mainPanel, gbc, "Number of Rooms:", String.valueOf(numberOfRooms));
        addDetail(mainPanel, gbc, "Number of People:", String.valueOf(numPeople));

        gbc.gridy++;
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton saveButton = new JButton("Save Booking");
        saveButton.addActionListener(e -> {
            saveBookingDetails();
            frame.dispose();
        });
        buttonPanel.add(saveButton);

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(e -> new ReturnTicketConfirmationUI(
                frame, username, destination, selectedBudget, selectedTravelType, startDate, endDate, 
                selectedTransport, selectedSeats, selectedHotel, roomType, numberOfRooms, numPeople, totalCost,
                selectedReturnTransport, returnSelectedSeats));
        buttonPanel.add(backButton);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);
    }

    private void addDetail(JPanel panel, GridBagConstraints gbc, String label, String value) {
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        panel.add(labelComponent, gbc);

        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        panel.add(valueComponent, gbc);

        gbc.gridy++;
    }

    private void saveBookingDetails() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/user_bookings.txt", true))) {
            writer.write("Username: " + username + "\n");
            writer.write("Destination: " + destination + "\n");
            writer.write("Budget: " + selectedBudget + "\n");
            writer.write("Travel Type: " + selectedTravelType + "\n");
            writer.write("Start Date: " + startDate + "\n");
            writer.write("End Date: " + endDate + "\n");
            writer.write("Transport (Outbound): " + selectedTransport + "\n");
            writer.write("Seats (Outbound): " + String.join(", ", selectedSeats) + "\n");           
            writer.write("Hotel: " + selectedHotel + "\n");
            writer.write("Room Type: " + roomType + "\n");
            writer.write("Number of Rooms: " + numberOfRooms + "\n");
            writer.write("Number of People: " + numPeople + "\n");
            writer.write("Total Cost: " + totalCost + "\n");
            writer.write("Transport (Return): " + selectedReturnTransport + "\n");
            writer.write("Seats (Return): " + String.join(", ", returnSelectedSeats) + "\n");
            writer.write("----------------------------\n");
            JOptionPane.showMessageDialog(frame, "Booking saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Failed to save booking details.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
