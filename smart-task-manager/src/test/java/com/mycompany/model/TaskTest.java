package com.mycompany.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskCreation() {

        Task task = new Task(
                1,
                "Finish Project",
                LocalDate.of(2026, 5, 25),
                "High",
                "Pending",
                "College"
        );

        assertEquals(1, task.getId());

        assertEquals(
                "Finish Project",
                task.getTitle()
        );

        assertEquals(
                "High",
                task.getPriority()
        );

        assertEquals(
                "Pending",
                task.getStatus()
        );

        assertEquals(
                "College",
                task.getCategory()
        );
    }

    @Test
    public void testSetters() {

        Task task = new Task(
                1,
                "Old Task",
                LocalDate.now(),
                "Low",
                "Pending",
                "Personal"
        );

        task.setTitle("New Task");

        task.setPriority("High");

        task.setStatus("Completed");

        task.setCategory("Work");

        assertEquals(
                "New Task",
                task.getTitle()
        );

        assertEquals(
                "High",
                task.getPriority()
        );

        assertEquals(
                "Completed",
                task.getStatus()
        );

        assertEquals(
                "Work",
                task.getCategory()
        );
    }
}