package main.ui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewBookingsUI {
    private JFrame frame;
    private String currentUsername;
    private List<String[]> bookings;
    private int currentIndex = 0;
    private JPanel cardPanel;
    private JLabel bookingDetails;

    public ViewBookingsUI(JFrame existingFrame, String username) {
        this.frame = existingFrame;
        this.currentUsername = username;
        this.bookings = new ArrayList<>();
        
        loadBookings();
        
        frame.getContentPane().removeAll();
        initializeUI();
        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        frame.setTitle("Your Bookings");
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 240, 255));

        JLabel titleLabel = new JLabel("Your Bookings");
titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
frame.add(titleLabel, BorderLayout.NORTH);

// Panel to hold the booking card
cardPanel = new JPanel();
cardPanel.setPreferredSize(new Dimension(550, 300)); // Adjusted size
cardPanel.setBackground(new Color(220, 220, 255)); // Light blue shade
cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adds margin around card

// Using GridBagLayout to center the card with spacing
JPanel centerPanel = new JPanel(new GridBagLayout());
centerPanel.setBackground(new Color(240, 240, 255)); // Soft background
centerPanel.add(cardPanel);

bookingDetails = new JLabel();
bookingDetails.setFont(new Font("Arial", Font.PLAIN, 16));
bookingDetails.setVerticalAlignment(SwingConstants.CENTER);
bookingDetails.setHorizontalAlignment(SwingConstants.CENTER);

// Adding to frame


        showBooking();
        
        cardPanel.add(bookingDetails);
        frame.add(centerPanel, BorderLayout.CENTER);
        //frame.add(cardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 240, 255));
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> new DashboardUI(frame, currentUsername));
        
        JButton prevButton = new JButton("←");
        prevButton.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                showBooking();
            }
        });
        
        JButton nextButton = new JButton("→");
        nextButton.addActionListener(e -> {
            if (currentIndex < bookings.size() - 1) {
                currentIndex++;
                showBooking();
            }
        });
        
        buttonPanel.add(prevButton);
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadBookings() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/user_bookings.txt"))) {
            String line;
            List<String> bookingData = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    if (!bookingData.isEmpty() && bookingData.get(0).equals(currentUsername)) {
                        bookings.add(bookingData.toArray(new String[0]));
                    }
                    bookingData = new ArrayList<>();
                    bookingData.add(line.substring(10));
                } else if (!line.equals("----------------------------")) {
                    bookingData.add(line);
                }
            }
            if (!bookingData.isEmpty() && bookingData.get(0).equals(currentUsername)) {
                bookings.add(bookingData.toArray(new String[0]));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading bookings: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showBooking() {
        if (bookings.isEmpty()) {
            bookingDetails.setText("No bookings found.");
            return;
        }
        
        String[] booking = bookings.get(currentIndex);
        StringBuilder formattedDetails = new StringBuilder("<html>");
        for (int i = 1; i < booking.length; i++) {
            formattedDetails.append(booking[i]).append("<br>");
        }
        formattedDetails.append("</html>");
        
        bookingDetails.setText(formattedDetails.toString());
        
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 255), 2, true),
                BorderFactory.createEmptyBorder(15, 30, 15, 30)
        ));
        cardPanel.setBackground(new Color(220, 220, 255));
    }
}