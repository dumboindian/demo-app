package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

    private final DatabaseService databaseService;

    public App(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public boolean userExists(String username) {
        return databaseService.userExists(username);
    }
}

// DatabaseService interface
interface DatabaseService {
    boolean userExists(String username);
}

// Optional: Implementation
class DatabaseServiceImpl implements DatabaseService {
    private final Connection conn;

    public DatabaseServiceImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean userExists(String username) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM users WHERE username=?")) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
}