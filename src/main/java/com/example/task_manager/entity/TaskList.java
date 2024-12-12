    package com.example.task_manager.entity;

    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;

    import java.util.List;

    @Document(collection = "task-list-data")
    public class TaskList {

        @Id
        private String id;
        private String username;
        private List<Task> task_list;
        private List<String> categories;

        TaskList(){}

        public TaskList(String username, List<Task> task_list, List<String> categories) {
            this.username = username;
            this.task_list = task_list;
            this.categories = categories;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<Task> getTask_list() {
            return task_list;
        }

        public void setTask_list(List<Task> task_list) {
            this.task_list = task_list;
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

        @Override
        public String toString() {
            return "TaskList{" +
                    "username='" + username + '\'' +
                    ", task_list=" + task_list +
                    ", categories=" + categories +
                    '}';
        }
    }
