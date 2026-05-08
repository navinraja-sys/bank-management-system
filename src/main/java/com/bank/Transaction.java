package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {

    // Deposit money into an account
    public static void deposit(String accNumber, double amount) {
        int accId = Account.getAccountId(accNumber);
        if (accId == -1) { System.out.println("❌ Account not found."); return; }

        String updateSql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        String insertSql = "INSERT INTO transactions (account_id, type, amount) VALUES (?, 'DEPOSIT', ?)";

        try (Connection conn = DBConnection.getConnection()) {
            // Update balance
            PreparedStatement upd = conn.prepareStatement(updateSql);
            upd.setDouble(1, amount);
            upd.setString(2, accNumber);
            upd.executeUpdate();

            // Record transaction
            PreparedStatement ins = conn.prepareStatement(insertSql);
            ins.setInt(1, accId);
            ins.setDouble(2, amount);
            ins.executeUpdate();

            System.out.printf("✅ ₹%.2f deposited successfully!%n", amount);

        } catch (SQLException e) {
            System.out.println("Deposit error: " + e.getMessage());
        }
    }

    // Withdraw money from an account
    public static void withdraw(String accNumber, double amount) {
        int accId = Account.getAccountId(accNumber);
        if (accId == -1) { System.out.println("❌ Account not found."); return; }

        // Check current balance first
        String balSql = "SELECT balance FROM accounts WHERE account_number = ?";
        String updateSql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        String insertSql = "INSERT INTO transactions (account_id, type, amount) VALUES (?, 'WITHDRAWAL', ?)";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement balStmt = conn.prepareStatement(balSql);
            balStmt.setString(1, accNumber);
            ResultSet rs = balStmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (amount > balance) {
                    System.out.printf("❌ Insufficient balance. Available: ₹%.2f%n", balance);
                    return;
                }
            }

            // Update balance
            PreparedStatement upd = conn.prepareStatement(updateSql);
            upd.setDouble(1, amount);
            upd.setString(2, accNumber);
            upd.executeUpdate();

            // Record transaction
            PreparedStatement ins = conn.prepareStatement(insertSql);
            ins.setInt(1, accId);
            ins.setDouble(2, amount);
            ins.executeUpdate();

            System.out.printf("✅ ₹%.2f withdrawn successfully!%n", amount);

        } catch (SQLException e) {
            System.out.println("Withdrawal error: " + e.getMessage());
        }
    }

    // View transaction history for an account
    public static void viewHistory(String accNumber) {
        int accId = Account.getAccountId(accNumber);
        if (accId == -1) { System.out.println("❌ Account not found."); return; }

        String sql = "SELECT type, amount, transaction_date FROM transactions WHERE account_id = ? ORDER BY transaction_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n--- Transaction History ---");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("[%s] %-12s ₹%.2f%n",
                        rs.getTimestamp("transaction_date"),
                        rs.getString("type"),
                        rs.getDouble("amount"));
            }
            if (!found) System.out.println("No transactions found.");

        } catch (SQLException e) {
            System.out.println("Error fetching history: " + e.getMessage());
        }
    }
}