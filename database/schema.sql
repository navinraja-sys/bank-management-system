-- Step 1: Create and use the database
CREATE DATABASE IF NOT EXISTS bank_management;
USE bank_management;

-- Step 2: Users table (login system)
CREATE TABLE users (
    user_id    INT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(50)  NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Step 3: Accounts table
CREATE TABLE accounts (
    account_id     INT AUTO_INCREMENT PRIMARY KEY,
    user_id        INT NOT NULL,
    account_number VARCHAR(20) NOT NULL UNIQUE,
    holder_name    VARCHAR(100) NOT NULL,
    balance        DECIMAL(10, 2) DEFAULT 0.00,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Step 4: Transactions table
CREATE TABLE transactions (
    transaction_id   INT AUTO_INCREMENT PRIMARY KEY,
    account_id       INT NOT NULL,
    type             ENUM('DEPOSIT', 'WITHDRAWAL') NOT NULL,
    amount           DECIMAL(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

-- Step 5: Insert a test user (username: admin, password: admin123)
-- Test user for development purposes only. Remove before production use.
INSERT INTO users (username, password) VALUES ('admin', 'admin123');