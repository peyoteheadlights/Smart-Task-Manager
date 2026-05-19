# Smart Task Manager (Client-Server Version)

A JavaFX desktop application for task management using a TCP client-server architecture with MySQL database integration.

This project demonstrates:
- Object-Oriented Programming
- JavaFX GUI development
- MySQL database integration
- TCP socket networking
- Client-server architecture
- Maven project management
- Unit testing with JUnit

---

# Features

## Task Management
- Add tasks
- Update tasks
- Delete tasks
- Mark tasks as completed
- Filter tasks by category
- Automatic task numbering
- Input validation

## Networking Features
- TCP socket communication
- Client-server architecture
- Centralized task management server
- Custom communication protocol
- Multiple client support

## Database Features
- MySQL database storage
- Persistent task saving
- Automatic task retrieval

---

# Technologies Used

| Technology | Purpose |
|---|---|
| Java 24 | Main programming language |
| JavaFX | GUI framework |
| Maven | Dependency management |
| MySQL | Database |
| JDBC | Database connectivity |
| TCP Sockets | Networking |
| JUnit 5 | Testing |

---

# System Architecture

```text
JavaFX Client App
        │
        │ TCP Socket Communication
        ▼
Task Server
        │
        ▼
MySQL Database
```

---

# Communication Protocol

The client communicates with the server using a custom text-based protocol.

## Protocol Format

```text
COMMAND|DATA1|DATA2|DATA3
```

## Supported Commands

| Command | Description |
|---|---|
| ADD | Add a task |
| GET_ALL | Retrieve all tasks |
| DELETE | Delete task |
| COMPLETE | Mark task completed |

---

# Example Messages

## Add Task

```text
ADD|Finish Assignment|2026-05-20|High|Pending|College
```

## Get Tasks

```text
GET_ALL
```

## Delete Task

```text
DELETE|3
```

## Mark Completed

```text
COMPLETE|5
```

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
│   │               │   └── TaskApp.java
│   │               │
│   │               ├── network
│   │               │   ├── TaskServer.java
│   │               │   └── TaskClient.java
│   │               │
│   │               ├── database
│   │               ├── exceptions
│   │               ├── manager
│   │               └── model
│   │
│   └── test
│       └── java
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

to your own MySQL credentials.

---

# Running The Project

## Step 1 — Open Project Folder

```powershell
cd smart-task-manager
```

---

# Step 2 — Start The Server

Open terminal 1:

```powershell
mvn exec:java "-Dexec.mainClass=com.mycompany.network.TaskServer"
```

Expected output:

```text
Server Started...
Database Connected Successfully
```

---

# Step 3 — Start The Client Application

Open terminal 2:

```powershell
mvn javafx:run
```

The JavaFX GUI should open successfully.

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
| Study Networking | High | University |

---

# Example Workflow

1. User adds task through JavaFX GUI
2. Client sends TCP request to server
3. Server processes request
4. Server updates MySQL database
5. Updated tasks are returned to client
6. GUI refreshes automatically

---

# Future Improvements

- User authentication
- Real-time synchronization
- Task reminders
- Search functionality
- Dark mode
- Dashboard statistics
- Encrypted communication
- REST API version

---

# Author

Mark Ramy

Advanced Programming Project
```
