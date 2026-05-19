package com.mycompany.database;

import com.mycompany.model.Task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseHandlerTest {

    @Test
    public void testDatabaseConnection() {

        DatabaseHandler db =
                new DatabaseHandler();

        assertNotNull(db);
    }

    @Test
    public void testAddAndRetrieveTask() {

        DatabaseHandler db =
                new DatabaseHandler();

        Task task = new Task(
                0,
                "DB Test",
                LocalDate.now().plusDays(1),
                "High",
                "Pending",
                "Testing"
        );

        db.addTask(task);

        List<Task> tasks =
                db.getAllTasks();

        assertFalse(tasks.isEmpty());
    }
}