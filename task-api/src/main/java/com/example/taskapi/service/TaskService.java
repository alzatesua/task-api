// service/TaskService.java
package com.example.taskapi.service;

import com.example.taskapi.dto.TaskRequest;
import com.example.taskapi.dto.TaskResponse;
import com.example.taskapi.entity.Task;
import com.example.taskapi.entity.User;
import com.example.taskapi.enums.Priority;
import com.example.taskapi.enums.TaskStatus;
import com.example.taskapi.exception.ResourceNotFoundException;
import com.example.taskapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public TaskResponse create(TaskRequest request, User user) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .priority(request.getPriority())
                .user(user)
                .build();

        return toResponse(taskRepository.save(task));
    }

    @Transactional(readOnly = true)
    public Page<TaskResponse> findAll(User user, TaskStatus status, Priority priority, Pageable pageable) {
        return taskRepository
                .findByUserIdWithFilters(user.getId(), status, priority, pageable)
                .map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public TaskResponse findById(Long id, User user) {
        Task task = getTaskForUser(id, user);
        return toResponse(task);
    }

    @Transactional
    public TaskResponse update(Long id, TaskRequest request, User user) {
        Task task = getTaskForUser(id, user);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        return toResponse(taskRepository.save(task));
    }

    @Transactional
    public void delete(Long id, User user) {
        Task task = getTaskForUser(id, user);
        taskRepository.delete(task);
    }

    private Task getTaskForUser(Long id, User user) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con id: " + id));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new SecurityException("No tienes permiso para acceder a esta tarea");
        }

        return task;
    }

    private TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .userEmail(task.getUser().getEmail())
                .build();
    }
}