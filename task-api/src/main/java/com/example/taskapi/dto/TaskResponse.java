package com.example.taskapi.dto;

import com.example.taskapi.enums.Priority;
import com.example.taskapi.enums.TaskStatus;

import java.time.LocalDateTime;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userEmail;

    public TaskResponse() {}

    public TaskResponse(Long id, String title, String description, TaskStatus status,
                        Priority priority, LocalDateTime createdAt,
                        LocalDateTime updatedAt, String userEmail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userEmail = userEmail;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public Priority getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public String getUserEmail() { return userEmail; }

    public static TaskResponseBuilder builder() { return new TaskResponseBuilder(); }

    public static class TaskResponseBuilder {
        private Long id;
        private String title;
        private String description;
        private TaskStatus status;
        private Priority priority;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String userEmail;

        public TaskResponseBuilder id(Long id) { this.id = id; return this; }
        public TaskResponseBuilder title(String title) { this.title = title; return this; }
        public TaskResponseBuilder description(String description) { this.description = description; return this; }
        public TaskResponseBuilder status(TaskStatus status) { this.status = status; return this; }
        public TaskResponseBuilder priority(Priority priority) { this.priority = priority; return this; }
        public TaskResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public TaskResponseBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public TaskResponseBuilder userEmail(String userEmail) { this.userEmail = userEmail; return this; }

        public TaskResponse build() {
            return new TaskResponse(id, title, description, status, priority, createdAt, updatedAt, userEmail);
        }
    }
}
