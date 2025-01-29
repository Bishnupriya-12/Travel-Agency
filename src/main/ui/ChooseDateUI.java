// package main.ui;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.text.SimpleDateFormat;
// import java.util.Calendar;
// import java.util.Date;

// public class ChooseDateUI {
//     private JFrame frame;
//     private String destination;
//     private String currentUsername;
//     private String selectedBudget;
//     private String selectedTravelType;
    
//     private JComboBox<String> dayComboBox;
//     //private JDatePickerImpl startDatePicker;
//     private JTextField endDateField;

//     public ChooseDateUI(JFrame existingFrame, String destination, String username, String selectedBudget, String selectedTravelType) {
//         this.frame = existingFrame;
//         this.destination = destination;
//         this.currentUsername = username;
//         this.selectedBudget = selectedBudget;
//         this.selectedTravelType = selectedTravelType;

//         frame.getContentPane().removeAll(); // Clear the frame
//         initializeUI(); // Initialize the Choose Date UI
//         frame.revalidate();
//         frame.repaint();
//     }


//     private void initializeUI() {
//         JPanel mainPanel = new JPanel();
//         mainPanel.setLayout(null);
//         mainPanel.setBackground(new Color(230, 240, 250));
    

//         JLabel usernameLabel = new JLabel("username: " + currentUsername);
//         usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
//         usernameLabel.setBounds(50, 10, 600, 30);
//         mainPanel.add(usernameLabel);

//         // Label for the destination
//         JLabel destinationLabel = new JLabel("Destination: " + destination);
//         destinationLabel.setFont(new Font("Arial", Font.BOLD, 16));
//         destinationLabel.setBounds(50, 30, 600, 30);
//         mainPanel.add(destinationLabel);
    
//         // Label for the selected package
//         JLabel packageLabel = new JLabel("Budget: " + selectedBudget + ", Travel Type: " + selectedTravelType);
//         packageLabel.setFont(new Font("Arial", Font.BOLD, 16));
//         packageLabel.setBounds(50, 70, 600, 30);
//         mainPanel.add(packageLabel);
    
//         // Label for selecting the start date
//         JLabel startDateLabel = new JLabel("Choose Start Date:");
//         startDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         startDateLabel.setBounds(50, 120, 150, 30);
//         mainPanel.add(startDateLabel);
    
//         // Spinner for selecting the start date
//         SpinnerDateModel model = new SpinnerDateModel();
//         JSpinner startDateSpinner = new JSpinner(model);
//         startDateSpinner.setBounds(200, 120, 200, 30);
//         JSpinner.DateEditor editor = new JSpinner.DateEditor(startDateSpinner, "MM/dd/yyyy");
//         startDateSpinner.setEditor(editor);
//         mainPanel.add(startDateSpinner);
    
//         // Label for number of days
//         JLabel daysLabel = new JLabel("Number of Days:");
//         daysLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         daysLabel.setBounds(50, 160, 150, 30);
//         mainPanel.add(daysLabel);
    
//         // Combo box for selecting the number of days
//         String[] daysOptions = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
//         dayComboBox = new JComboBox<>(daysOptions);
//         dayComboBox.setBounds(200, 160, 100, 30);
//         mainPanel.add(dayComboBox);
    
//         // Label for end date
//         JLabel endDateLabel = new JLabel("End Date:");
//         endDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         endDateLabel.setBounds(50, 200, 150, 30);
//         mainPanel.add(endDateLabel);
    
//         // End date field (auto-filled)
//         endDateField = new JTextField();
//         endDateField.setEditable(false);
//         endDateField.setBounds(200, 200, 200, 30);
//         mainPanel.add(endDateField);
    
//         // Calculate end date when number of days is selected
//         dayComboBox.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 calculateEndDate(startDateSpinner);
//             }
//         });
    
//         // Button to move to the next step (Confirm Booking)
//         JButton confirmButton = new JButton("Confirm Booking");
//         confirmButton.setBounds(50, 250, 150, 40);
//         confirmButton.setFont(new Font("Arial", Font.PLAIN, 14));
//         confirmButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 Date startDatee = (Date) startDateSpinner.getValue();
//                 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//                 String startDate = dateFormat.format(startDatee);
//                 //String startDate = startDateSpinner.getValue().toString();
//                 String endDate = endDateField.getText(); // Assuming you calculate this already
//                 //saveUserDetails(destination, selectedBudget, selectedTravelType, startDate, endDate, currentUsername);
                
//                 // Proceed to the transport selection UI
//                 new ChooseTransportUI(frame, currentUsername, destination, selectedBudget, selectedTravelType, startDate, endDate);
//             }
//         });
        
//         mainPanel.add(confirmButton);
    
