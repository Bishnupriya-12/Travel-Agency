package main.ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ProfileUI {
    private JFrame frame;
    private String currentUsername;

    public ProfileUI(JFrame existingFrame, String username) {
        this.frame = existingFrame;
        this.currentUsername = username;

        frame.getContentPane().removeAll(); // Clear the frame
        initializeUI(); // Initialize the Profile UI
        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Your Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(230, 240, 250));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 90, 100, 25);
        formPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 90, 200, 25);
        formPanel.add(emailField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 25);
        formPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 25);
        formPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 130, 100, 25);
        formPanel.add(passwordLabel);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(150, 130, 200, 25);
        formPanel.add(passwordField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 170, 100, 25);
        formPanel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(150, 170, 200, 25);
        formPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 210, 100, 25);
        formPanel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(150, 210, 200, 25);
        formPanel.add(lastNameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 250, 100, 25);
        formPanel.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(150, 250, 200, 25);
        formPanel.add(ageField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 290, 100, 25);
        formPanel.add(genderLabel);

        JTextField genderField = new JTextField();
        genderField.setBounds(150, 290, 200, 25);
        formPanel.add(genderField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 330, 100, 25);
        formPanel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 330, 200, 25);
        formPanel.add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 370, 100, 25);
        formPanel.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(150, 370, 200, 25);
        formPanel.add(addressField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.setBounds(150, 420, 200, 30);
        saveButton.setBackground(new Color(100, 200, 100));
        saveButton.setForeground(Color.WHITE);
        formPanel.add(saveButton);

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setBounds(150, 460, 200, 30);
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        formPanel.add(backButton);

        panel.add(formPanel, BorderLayout.CENTER);
        frame.add(panel);

        // Load user data
        loadUserData(usernameField, emailField, passwordField, firstNameField, lastNameField, ageField, genderField, phoneField, addressField);

        // Action listeners
        saveButton.addActionListener(e -> saveUserData(emailField, usernameField, passwordField, firstNameField, lastNameField, ageField, genderField, phoneField, addressField));
        backButton.addActionListener(e -> new DashboardUI(frame, currentUsername));
    }

    private void loadUserData(JTextField... fields) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(currentUsername)) {
                    for (int i = 0; i < fields.length && i < parts.length; i++) {
                        fields[i].setText(parts[i].trim());
                    }
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void saveUserData(JTextField emailField, JTextField usernameField, JTextField passwordField, 
                          JTextField firstNameField, JTextField lastNameField, JTextField ageField, 
                          JTextField genderField, JTextField phoneField, JTextField addressField) {
    try {
        ArrayList<String> lines = new ArrayList<>();
        boolean userFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 2 && parts[1].equals(currentUsername)) {
                    // Construct new user data line ensuring each field is placed correctly
                    line = usernameField.getText().trim() + "," +
                           emailField.getText().trim() + "," +
                           passwordField.getText().trim() + "," +
                           firstNameField.getText().trim() + "," +
                           lastNameField.getText().trim() + "," +
                           ageField.getText().trim() + "," +
                           genderField.getText().trim() + "," +
                           phoneField.getText().trim() + "," +
                           addressField.getText().trim();
                    
                    userFound = true;
                }
                lines.add(line);
            }
        }

        // If user was found, rewrite the file with updated data
        if (userFound) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/users.txt"))) {
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
            JOptionPane.showMessageDialog(frame, "Profile updated successfully!");
        } else {
            JOptionPane.showMessageDialog(frame, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Error saving user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}