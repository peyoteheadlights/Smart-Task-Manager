package com.mycompany.model;

import java.time.LocalDate;

public class Task {

    private int id;
    private String title;
    private LocalDate deadline;
    private String priority;
    private String status;
    private String category;

    public Task(
            int id,
            String title,
            LocalDate deadline,
            String priority,
            String status,
            String category
    ) {

        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.priority = priority;
        this.status = status;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategory(String category) {
        this.category = category;
    }

@Override
public String toString() {

    return title + " | " +
            priority + " | " +
            status + " | " +
            category + " | " +
            deadline;
}
}