//         frame.add(mainPanel);
//     }
    
//     private void calculateEndDate(JSpinner startDateSpinner) {
//         // Get the start date from the spinner
//         Date startDatee = (Date) startDateSpinner.getValue();
//         SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//         String startDate = dateFormat.format(startDatee);

//         if (startDate != null) {
//             // Get the number of days to stay
//             int numDays = Integer.parseInt((String) dayComboBox.getSelectedItem());
    
//             // Calculate the end date
//             Calendar cal = Calendar.getInstance();
//             cal.setTime(startDatee);
//             cal.add(Calendar.DAY_OF_YEAR, numDays);
//             Date endDate = cal.getTime();
    
//             // Set the end date in the text field
//             SimpleDateFormat dateFormatend = new SimpleDateFormat("MM/dd/yyyy");
//             endDateField.setText(dateFormatend.format(endDate));


//         }
//     }

// //     private void saveUserDetails(String destination, String selectedBudget, String selectedTravelType, String startDate, String endDate, String username) {
// //     try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/user_bookings.txt", true))) {
// //         writer.write("Username: " + username + "\n");
// //         writer.write("Destination: " + destination + "\n");
// //         writer.write("selectedBudget " + selectedBudget+ "\n");
// //         writer.write("selectedTravelType " + selectedTravelType+ "\n");
// //         writer.write("Start Date: " + startDate + "\n");
// //         writer.write("End Date: " + endDate + "\n");
// //         writer.write("----------------------------\n"); // Separator
// //     } catch (IOException e) {
// //         e.printStackTrace();
// //     }
// // }

    
    
// }
package main.ui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChooseDateUI {
    private JFrame frame;
    private String destination;
    private String currentUsername;
    private String selectedBudget;
    private String selectedTravelType;

    private JComboBox<String> dayComboBox;
    private JTextField endDateField;

    public ChooseDateUI(JFrame existingFrame, String destination, String username, String selectedBudget, String selectedTravelType) {
        this.frame = existingFrame;
        this.destination = destination;
        this.currentUsername = username;
        this.selectedBudget = selectedBudget;
        this.selectedTravelType = selectedTravelType;

        frame.getContentPane().removeAll(); // Clear the frame
        initializeUI(); // Initialize the Choose Date UI
        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        frame.setTitle("Choose Your Travel Dates");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 248, 255)); // Light blue background
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margin between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        JLabel titleLabel = new JLabel("Choose Your Travel Dates");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Username Label
        JLabel usernameLabel = new JLabel("Username: " + currentUsername);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(usernameLabel, gbc);

        // Destination Label
        JLabel destinationLabel = new JLabel("Destination: " + destination);
        destinationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        mainPanel.add(destinationLabel, gbc);

        // Package Details
        JLabel packageLabel = new JLabel("Budget: " + selectedBudget + ", Travel Type: " + selectedTravelType);
        packageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 3;
        mainPanel.add(packageLabel, gbc);

        // Start Date Label
        JLabel startDateLabel = new JLabel("Choose Start Date:");
        startDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        mainPanel.add(startDateLabel, gbc);

        // Start Date Spinner
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner startDateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(startDateSpinner, "MM/dd/yyyy");
        startDateSpinner.setEditor(editor);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(startDateSpinner, gbc);

        // Number of Days Label
        JLabel daysLabel = new JLabel("Number of Days:");
        daysLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(daysLabel, gbc);

        // Number of Days ComboBox
        String[] daysOptions = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        dayComboBox = new JComboBox<>(daysOptions);
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(dayComboBox, gbc);

        // End Date Label
        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(endDateLabel, gbc);

        // End Date Field
        endDateField = new JTextField();
        endDateField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(endDateField, gbc);

        // Calculate End Date on Days Selection
        dayComboBox.addActionListener(e -> calculateEndDate(startDateSpinner));

        // Confirm Button
        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(65, 105, 225)); // Royal blue background
        confirmButton.setForeground(Color.WHITE); // White text
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        confirmButton.addActionListener(e -> {
            Date startDate = (Date) startDateSpinner.getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String formattedStartDate = dateFormat.format(startDate);
            String endDate = endDateField.getText();

            new ChooseTransportUI(frame, currentUsername, destination, selectedBudget, selectedTravelType, formattedStartDate, endDate);
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(confirmButton, gbc);

        frame.add(mainPanel);
    }

    private void calculateEndDate(JSpinner startDateSpinner) {
        Date startDate = (Date) startDateSpinner.getValue();
        int numDays = Integer.parseInt((String) dayComboBox.getSelectedItem());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, numDays);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        endDateField.setText(dateFormat.format(calendar.getTime()));
    }
}
