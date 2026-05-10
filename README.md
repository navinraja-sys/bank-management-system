# 🏦 Bank Management System

A console-based Bank Management System built using **Core Java** and **MySQL**, demonstrating real-world JDBC connectivity, OOP principles, and SQL operations.

---

## 🚀 Features

- User Registration & Login (with credential validation)
- Create Bank Accounts with unique account numbers
- Deposit & Withdraw money with balance checks
- View full Transaction History
- Clean console-based menu interface

---

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| Java 17 | Core application logic |
| JDBC | Database connectivity |
| MySQL | Data storage |
| Maven | Dependency management |
| IntelliJ IDEA | Development environment |

---

## 📁 Project Structure

```
BankManagementSystem/
├── database/
│   ├── schema.sql           # SQL schema file
├── src/main/java/com/bank/
│   ├── Main.java            # Entry point & menu
│   ├── DBConnection.java    # JDBC connection setup
│   ├── User.java            # Login & registration
│   ├── Account.java         # Account operations
│   └── Transaction.java     # Deposit, withdraw, history
├── pom.xml                  # Maven dependencies
└── README.md
```

---

## ⚙️ Setup Instructions

### Prerequisites
- Java 17+
- MySQL 8.0+
- IntelliJ IDEA (Community Edition)
- Maven

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/navinraja-sys/bank-management-system.git
   ```

2. **Set up the database**
    - Open MySQL Workbench
    - Run the SQL script provided in `database/schema.sql`

3. **Configure DB credentials**
    - Open `DBConnection.java`
    - Update `URL`, `USER`, and `PASSWORD` with your MySQL credentials

4. **Run the project**
    - Open in IntelliJ IDEA
    - Let Maven download dependencies
    - Run `Main.java`

---

## 📸 Sample Output

```
=============================
  Bank Management System
=============================
1. Login
2. Register
3. Exit
Choose: 1

Username: admin
Password: ******
✅ Login successful! Welcome, admin

1. Create Account
2. View My Accounts
3. Deposit Money
4. Withdraw Money
5. Transaction History
6. Logout
```

---

## 💡 Key Concepts Demonstrated

- Object-Oriented Programming (Classes, Encapsulation)
- JDBC PreparedStatements (prevents SQL injection)
- MySQL schema design with Foreign Keys
- Exception Handling in Java
- Unique ID generation using `System.currentTimeMillis()`

---

## 👨‍💻 Author

**Navin Raja**  
[LinkedIn](https://www.linkedin.com/in/navin-raja-nadar-1655a1298) | [GitHub](https://github.com/navinraja-sys)