package main.ui;

import javax.swing.*;

import main.auth.Authentication;
import main.models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterUI {
    private JFrame frame;

    public RegisterUI(JFrame existingFrame) {
        this.frame = existingFrame; // Reuse the existing frame
        frame.getContentPane().removeAll(); // Clear the frame
        initializeUI(); // Initialize Register UI
        frame.revalidate(); // Refresh the frame
        frame.repaint(); // Update the changes
    }

    private void initializeUI() {

        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(350, 500));
        imagePanel.setBounds(0, 0, 350, 500);
        imagePanel.setLayout(new BorderLayout());
        
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Load and scale the image
        ImageIcon originalIcon = new ImageIcon("src/resources/images/registerIMG.jpg"); // Replace with your image path
        Image scaledImage = originalIcon.getImage().getScaledInstance(350, 500, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        imagePanel.add(imageLabel, BorderLayout.CENTER);
        
        // Right panel for the register form
        JPanel registerPanel = new JPanel();
        registerPanel.setBounds(350, 0, 450, 500);
        registerPanel.setLayout(null);
        registerPanel.setBackground(new Color(250, 240, 250)); // Light blue background

        // Title
        JLabel titleLabel = new JLabel("Register to Travel Agency");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(400, 50, 350, 30);
        registerPanel.add(titleLabel);

        // Email label and text field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setBounds(400, 100, 100, 25);
        registerPanel.add(emailLabel);

        JTextField emailText = new JTextField();
        emailText.setBounds(530, 100, 200, 25);
        registerPanel.add(emailText);

        // Username label and text field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setBounds(400, 140, 100, 25);
        registerPanel.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(530, 140, 200, 25);
        registerPanel.add(userText);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(400, 180, 100, 25);
        registerPanel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(530, 180, 200, 25);
        registerPanel.add(passwordText);

        // Confirm Password label and field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPasswordLabel.setBounds(400, 220, 150, 25);
        registerPanel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordText = new JPasswordField();
        confirmPasswordText.setBounds(530, 220, 200, 25);
        registerPanel.add(confirmPasswordText);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(400, 280, 150, 30);
        registerButton.setBackground(new Color(100, 200, 100)); // Light green button
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerPanel.add(registerButton);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(560, 280, 150, 30);
        backButton.setBackground(new Color(200, 100, 100)); // Light red button
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerPanel.add(backButton);

        // Add panels to the frame
        frame.add(imagePanel);
        frame.add(registerPanel);

        // Register button action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailText.getText();
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                String confirmPassword = new String(confirmPasswordText.getPassword());

                if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Authentication.isUserExists(username)) {
                    JOptionPane.showMessageDialog(frame, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User newUser = new User(email, username, password);

                if (Authentication.registerUser(newUser.getEmail(), newUser.getUsername(), newUser.getPassword())) {
                    JOptionPane.showMessageDialog(frame, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new LoginUI(frame); // Redirect to login
                } else {
                    JOptionPane.showMessageDialog(frame, "Error registering user!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Back button action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginUI(frame); // Redirect to login
            }
        });
    }

    
}