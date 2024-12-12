package com.example.task_manager.repository;

import com.example.task_manager.entity.TaskList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskListRepository extends MongoRepository<TaskList, String> {
    TaskList findByUsername(String username);
}
