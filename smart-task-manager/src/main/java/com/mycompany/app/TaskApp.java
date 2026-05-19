package com.mycompany.app;

import com.mycompany.exceptions.InvalidTaskException;
import com.mycompany.manager.TaskManager;
import com.mycompany.model.Task;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class TaskApp extends Application {

    private TaskManager manager;

    private ObservableList<Task> taskList;

    private ComboBox<String> filterBox;

    private ListView<Task> listView;

    @Override
    public void start(Stage stage) {

        manager = new TaskManager();

        taskList = FXCollections.observableArrayList();

        Label titleLabel = new Label("Task Title");

        TextField titleField = new TextField();

        Label priorityLabel = new Label("Priority");

        ComboBox<String> priorityBox =
                new ComboBox<>();

        priorityBox.getItems().addAll(
                "High",
                "Medium",
                "Low"
        );

        Label categoryLabel =
                new Label("Category");

        TextField categoryField =
                new TextField();

        Label deadlineLabel =
                new Label("Deadline");

        DatePicker datePicker =
                new DatePicker();

        Button addButton =
                new Button("Add Task");

        Button updateButton =
                new Button("Update Task");

        Button deleteButton =
                new Button("Delete Task");

        Button completeButton =
                new Button("Mark Completed");

        Label filterLabel =
                new Label("Filter By Category");

        filterBox = new ComboBox<>();

        listView = new ListView<>();

        // CUSTOM DISPLAY
        listView.setCellFactory(param -> new ListCell<Task>() {

            @Override
            protected void updateItem(Task task, boolean empty) {

                super.updateItem(task, empty);

                if (empty || task == null) {

                    setText(null);

                } else {

                    int displayIndex =
                            getIndex() + 1;

                    setText(
                            displayIndex + " | " +
                            task.getTitle() + " | " +
                            task.getPriority() + " | " +
                            task.getStatus() + " | " +
                            task.getCategory() + " | " +
                            task.getDeadline()
                    );
                }
            }
        });

        refreshTasks();

        updateCategoryFilter();

        // ADD TASK
        addButton.setOnAction(event -> {

            try {

                Task task = new Task(
                        0,
                        titleField.getText(),
                        datePicker.getValue(),
                        priorityBox.getValue(),
                        "Pending",
                        categoryField.getText()
                );

                manager.addTask(task);

                refreshTasks();

                updateCategoryFilter();

                clearFields(
                        titleField,
                        categoryField,
                        priorityBox,
                        datePicker
                );

                showAlert(
                        "Task Added Successfully"
                );

            } catch (InvalidTaskException ex) {

                showAlert(ex.getMessage());
            }
        });

        // DELETE TASK
        deleteButton.setOnAction(event -> {

            Task selectedTask =
                    listView.getSelectionModel()
                            .getSelectedItem();

            if (selectedTask == null) {

                showAlert("Select a task");

                return;
            }

            try {

                manager.deleteTask(
                        selectedTask.getId()
                );

                refreshTasks();

                updateCategoryFilter();

                showAlert("Task Deleted");

            } catch (Exception ex) {

                showAlert(ex.getMessage());
            }
        });

        // COMPLETE TASK
        completeButton.setOnAction(event -> {

            Task selectedTask =
                    listView.getSelectionModel()
                            .getSelectedItem();

            if (selectedTask == null) {

                showAlert("Select a task");

                return;
            }

            try {

                manager.markTaskCompleted(
                        selectedTask.getId()
                );

                refreshTasks();

                showAlert(
                        "Task Marked Completed"
                );

            } catch (Exception ex) {

                showAlert(ex.getMessage());
            }
        });

        // UPDATE TASK
        updateButton.setOnAction(event -> {

            Task selectedTask =
                    listView.getSelectionModel()
                            .getSelectedItem();

            if (selectedTask == null) {

                showAlert("Select a task");

                return;
            }

            try {

                selectedTask.setTitle(
                        titleField.getText()
                );

                selectedTask.setPriority(
                        priorityBox.getValue()
                );

                selectedTask.setCategory(
                        categoryField.getText()
                );

                selectedTask.setDeadline(
                        datePicker.getValue()
                );

                manager.updateTask(selectedTask);

                refreshTasks();

                updateCategoryFilter();

                showAlert(
                        "Task Updated Successfully"
                );

            } catch (Exception ex) {

                showAlert(ex.getMessage());
            }
        });

        // LOAD TASK INTO INPUTS
        listView.setOnMouseClicked(event -> {

            Task selectedTask =
                    listView.getSelectionModel()
                            .getSelectedItem();

            if (selectedTask != null) {

                titleField.setText(
                        selectedTask.getTitle()
                );

                categoryField.setText(
                        selectedTask.getCategory()
                );

                priorityBox.setValue(
                        selectedTask.getPriority()
                );

                datePicker.setValue(
                        selectedTask.getDeadline()
                );
            }
        });

        // FILTER TASKS
        filterBox.setOnAction(event -> {

            String selectedCategory =
                    filterBox.getValue();

            if (selectedCategory == null) {

                return;
            }

            if (selectedCategory.equals("All")) {

                taskList.setAll(
                        manager.getTasks()
                );

            } else {

                taskList.setAll(
                        manager.getTasksByCategory(
                                selectedCategory
                        )
                );
            }

            listView.refresh();
        });

        VBox root = new VBox(10);

        root.setPadding(new Insets(20));

        root.getChildren().addAll(

                titleLabel,
                titleField,

                priorityLabel,
                priorityBox,

                categoryLabel,
                categoryField,

                deadlineLabel,
                datePicker,

                addButton,
                updateButton,
                deleteButton,
                completeButton,

                filterLabel,
                filterBox,

                listView
        );

        Scene scene =
                new Scene(root, 700, 700);

        stage.setTitle(
                "Smart Task Manager"
        );

        stage.setScene(scene);

        stage.show();
    }

    // REFRESH TASKS
    private void refreshTasks() {

        taskList.setAll(
                manager.getTasks()
        );

        listView.setItems(taskList);

        listView.refresh();
    }

    // AUTO CATEGORY FILTER
    private void updateCategoryFilter() {

        Set<String> categories =
                new HashSet<>();

        categories.add("All");

        for (Task task : manager.getTasks()) {

            categories.add(
                    task.getCategory()
            );
        }

        filterBox.getItems().clear();

        filterBox.getItems().addAll(
                categories
        );

        filterBox.setValue("All");
    }

    // CLEAR INPUTS
    private void clearFields(
            TextField titleField,
            TextField categoryField,
            ComboBox<String> priorityBox,
            DatePicker datePicker
    ) {

        titleField.clear();

        categoryField.clear();

        priorityBox.setValue(null);

        datePicker.setValue(null);
    }

    // ALERT
    private void showAlert(String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("Message");

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void main(String[] args) {

        launch(args);
    }
}