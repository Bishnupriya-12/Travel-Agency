package main.ui;

import javax.swing.*;

import main.auth.Authentication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI {
    private JFrame frame;

    public LoginUI(JFrame existingFrame) {
        this.frame = existingFrame; // Reuse the existing frame
        frame.getContentPane().removeAll(); // Clear the frame
        initializeUI(); // Initialize the Login UI
        frame.revalidate(); // Refresh the frame
        frame.repaint(); // Update the changes
    }

    public void display() {
        frame = new JFrame("Travel Agency - Login");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
        frame.setVisible(true);
    }

    private void initializeUI() {
        // Left panel for the image
        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(0, 0, 350, 500);
        imagePanel.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("src/resources/loginIMG.jpg")); // Replace with your image path
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Right panel for the login form
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(350, 0, 450, 500);
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(230, 240, 250)); // Light blue background

        JLabel titleLabel = new JLabel("Login to Travel Agency");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(400, 50, 350, 30);
        loginPanel.add(titleLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setBounds(400, 120, 100, 25);
        loginPanel.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(510, 120, 200, 25);
        loginPanel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(400, 160, 100, 25);
        loginPanel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(510, 160, 200, 25);
        loginPanel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(560, 220, 150, 30);
        loginButton.setBackground(new Color(100, 200, 100));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginPanel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(400, 220, 150, 30);
        registerButton.setBackground(new Color(100, 100, 200));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginPanel.add(registerButton);

        // Add panels to the frame
        frame.add(imagePanel);
        frame.add(loginPanel);


        // Action listeners for buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirect to dashboard (logic will be added later)
                //JOptionPane.showMessageDialog(frame, "Login clicked!");
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                if (Authentication.isValidUser(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                    // Proceed to the dashboard (or home screen)
                    new DashboardUI(frame); // Implement DashboardUI for redirection
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.");
                }
            }
        });

        // Register button action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterUI(frame); // Redirect to Register UI in the same frame
            }
        });
    }
}
