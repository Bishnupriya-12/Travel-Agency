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

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 25);
        formPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 25);
        usernameField.setEditable(false);
        formPanel.add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 90, 100, 25);
        formPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 90, 200, 25);
        formPanel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 130, 100, 25);
        formPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 130, 200, 25);
        formPanel.add(passwordField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.setBounds(150, 180, 200, 30);
        saveButton.setBackground(new Color(100, 200, 100));
        saveButton.setForeground(Color.WHITE);
        formPanel.add(saveButton);

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setBounds(150, 230, 200, 30);
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        formPanel.add(backButton);

        panel.add(formPanel, BorderLayout.CENTER);
        frame.add(panel);

        // Load user data
        loadUserData(usernameField, emailField, passwordField);

        // Action listeners
        saveButton.addActionListener(e -> saveUserData(emailField, passwordField));
        backButton.addActionListener(e -> new DashboardUI(frame, "Arpita"));
    }

    private void loadUserData(JTextField usernameField, JTextField emailField, JPasswordField passwordField) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(currentUsername)) {
                    usernameField.setText(parts[1]);
                    emailField.setText(parts[0]);
                    passwordField.setText(parts[2]);
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveUserData(JTextField emailField, JPasswordField passwordField) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/users.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(currentUsername)) {
                        String newEmail = emailField.getText();
                        String newPassword = new String(passwordField.getPassword());
                        line = currentUsername + "," + newEmail + "," + newPassword;
                    }
                    lines.add(line);
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/users.txt"))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            JOptionPane.showMessageDialog(frame, "Profile updated successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
