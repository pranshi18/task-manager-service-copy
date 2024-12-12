package com.example.task_manager.service;

import com.example.task_manager.entity.Task;
import com.example.task_manager.entity.TaskList;
import com.example.task_manager.repository.TaskListRepository;
import com.example.task_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TaskList addTask(Task task, String username) {

        TaskList taskDetails = taskListRepository.findByUsername(username);
        try{
            if (taskDetails.getTask_list() == null) {
                taskDetails.setTask_list(new ArrayList<>()); // Initialize the task list if it's null
            }
            task.setId(UUID.randomUUID().toString());
            taskDetails.getTask_list().add(task);
            taskListRepository.save(taskDetails);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return taskDetails;
    }

    @Override
    public TaskList addCategory(String category, String username) {
        TaskList taskDetails = taskListRepository.findByUsername(username);
        try{
            List<String> categories = taskDetails.getCategories();
            if(categories==null)
                    categories = new ArrayList<>();
            categories.add(category);
            taskDetails.setCategories(categories);
            taskListRepository.save(taskDetails);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return taskDetails;
    }

    @Override
    public TaskList getAllTasks(String username) {
        return taskListRepository.findByUsername(username);
    }

    @Override
    public void updateTask(String username, Task task) {
        TaskList taskDetails = taskListRepository.findByUsername(username);

        taskDetails.getTask_list().forEach(existingTask -> {
            if(existingTask.getId().equals(task.getId())){
                existingTask.setTitle(task.getTitle());
                existingTask.setDescription(task.getDescription());
                existingTask.setCategory(task.getCategory());
                existingTask.setCompleted(task.isCompleted());
                existingTask.setTaskDate(task.getTaskDate());
            }
        });

        taskListRepository.save(taskDetails);
    }
}
