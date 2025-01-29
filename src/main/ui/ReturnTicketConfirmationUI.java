// package main.ui;

// import java.awt.Color;
// import java.awt.Component;
// import java.awt.Dimension;
// import java.awt.Font;
// import java.io.BufferedWriter;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.HashSet;

// import javax.swing.Box;
// import javax.swing.BoxLayout;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;

// public class ReturnTicketConfirmationUI {

//     private JFrame frame;
//     private int numPeople;
//     private String username;
//     private String destination;
//     private String selectedBudget;
//     private String selectedTravelType;
//     private String startDate; // Tour date
//     private String endDate;
//     private String selectedTransport;
//     private HashSet<String> selectedSeats;
//     private String selectedHotel;
//     private String roomType;
//     private int numberOfRooms;
//     private int totalCost;
    
//     private String selectedReturnTransport;
//     private HashSet<String> returnselectedSeats;


//     //new ReturnSeatSelectionUI(frame, currentUsername, currentUsername, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats, selectedHotel, roomType, numberOfRooms, numPeople);


//     public ReturnTicketConfirmationUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType, String startDate, String endDate, String selectedTransport, HashSet<String> selectedSeats, String selectedHotel, String roomType, int numberOfRooms, int numPeople, int totalCost, String selectedReturnTransport, HashSet<String> returnselectedSeats) {
//         this.frame = existingFrame;
//         this.username = username;
//         this.destination = destination;
//         this.selectedBudget = selectedBudget;
//         this.selectedTravelType = selectedTravelType;
//         this.startDate = startDate;
//         this.endDate = endDate;
//         this.numPeople = numPeople;
//         this.selectedTransport = selectedTransport;
//         this.selectedSeats = selectedSeats;
//         this.selectedHotel = selectedHotel;
//         this.roomType = roomType;
//         this.numberOfRooms = numberOfRooms;
//         this.totalCost = totalCost;
//         this.selectedReturnTransport = selectedReturnTransport;
//         this.returnselectedSeats = returnselectedSeats;


//         frame.getContentPane().removeAll();
//         initializeUI();
//         frame.revalidate();
//         frame.repaint();
//     }

//     private void initializeUI() {
//         JPanel mainPanel = new JPanel();
//         mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//         mainPanel.setBackground(new Color(230, 240, 250));

//         JLabel titleLabel = new JLabel("Ticket Confirmation");
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
//         titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//         mainPanel.add(titleLabel);

//         mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

//         JLabel transportLabel = new JLabel("Transport: " + selectedReturnTransport);
//         transportLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         transportLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//         mainPanel.add(transportLabel);

//         JLabel destinationLabel = new JLabel("From Destination: " + destination);
//         destinationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         destinationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//         mainPanel.add(destinationLabel);

//         JLabel dateLabel = new JLabel("Date: " + endDate);
//         dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//         mainPanel.add(dateLabel);

//         JLabel seatsLabel = new JLabel("Return Seats: " + String.join(", ", returnselectedSeats));
//         seatsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         seatsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//         mainPanel.add(seatsLabel);

//         mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

//         JButton confirmButton = new JButton("Confirm Booking");
//         confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//         confirmButton.addActionListener(e -> {
//             saveTicketDetails();
//             //saveUserDetails();
//             JOptionPane.showMessageDialog(frame, "Ticket booked successfully!");
//             // Proceed to Hotel Selection UI
//             //JFrame existingFrame, String username, String destination, String selectedBudget, String startDate, String endDate, String transportOutbound, String seatsOutbound)
//             new BookingConfirmationUI(frame, username, destination, selectedBudget, selectedTravelType,startDate, endDate, selectedTransport,selectedSeats,selectedHotel, roomType, numberOfRooms, numPeople, totalCost, selectedReturnTransport, returnselectedSeats);
//         });
//         mainPanel.add(confirmButton);

//         JButton backButton = new JButton("Go Back");
//         backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//         backButton.addActionListener(e -> {
//             //new SeatSelectionUI(frame, selectedTransport, selectedSeats.size(), username, destination, startDate);
//             //public ReturnSeatSelectionUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType, String startDate, String endDate, String selectedTransport, HashSet<String> selectedSeats, String selectedHotel, String roomType, int numberOfRooms, int numPeople, int totalCost, String selectedReturnTransport) {

