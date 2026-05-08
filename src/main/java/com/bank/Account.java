package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

    // Create a new bank account for the logged-in user
    public static void createAccount(int userId, String holderName, double initialDeposit) {
        String accNumber = "ACC" + System.currentTimeMillis(); // unique account number
        String sql = "INSERT INTO accounts (user_id, account_number, holder_name, balance) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setString(2, accNumber);
            stmt.setString(3, holderName);
            stmt.setDouble(4, initialDeposit);
            stmt.executeUpdate();

            System.out.println("✅ Account created successfully!");
            System.out.println("   Account Number : " + accNumber);
            System.out.println("   Holder Name    : " + holderName);
            System.out.printf( "   Initial Balance: ₹%.2f%n", initialDeposit);

        } catch (SQLException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    // View all accounts belonging to the logged-in user
    public static void viewAccounts(int userId) {
        String sql = "SELECT account_number, holder_name, balance, created_at FROM accounts WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n--- Your Accounts ---");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("Account No : " + rs.getString("account_number"));
                System.out.println("Holder     : " + rs.getString("holder_name"));
                System.out.printf( "Balance    : ₹%.2f%n", rs.getDouble("balance"));
                System.out.println("Created At : " + rs.getTimestamp("created_at"));
                System.out.println("---------------------");
            }
            if (!found) System.out.println("No accounts found. Create one first.");

        } catch (SQLException e) {
            System.out.println("Error fetching accounts: " + e.getMessage());
        }
    }

    // Get account_id from account number (used internally)
    public static int getAccountId(String accNumber) {
        String sql = "SELECT account_id FROM accounts WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, accNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("account_id");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }
}