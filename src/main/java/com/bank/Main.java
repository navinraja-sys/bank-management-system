package com.bank;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=============================");
        System.out.println("  Bank Management System");
        System.out.println("=============================");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    User user = handleLogin();
                    if (user != null) showDashboard(user);
                }
                case 2 -> handleRegister();
                case 3 -> { System.out.println("Goodbye!"); running = false; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // --- Login ---
    static User handleLogin() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        return User.login(username, password);
    }

    // --- Register ---
    static void handleRegister() {
        System.out.print("Choose a username: ");
        String username = sc.nextLine();
        System.out.print("Choose a password: ");
        String password = sc.nextLine();
        User.register(username, password);
    }

    // --- Dashboard after login ---
    static void showDashboard(User user) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n=============================");
            System.out.println("  Welcome, " + user.getUsername());
            System.out.println("=============================");
            System.out.println("1. Create Account");
            System.out.println("2. View My Accounts");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transaction History");
            System.out.println("6. Logout");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Account Holder Name: ");
                    String name = sc.nextLine();
                    System.out.print("Initial Deposit (₹): ");
                    double deposit = sc.nextDouble();
                    sc.nextLine();
                    Account.createAccount(user.getUserId(), name, deposit);
                }
                case 2 -> Account.viewAccounts(user.getUserId());
                case 3 -> {
                    System.out.print("Account Number: ");
                    String acc = sc.nextLine();
                    System.out.print("Amount to Deposit (₹): ");
                    double amt = sc.nextDouble();
                    sc.nextLine();
                    Transaction.deposit(acc, amt);
                }
                case 4 -> {
                    System.out.print("Account Number: ");
                    String acc = sc.nextLine();
                    System.out.print("Amount to Withdraw (₹): ");
                    double amt = sc.nextDouble();
                    sc.nextLine();
                    Transaction.withdraw(acc, amt);
                }
                case 5 -> {
                    System.out.print("Account Number: ");
                    String acc = sc.nextLine();
                    Transaction.viewHistory(acc);
                }
                case 6 -> { System.out.println("Logged out."); loggedIn = false; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}