//             new ReturnSeatSelectionUI(frame, username, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats, selectedHotel, roomType, numberOfRooms, numPeople, totalCost, selectedReturnTransport);
//             //new ReturnSeatSelectionUI(frame, username, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, returnselectedSeats.size());
//         });
//         mainPanel.add(backButton);

//         frame.add(mainPanel);
//     }

//     private void saveTicketDetails() {
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/booked_seats.txt", true))) {
//             writer.write("Return Date: " + endDate + "\n");
//             writer.write("From Destination: " + destination + "\n");
//             writer.write("Transport: " + selectedReturnTransport + "\n");
//             writer.write("Seats: " + String.join(", ", returnselectedSeats) + "\n");
//             writer.write("----------------------------\n");
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

    
// }

package main.ui;

import java.awt.*;
import java.io.*;
import java.util.HashSet;
import javax.swing.*;

public class ReturnTicketConfirmationUI {
    private JFrame frame;
    private int numPeople, numberOfRooms, totalCost;
    private String username, destination, selectedBudget, selectedTravelType, startDate, endDate;
    private String selectedTransport, selectedHotel, roomType, selectedReturnTransport;
    private HashSet<String> selectedSeats, returnselectedSeats;

    public ReturnTicketConfirmationUI(JFrame existingFrame, String username, String destination, 
                                       String selectedBudget, String selectedTravelType, String startDate, 
                                       String endDate, String selectedTransport, HashSet<String> selectedSeats, 
                                       String selectedHotel, String roomType, int numberOfRooms, int numPeople, 
                                       int totalCost, String selectedReturnTransport, HashSet<String> returnselectedSeats) {
        this.frame = existingFrame;
        this.username = username;
        this.destination = destination;
        this.selectedBudget = selectedBudget;
        this.selectedTravelType = selectedTravelType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numPeople = numPeople;
        this.selectedTransport = selectedTransport;
        this.selectedSeats = selectedSeats;
        this.selectedHotel = selectedHotel;
        this.roomType = roomType;
        this.numberOfRooms = numberOfRooms;
        this.totalCost = totalCost;
        this.selectedReturnTransport = selectedReturnTransport;
        this.returnselectedSeats = returnselectedSeats;

        frame.getContentPane().removeAll();
        initializeUI();
        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(245, 250, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel titleLabel = createLabel("Ticket Confirmation", 20, Font.BOLD);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainPanel.add(createLabel("Transport: " + selectedReturnTransport, 14, Font.PLAIN));
        mainPanel.add(createLabel("From Destination: " + destination, 14, Font.PLAIN));
        mainPanel.add(createLabel("Date: " + endDate, 14, Font.PLAIN));
        mainPanel.add(createLabel("Return Seats: " + String.join(", ", returnselectedSeats), 14, Font.PLAIN));
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton confirmButton = createButton("Confirm Booking", e -> {
            saveTicketDetails();
            JOptionPane.showMessageDialog(frame, "Ticket booked successfully!");
            new BookingConfirmationUI(frame, username, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats, selectedHotel, roomType, numberOfRooms, numPeople, totalCost, selectedReturnTransport, returnselectedSeats);
        });
        mainPanel.add(confirmButton);

        JButton backButton = createButton("Go Back", e -> {
            new ReturnSeatSelectionUI(frame, username, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats, selectedHotel, roomType, numberOfRooms, numPeople, totalCost, selectedReturnTransport);
        });
        mainPanel.add(backButton);

        frame.add(mainPanel);
    }

    private JLabel createLabel(String text, int size, int style) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", style, size));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JButton createButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(action);
        button.setMaximumSize(new Dimension(200, 40));
        return button;
    }

    private void saveTicketDetails() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/booked_seats.txt", true))) {
            writer.write("Return Date: " + endDate + "\n");
            writer.write("From Destination: " + destination + "\n");
            writer.write("Transport: " + selectedReturnTransport + "\n");
            writer.write("Seats: " + String.join(", ", returnselectedSeats) + "\n");
            writer.write("----------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
