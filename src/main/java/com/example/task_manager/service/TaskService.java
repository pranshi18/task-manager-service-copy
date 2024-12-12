package com.example.task_manager.service;

import com.example.task_manager.entity.Task;
import com.example.task_manager.entity.TaskList;

public interface TaskService {

    TaskList addTask(Task task, String username);

    TaskList addCategory(String category, String username);

    TaskList getAllTasks(String username);

    void updateTask(String username, Task task);
}
