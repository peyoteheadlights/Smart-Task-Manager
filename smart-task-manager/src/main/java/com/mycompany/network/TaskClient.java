package com.mycompany.network;

import com.mycompany.model.Task;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskClient {

    private final String HOST = "localhost";

    private final int PORT = 5000;

    // ADD TASK
    public void addTask(Task task) {

        try {

            Socket socket =
                    new Socket(HOST, PORT);

            PrintWriter out =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true
                    );

            out.println(
                    "ADD|" +
                    task.getTitle() + "|" +
                    task.getDeadline() + "|" +
                    task.getPriority() + "|" +
                    task.getStatus() + "|" +
                    task.getCategory()
            );

            socket.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // GET TASKS
    public List<Task> getTasks() {

        List<Task> tasks =
                new ArrayList<>();

        try {

            Socket socket =
                    new Socket(HOST, PORT);

            PrintWriter out =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true
                    );

            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()
                            )
                    );

            out.println("GET_ALL");

            String line;

            while (!(line = in.readLine())
                    .equals("END")) {

                String[] parts =
                        line.split("\\|");

                Task task = new Task(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        LocalDate.parse(parts[5]),
                        parts[2],
                        parts[3],
                        parts[4]
                );

                tasks.add(task);
            }

            socket.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return tasks;
    }

    // DELETE TASK
    public void deleteTask(int id) {

        try {

            Socket socket =
                    new Socket(HOST, PORT);

            PrintWriter out =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true
                    );

            out.println("DELETE|" + id);

            socket.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // COMPLETE TASK
    public void completeTask(int id) {

        try {

            Socket socket =
                    new Socket(HOST, PORT);

            PrintWriter out =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true
                    );

            out.println("COMPLETE|" + id);

            socket.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}