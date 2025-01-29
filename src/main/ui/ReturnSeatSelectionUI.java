// package main.ui;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.*;
// import java.util.Arrays;
// import java.util.HashSet;

// public class ReturnSeatSelectionUI {
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

//     private HashSet<String> returnselectedSeats = new HashSet<>(); // User-selected seats
//     private HashSet<String> returnoccupiedSeats = new HashSet<>(); // Seats already taken


//     //new ReturnSeatSelectionUI(frame, currentUsername, currentUsername, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats, selectedHotel, roomType, numberOfRooms, numPeople);


//     public ReturnSeatSelectionUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType, String startDate, String endDate, String selectedTransport, HashSet<String> selectedSeats, String selectedHotel, String roomType, int numberOfRooms, int numPeople, int totalCost, String selectedReturnTransport) {
//         this.frame = existingFrame;
//         this.username = username;
//         this.destination = destination;
//         this.selectedBudget = selectedBudget;
//         this.selectedTravelType = selectedTravelType;
//         this.startDate = startDate;
//         this.endDate = endDate;
//         this.selectedTransport = selectedTransport;
//         this.numPeople = numPeople;
//         this.selectedTransport = selectedTransport;
//         this.selectedSeats = selectedSeats;
//         this.selectedHotel = selectedHotel;
//         this.roomType = roomType;
//         this.numberOfRooms = numberOfRooms;
//         this.totalCost = totalCost;
//         this.selectedReturnTransport = selectedReturnTransport;

//         frame.getContentPane().removeAll(); // Clear frame

//         loadOccupiedSeats(); // Load occupied seats from file
//         initializeUI(); // Create UI
//         frame.revalidate();
//         frame.repaint();
//     }

//     private void initializeUI() {
//         JPanel mainPanel = new JPanel();
//         mainPanel.setLayout(new BorderLayout());
//         mainPanel.setBackground(new Color(240, 240, 240));

//         JLabel titleLabel = new JLabel("Select Your Seats for " + selectedTransport);
//         titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
//         mainPanel.add(titleLabel, BorderLayout.NORTH);

//         JPanel seatPanel = createSeatGrid(); // Create the seat grid
//         mainPanel.add(seatPanel, BorderLayout.CENTER);

//         JButton confirmButton = new JButton("Confirm Seats");
//         confirmButton.addActionListener(e -> confirmSeats());
//         mainPanel.add(confirmButton, BorderLayout.SOUTH);

//         frame.add(mainPanel);
//     }

//     private JPanel createSeatGrid() {
//         JPanel seatPanel = new JPanel();
//         seatPanel.setLayout(new GridLayout(5, 5, 5, 5)); // 5x5 seat grid
//         seatPanel.setBackground(Color.WHITE);

//         for (int row = 1; row <= 5; row++) {
//             for (int col = 1; col <= 5; col++) {
//                 String seat = "R" + row + "C" + col; // Seat identifier (e.g., R1C1)
//                 JButton seatButton = new JButton(seat);

//                 if (returnoccupiedSeats.contains(seat)) {
//                     seatButton.setEnabled(false); // Disable occupied seats
//                     seatButton.setBackground(Color.RED);
//                 } else {
//                     seatButton.setBackground(Color.GREEN);
//                     seatButton.addActionListener(new SeatButtonListener(seat, seatButton));
//                 }

//                 seatPanel.add(seatButton);
//             }
//         }

//         return seatPanel;
//     }

//     private void confirmSeats() {
//         if (returnselectedSeats.size() == numPeople) {
//             JOptionPane.showMessageDialog(frame, "Seats selected: " + returnselectedSeats);

//             // Pass data to Ticket Confirmation UI
//             //new ReturnSeatSelectionUI(frame, currentUsername, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats, selectedHotel, roomType, numberOfRooms, numPeople);
//             new ReturnTicketConfirmationUI(frame, username, destination, selectedBudget, selectedTravelType,startDate, endDate, selectedTransport,selectedSeats,selectedHotel, roomType, numberOfRooms, numPeople, totalCost, selectedReturnTransport, returnselectedSeats);
//         } else {
//             JOptionPane.showMessageDialog(frame, "Please select " + numPeople + " seats.");
//         }
//     }

//     private void loadOccupiedSeats() {
//         File file = new File("src/resources/booked_seats.txt");
//         if (!file.exists()) return;

//         try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//             String line;
//             String currentDate = null, currentDestination = null, currentTransport = null;

