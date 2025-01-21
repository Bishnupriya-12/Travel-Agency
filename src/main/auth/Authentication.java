package main.auth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Authentication {

    // Method to validate user credentials from users.txt
    public static boolean isValidUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String storedUsername = userData[1].trim();
                String storedPassword = userData[2].trim();

                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isUserExists(String username) {
        try (Scanner scanner = new Scanner(new File("src/resources/users.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.split(",")[1].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
        return false;
    }

    public static boolean registerUser(String email, String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/users.txt", true))) {
            writer.write(email + "," + username + "," + password);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to users file: " + e.getMessage());
        }
        return false;
    }
}
