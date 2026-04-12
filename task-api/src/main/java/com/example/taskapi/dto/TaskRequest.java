package com.example.taskapi.dto;

import com.example.taskapi.enums.Priority;
import com.example.taskapi.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequest {
    @NotBlank(message = "El título es obligatorio")
    private String title;

    private String description;

    private TaskStatus status = TaskStatus.PENDING;

    private Priority priority = Priority.MEDIUM;
}
