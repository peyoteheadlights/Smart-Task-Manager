package com.mycompany.manager;

import com.mycompany.exceptions.InvalidTaskException;
import com.mycompany.model.Task;
import com.mycompany.network.TaskClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final TaskClient client;

    public TaskManager() {

        client = new TaskClient();
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

        client.addTask(task);
    }

    // GET TASKS
    public List<Task> getTasks() {

        return client.getTasks();
    }

    // DELETE TASK
    public void deleteTask(int id) {

        client.deleteTask(id);
    }

    // COMPLETE TASK
    public void markTaskCompleted(int id) {

        client.completeTask(id);
    }

    // UPDATE TASK
    public void updateTask(Task updatedTask) {

        deleteTask(updatedTask.getId());

        client.addTask(updatedTask);
    }

    // FILTER
    public List<Task> getTasksByCategory(
            String category
    ) {

        List<Task> filtered =
                new ArrayList<>();

        for (Task task : getTasks()) {

            if (task.getCategory()
                    .equalsIgnoreCase(category)) {

                filtered.add(task);
            }
        }

        return filtered;
    }
}