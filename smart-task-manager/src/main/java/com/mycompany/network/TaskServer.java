package com.mycompany.network;

import com.mycompany.database.DatabaseHandler;
import com.mycompany.model.Task;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

public class TaskServer {

    public static void main(String[] args) {

        try {

            ServerSocket serverSocket =
                    new ServerSocket(5000);

            System.out.println(
                    "Server Started..."
            );

            DatabaseHandler db =
                    new DatabaseHandler();

            while (true) {

                Socket socket =
                        serverSocket.accept();

                System.out.println(
                        "Client Connected"
                );

                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()
                                )
                        );

                PrintWriter out =
                        new PrintWriter(
                                socket.getOutputStream(),
                                true
                        );

                String request =
                        in.readLine();

                if (request == null) {

                    socket.close();

                    continue;
                }

                String[] parts =
                        request.split("\\|");

                String command =
                        parts[0];

                // ADD TASK
                if (command.equals("ADD")) {

                    Task task = new Task(
                            0,
                            parts[1],
                            LocalDate.parse(parts[2]),
                            parts[3],
                            parts[4],
                            parts[5]
                    );

                    db.addTask(task);

                    out.println("SUCCESS");

                }

                // GET TASKS
                else if (command.equals("GET_ALL")) {

                    List<Task> tasks =
                            db.getAllTasks();

                    for (Task task : tasks) {

                        out.println(
                                task.getId() + "|" +
                                task.getTitle() + "|" +
                                task.getPriority() + "|" +
                                task.getStatus() + "|" +
                                task.getCategory() + "|" +
                                task.getDeadline()
                        );
                    }

                    out.println("END");
                }

                // DELETE TASK
                else if (command.equals("DELETE")) {

                    int id =
                            Integer.parseInt(parts[1]);

                    db.deleteTask(id);

                    out.println("DELETED");
                }

                // COMPLETE TASK
                else if (command.equals("COMPLETE")) {

                    int id =
                            Integer.parseInt(parts[1]);

                    db.markTaskCompleted(id);

                    out.println("COMPLETED");
                }

                socket.close();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}