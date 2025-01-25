package com.example.task_manager.controller;

import com.example.task_manager.entity.Task;
import com.example.task_manager.entity.TaskList;
import com.example.task_manager.entity.User;
import com.example.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class RestController {

    @Autowired
    private TaskService taskService;

    private String getAuthenticatedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() throws Exception{
        return ResponseEntity.ok("HEllo World");
    }

    @PostMapping("/addTask")
    public TaskList addTask(@RequestBody Task task){
        String username = getAuthenticatedUsername();
        return taskService.addTask(task, username);
    }

    @GetMapping("/addCategory")
    public ResponseEntity<TaskList> addCategory(@RequestParam String category){
        String username = getAuthenticatedUsername();
        try{
            TaskList updatedTaskList = taskService.addCategory(category, username);
            return ResponseEntity.ok(updatedTaskList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/getTaskList")
    public ResponseEntity<TaskList> getAllTasks(){
        String username = getAuthenticatedUsername();
        try{
            TaskList taskList = taskService.getAllTasks(username);
            return ResponseEntity.ok(taskList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/updateTask")
    public ResponseEntity<Map<String,String>> updateTask(@RequestBody Task task){
        String username = getAuthenticatedUsername();
        try{
            taskService.updateTask(username, task);
            return ResponseEntity.ok(Map.of("message","updatedSuccesfully"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/deleteCompletedTasks")
    public ResponseEntity<Map<String, String>> deleteCompletedTasks(){
        String username = getAuthenticatedUsername();
        try{
            taskService.deleteCompletedTasks(username);
            return ResponseEntity.ok(Map.of("messsage", "deleted completed tasks"));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
