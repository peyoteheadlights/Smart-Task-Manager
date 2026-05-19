CREATE DATABASE IF NOT EXISTS smart_task_manager;

USE smart_task_manager;

DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks (

    id INT PRIMARY KEY AUTO_INCREMENT,

    title VARCHAR(255) NOT NULL,

    deadline DATE,

    priority VARCHAR(50),

    status VARCHAR(50),

    category VARCHAR(100)
);

INSERT INTO tasks(title, deadline, priority, status, category)
VALUES
('Finish Java Project', '2026-05-25', 'High', 'Pending', 'College'),

('Study Databases', '2026-05-22', 'Medium', 'Pending', 'Study'),

('Buy Groceries', '2026-05-20', 'Low', 'Completed', 'Personal');