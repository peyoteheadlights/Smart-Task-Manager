package com.mycompany.database;

import com.mycompany.model.Task;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private final String URL =
            "jdbc:mysql://localhost:3306/smart_task_manager";

    private final String USER = "root";

    private final String PASSWORD = "1234";

    // TEST CONNECTION
    public DatabaseHandler() {

        try {

            Connection conn =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println(
                    "Database Connected Successfully"
            );

            conn.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // ADD TASK
    public void addTask(Task task) {

        String sql =
                "INSERT INTO tasks(title, deadline, priority, status, category) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn =
                        DriverManager.getConnection(
                                URL,
                                USER,
                                PASSWORD
                        );

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, task.getTitle());

            stmt.setDate(
                    2,
                    Date.valueOf(task.getDeadline())
            );

            stmt.setString(3, task.getPriority());

            stmt.setString(4, task.getStatus());

            stmt.setString(5, task.getCategory());

            stmt.executeUpdate();

            System.out.println(
                    "Task inserted successfully"
            );

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // GET ALL TASKS
    public List<Task> getAllTasks() {

        List<Task> tasks =
                new ArrayList<>();

        String sql = "SELECT * FROM tasks";

        try (
                Connection conn =
                        DriverManager.getConnection(
                                URL,
                                USER,
                                PASSWORD
                        );

                Statement stmt =
                        conn.createStatement();

                ResultSet rs =
                        stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                int id =
                        rs.getInt("id");

                String title =
                        rs.getString("title");

                LocalDate deadline =
                        rs.getDate("deadline")
                                .toLocalDate();

                String priority =
                        rs.getString("priority");

                String status =
                        rs.getString("status");

                String category =
                        rs.getString("category");

                Task task = new Task(
                        id,
                        title,
                        deadline,
                        priority,
                        status,
                        category
                );

                tasks.add(task);

                System.out.println(
                        "Loaded task: " + task
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return tasks;
    }

    // UPDATE TASK
    public void updateTask(Task task) {

        String sql =
                "UPDATE tasks SET title=?, deadline=?, priority=?, status=?, category=? WHERE id=?";

        try (
                Connection conn =
                        DriverManager.getConnection(
                                URL,
                                USER,
                                PASSWORD
                        );

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, task.getTitle());

            stmt.setDate(
                    2,
                    Date.valueOf(task.getDeadline())
            );

            stmt.setString(3, task.getPriority());

            stmt.setString(4, task.getStatus());

            stmt.setString(5, task.getCategory());

            stmt.setInt(6, task.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // DELETE TASK
    public void deleteTask(int id) {

        String sql =
                "DELETE FROM tasks WHERE id=?";

        try (
                Connection conn =
                        DriverManager.getConnection(
                                URL,
                                USER,
                                PASSWORD
                        );

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // MARK COMPLETED
    public void markTaskCompleted(int id) {

        String sql =
                "UPDATE tasks SET status='Completed' WHERE id=?";

        try (
                Connection conn =
                        DriverManager.getConnection(
                                URL,
                                USER,
                                PASSWORD
                        );

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}