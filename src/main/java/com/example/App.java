package com.example;

import java.sql.*;
import java.util.Scanner;
import java.security.SecureRandom;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        // ✅ Use the new method for validation
        if (!isValidUsername(username)) {
            System.out.println("Invalid username format.");
            return;
        }

        // ✅ Secure random OTP generation
        SecureRandom secureRandom = new SecureRandom();
        int otp = 100000 + secureRandom.nextInt(900000);

        logger.info("OTP generated successfully"); // no sensitive data logged

        // ✅ Database connection using environment variables (no hardcoding)
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM users WHERE username = ?")) {

            // ✅ Prevent SQL Injection
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("User found. OTP: " + otp);
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            // ✅ Safe error handling (no stack trace exposure)
            logger.severe("Database error occurred.");
        }

        sc.close();
    }

    // ✅ NEW: Method for testing username validation
    public static boolean isValidUsername(String username) {
        return username != null && username.matches("^\\w{3,20}$");
    }
}