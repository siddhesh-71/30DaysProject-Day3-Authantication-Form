# 🔐 Login & Registration System – Java Swing + MySQL

This is a simple Login and Registration System built using **Java Swing** for GUI and **MySQL** for backend database integration using JDBC.

## 📁 Features

- 🌟 Stylish Login and Registration Forms
- ✅ User Authentication with MySQL Database
- 🔒 Password validation
- 🖥️ Dashboard UI after login
- 🎨 Clean and modern user interface

## 🛠️ Tech Stack

- Java Swing (GUI)
- MySQL (Database)
- JDBC (Java Database Connectivity)

## 💾 Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/LoginRegisterDashboard.git
```

### 2. Add MySQL JDBC Connector
- Download from: [https://dev.mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/)
- Add `mysql-connector-java-8.x.xx.jar` to your classpath.

### 3. Create the Database
```sql
CREATE DATABASE user_db;
USE user_db;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100)
);
```

### 4. Update DB Credentials
Inside `LoginForm.java` and `RegisterForm.java`, update:
```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/user_db", "root", "your_password");
```

### 5. Compile and Run
```bash
javac -cp .;mysql-connector-java-8.x.xx.jar LoginForm.java RegisterForm.java Dashboard.java
java -cp .;mysql-connector-java-8.x.xx.jar LoginForm
```

> Use `:` instead of `;` on Linux/macOS

## 📸 Screenshots
*Add your screenshots here if available*

## 🤝 Contributing
Pull requests are welcome! Feel free to open issues for suggestions or bugs.

## 📄 License
This project is open-source and free to use for learning purposes.