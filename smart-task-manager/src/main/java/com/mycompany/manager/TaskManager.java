package com.mycompany.manager;

import com.mycompany.database.DatabaseHandler;
import com.mycompany.exceptions.InvalidTaskException;
import com.mycompany.exceptions.TaskNotFoundException;
import com.mycompany.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final List<Task> tasks;

    private final DatabaseHandler db;

    public TaskManager() {

        db = new DatabaseHandler();

        tasks = new ArrayList<>();

        refreshFromDatabase();
    }

    // REFRESH TASKS FROM DATABASE
    private void refreshFromDatabase() {

        tasks.clear();

        tasks.addAll(db.getAllTasks());
    }

    // ADD TASK
    public void addTask(Task task)
            throws InvalidTaskException {

        if (task.getTitle() == null ||
                task.getTitle().trim().isEmpty()) {

            throw new InvalidTaskException(
                    "Task title cannot be empty"
            );
        }

        if (task.getDeadline() == null ||
                task.getDeadline().isBefore(LocalDate.now())) {

            throw new InvalidTaskException(
                    "Invalid deadline"
            );
        }

        db.addTask(task);

        refreshFromDatabase();
    }

    // GET ALL TASKS
    public List<Task> getTasks() {

        refreshFromDatabase();

        return tasks;
    }

    // DELETE TASK
    public void deleteTask(int id)
            throws TaskNotFoundException {

        boolean found = false;

        for (Task task : tasks) {

            if (task.getId() == id) {

                found = true;

                break;
            }
        }

        if (!found) {

            throw new TaskNotFoundException(
                    "Task not found"
            );
        }

        db.deleteTask(id);

        refreshFromDatabase();
    }

    // MARK TASK COMPLETED
    public void markTaskCompleted(int id)
            throws TaskNotFoundException {

        boolean found = false;

        for (Task task : tasks) {

            if (task.getId() == id) {

                found = true;

                break;
            }
        }

        if (!found) {

            throw new TaskNotFoundException(
                    "Task not found"
            );
        }

        db.markTaskCompleted(id);

        refreshFromDatabase();
    }

    // UPDATE TASK
    public void updateTask(Task updatedTask)
            throws TaskNotFoundException {

        boolean found = false;

        for (Task task : tasks) {

            if (task.getId() == updatedTask.getId()) {

                found = true;

                break;
            }
        }

        if (!found) {

            throw new TaskNotFoundException(
                    "Task not found"
            );
        }

        db.updateTask(updatedTask);

        refreshFromDatabase();
    }

    // FILTER TASKS BY CATEGORY
    public List<Task> getTasksByCategory(
            String category
    ) {

        refreshFromDatabase();

        List<Task> filteredTasks =
                new ArrayList<>();

        for (Task task : tasks) {

            if (task.getCategory()
                    .equalsIgnoreCase(category)) {

                filteredTasks.add(task);
            }
        }

        return filteredTasks;
    }
}