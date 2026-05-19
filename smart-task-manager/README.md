# Smart Task Manager

A JavaFX desktop application for managing tasks with MySQL database integration.

## Features

- Add tasks
- Update existing tasks
- Delete tasks
- Mark tasks as completed
- Filter tasks by category
- Store tasks in MySQL database
- Automatic category loading
- Input validation
- JavaFX graphical user interface

---

# Technologies Used

- Java 24
- JavaFX
- Maven
- MySQL
- JDBC
- JUnit 5

---

# Project Structure

```text
smart-task-manager
│
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── mycompany
│   │               ├── app
│   │               ├── database
│   │               ├── exceptions
│   │               ├── manager
│   │               └── model
│   │
│   └── test
│       └── java
│           └── com
│               └── mycompany
│                   ├── database
│                   ├── manager
│                   └── model
│
├── pom.xml
└── README.md
```

---

# Database Setup

## Create Database

Run this SQL script in MySQL Workbench:

```sql
CREATE DATABASE IF NOT EXISTS smart_task_manager;

USE smart_task_manager;

CREATE TABLE IF NOT EXISTS tasks (

    id INT PRIMARY KEY AUTO_INCREMENT,

    title VARCHAR(255) NOT NULL,

    deadline DATE NOT NULL,

    priority VARCHAR(50),

    status VARCHAR(50),

    category VARCHAR(100)
);
```

---

# Configure Database Connection

Open:

```text
DatabaseHandler.java
```

Change:

```java
private final String USER = "root";

private final String PASSWORD = "YOUR_PASSWORD";
```

to your MySQL username and password.

---

# JavaFX Setup

Download JavaFX SDK:

https://gluonhq.com/products/javafx/

Extract it to:

```text
C:\javafx-sdk-24.0.2
```

---

# Run The Project

Open terminal inside project folder:

```powershell
cd smart-task-manager
```

Run:

```powershell
mvn clean javafx:run
```

---

# Running Tests

Run all tests using:

```powershell
mvn test
```

Expected result:

```text
BUILD SUCCESS
```

---

# Example Tasks

| Title | Priority | Category |
|---|---|---|
| Finish OOP Assignment | High | College |
| Buy Groceries | Medium | Personal |
| Prepare Presentation | High | Work |

---

# Possible Future Improvements

- Search tasks
- Due date reminders
- User authentication
- Dark mode
- Task statistics dashboard

---

# Author

Mark Ramy

Advanced Programming 12th Project