//             while ((line = reader.readLine()) != null) {
//                 if (line.startsWith("Return Date: ")) {
//                     currentDate = line.substring(13).trim();
//                 } else if (line.startsWith("From Destination: ")) {
//                     currentDestination = line.substring(17).trim();
//                 } else if (line.startsWith("Transport: ")) {
//                     currentTransport = line.substring(10).trim();
//                 } else if (line.startsWith("Seats: ")) {
//                     if (startDate.equals(currentDate) && destination.equals(currentDestination) && selectedTransport.equals(currentTransport)) {
//                         String[] seats = line.substring(7).split(", ");
//                         returnoccupiedSeats.addAll(Arrays.asList(seats)); // Add all occupied seats
//                     }
//                 }
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     private class SeatButtonListener implements ActionListener {
//         private final String seat;
//         private final JButton button;

//         public SeatButtonListener(String seat, JButton button) {
//             this.seat = seat;
//             this.button = button;
//         }

//         @Override
//         public void actionPerformed(ActionEvent e) {
//             if (returnselectedSeats.contains(seat)) {
//                 returnselectedSeats.remove(seat);
//                 button.setBackground(Color.GREEN);
//             } else if (returnselectedSeats.size() < numPeople) {
//                 returnselectedSeats.add(seat);
//                 button.setBackground(Color.BLUE);
//             } else {
//                 JOptionPane.showMessageDialog(frame, "You can only select " + numPeople + " seats.");
//             }
//         }
//     }
// }

package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public class ReturnSeatSelectionUI {
    private JFrame frame;
    private int numPeople;
    private String username;
    private String destination;
    private String selectedBudget;
    private String selectedTravelType;
    private String startDate; // Tour date
    private String endDate;

    private String selectedTransport;
    private HashSet<String> selectedSeats;
    private String selectedHotel;
    private String roomType;
    private int numberOfRooms;
    private int totalCost;

    private String selectedReturnTransport;

    private HashSet<String> returnselectedSeats = new HashSet<>(); // User-selected seats
    private HashSet<String> returnoccupiedSeats = new HashSet<>(); // Seats already taken


    //new ReturnSeatSelectionUI(frame, currentUsername, currentUsername, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, selectedSeats, selectedHotel, roomType, numberOfRooms, numPeople);


    public ReturnSeatSelectionUI(JFrame existingFrame, String username, String destination, String selectedBudget, String selectedTravelType, String startDate, String endDate, String selectedTransport, HashSet<String> selectedSeats, String selectedHotel, String roomType, int numberOfRooms, int numPeople, int totalCost, String selectedReturnTransport) {
        this.frame = existingFrame;
        this.username = username;
        this.destination = destination;
        this.selectedBudget = selectedBudget;
        this.selectedTravelType = selectedTravelType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.selectedTransport = selectedTransport;
        this.numPeople = numPeople;
        this.selectedTransport = selectedTransport;
        this.selectedSeats = selectedSeats;
        this.selectedHotel = selectedHotel;
        this.roomType = roomType;
        this.numberOfRooms = numberOfRooms;
        this.totalCost = totalCost;
        this.selectedReturnTransport = selectedReturnTransport;

        frame.getContentPane().removeAll(); // Clear frame

        loadOccupiedSeats(); // Load occupied seats from file
        initializeUI(); // Create UI
        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(230, 240, 250));

        JLabel titleLabel = new JLabel("Select Your Return Seats for " + selectedReturnTransport);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel seatPanel = createSeatGrid(); // Create the seat grid
        mainPanel.add(seatPanel, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm Seats");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(100, 150, 200));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        confirmButton.addActionListener(e -> confirmSeats());
        mainPanel.add(confirmButton, BorderLayout.SOUTH);

        frame.add(mainPanel);
    }

    private JPanel createSeatGrid() {
        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(4, 5, 10, 10)); // 4 rows, 5 columns with gaps
        seatPanel.setBackground(new Color(230, 240, 250));

        String[] rows = {"A", "B", "C", "D"};
        for (String row : rows) {
            for (int col = 1; col <= 4; col++) {
                String seat = row + col; // Seat identifier (e.g., A1, A2)
                JButton seatButton = new JButton(seat);
                seatButton.setFont(new Font("Arial", Font.PLAIN, 12));

                if (returnoccupiedSeats.contains(seat)) {
                    seatButton.setEnabled(false); // Disable occupied seats
                    seatButton.setBackground(Color.RED);
                } else {
                    seatButton.setBackground(Color.GREEN);
                    seatButton.addActionListener(new SeatButtonListener(seat, seatButton));
                }

                seatPanel.add(seatButton);

                // Add gap between 2nd and 3rd columns (bus aisle)
                if (col == 2) {
                    seatPanel.add(Box.createHorizontalStrut(20));
                }
            }
        }
        return seatPanel;
    }

    private void confirmSeats() {
        if (returnselectedSeats.size() == numPeople) {
            JOptionPane.showMessageDialog(frame, "Return seats selected: " + returnselectedSeats);

            // Pass data to Return Ticket Confirmation UI
            new ReturnTicketConfirmationUI(frame, username, destination, selectedBudget, selectedTravelType,startDate, endDate, selectedTransport,selectedSeats,selectedHotel, roomType, numberOfRooms, numPeople, totalCost, selectedReturnTransport, returnselectedSeats);
            //new ReturnTicketConfirmationUI(frame, username, destination, selectedBudget, selectedTravelType, startDate, endDate, selectedTransport, returnoccupiedSeats, destination, destination, numPeople, totalCost, numPeople, destination, returnselectedSeats);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select " + numPeople + " seats.");
        }
    }

    private void loadOccupiedSeats() {
        File file = new File("src/resources/booked_seats.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String currentDate = null, currentDestination = null, currentTransport = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Return Date: ")) {
                    currentDate = line.substring(13).trim();
                } else if (line.startsWith("From Destination: ")) {
                    currentDestination = line.substring(17).trim();
                } else if (line.startsWith("Transport: ")) {
                    currentTransport = line.substring(10).trim();
                } else if (line.startsWith("Seats: ")) {
                    if (startDate.equals(currentDate) && destination.equals(currentDestination) && selectedReturnTransport.equals(currentTransport)) {
                        String[] seats = line.substring(7).split(", ");
                        returnoccupiedSeats.addAll(Arrays.asList(seats)); // Add all occupied seats
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SeatButtonListener implements ActionListener {
        private final String seat;
        private final JButton button;

        public SeatButtonListener(String seat, JButton button) {
            this.seat = seat;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (returnselectedSeats.contains(seat)) {
                returnselectedSeats.remove(seat);
                button.setBackground(Color.GREEN);
            } else if (returnselectedSeats.size() < numPeople) {
                returnselectedSeats.add(seat);
                button.setBackground(Color.BLUE);
            } else {
                JOptionPane.showMessageDialog(frame, "You can only select " + numPeople + " seats.");
            }
        }
    }
}
