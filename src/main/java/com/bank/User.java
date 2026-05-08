package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private int userId;
    private String username;

    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public int getUserId()     { return userId; }
    public String getUsername(){ return username; }

    // Login: returns a User object if credentials match, else null
    public static User login(String username, String password) {
        String sql = "SELECT user_id, username FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("✅ Login successful! Welcome, " + rs.getString("username"));
                return new User(rs.getInt("user_id"), rs.getString("username"));
            } else {
                System.out.println("❌ Invalid username or password.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return null;
        }
    }

    // Register a new user
    public static boolean register(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Registration successful! You can now log in.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
        }
        return false;
    }
}