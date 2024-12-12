package com.example.task_manager.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class Task {

    @Id
    private String id;

    private String title;
    private String description;
    private Date taskDate;
//    private String reminderBefore
    private String category;
    private boolean isCompleted;

    Task(){}

    public Task(String title, String description, Date taskDate, String category, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.taskDate = taskDate;
        this.category = category;
        this.isCompleted = isCompleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskDate=" + taskDate +
                ", category='" + category + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
