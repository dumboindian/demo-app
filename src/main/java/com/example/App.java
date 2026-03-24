package com.example;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private static final String USERNAME_PATTERN = "^\\w{3,20}$";

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            LOGGER.info("Enter username: ");
            String username = sc.nextLine();

            // ✅ Input validation
            if (username == null || !username.matches(USERNAME_PATTERN)) {
                LOGGER.warning("Invalid username format.");
                return;
            }

            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");

            if (url == null || user == null || password == null) {
                LOGGER.severe("Database configuration missing.");
                return;
            }

            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(
                         "SELECT username FROM users WHERE username = ?")) {

                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        LOGGER.info("User found.");
                    } else {
                        LOGGER.info("User not found.");
                    }
                }

            } catch (SQLException e) {
                LOGGER.severe("Database error.");
            }
        }
    }
}