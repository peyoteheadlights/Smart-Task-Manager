package com.mycompany.manager;

import com.mycompany.exceptions.InvalidTaskException;
import com.mycompany.exceptions.TaskNotFoundException;
import com.mycompany.model.Task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    @Test
    public void testAddTask()
            throws InvalidTaskException {

        TaskManager manager =
                new TaskManager();

        int oldSize =
                manager.getTasks().size();

        Task task = new Task(
                0,
                "Test Task",
                LocalDate.now().plusDays(1),
                "High",
                "Pending",
                "Testing"
        );

        manager.addTask(task);

        int newSize =
                manager.getTasks().size();

        assertEquals(
                oldSize + 1,
                newSize
        );
    }

    @Test
    public void testInvalidTaskTitle() {

        TaskManager manager =
                new TaskManager();

        Task task = new Task(
                0,
                "",
                LocalDate.now().plusDays(1),
                "High",
                "Pending",
                "Testing"
        );

        assertThrows(
                InvalidTaskException.class,
                () -> manager.addTask(task)
        );
    }

    @Test
    public void testDeleteTask()
            throws Exception {

        TaskManager manager =
                new TaskManager();

        Task task = new Task(
                0,
                "Delete Test",
                LocalDate.now().plusDays(1),
                "Medium",
                "Pending",
                "Testing"
        );

        manager.addTask(task);

        Task addedTask =
                manager.getTasks()
                        .get(manager.getTasks().size() - 1);

        manager.deleteTask(
                addedTask.getId()
        );

        boolean exists = false;

        for (Task t : manager.getTasks()) {

            if (t.getId() ==
                    addedTask.getId()) {

                exists = true;
            }
        }

        assertFalse(exists);
    }

    @Test
    public void testMarkCompleted()
            throws Exception {

        TaskManager manager =
                new TaskManager();

        Task task = new Task(
                0,
                "Completion Test",
                LocalDate.now().plusDays(1),
                "Low",
                "Pending",
                "Testing"
        );

        manager.addTask(task);

        Task addedTask =
                manager.getTasks()
                        .get(manager.getTasks().size() - 1);

        manager.markTaskCompleted(
                addedTask.getId()
        );

        assertEquals(
                "Completed",
                manager.getTasks()
                        .get(manager.getTasks().size() - 1)
                        .getStatus()
        );
    }

    @Test
    public void testFilterByCategory()
            throws InvalidTaskException {

        TaskManager manager =
                new TaskManager();

        Task task = new Task(
                0,
                "Category Test",
                LocalDate.now().plusDays(1),
                "High",
                "Pending",
                "College"
        );

        manager.addTask(task);

        assertFalse(
                manager.getTasksByCategory("College")
                        .isEmpty()
        );
    }
}