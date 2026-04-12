// controller/TaskController.java
package com.example.taskapi.controller;

import com.example.taskapi.dto.*;
import com.example.taskapi.entity.User;
import com.example.taskapi.enums.Priority;
import com.example.taskapi.enums.TaskStatus;
import com.example.taskapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Tareas", description = "CRUD completo de tareas")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Crear una nueva tarea")
    public ResponseEntity<ApiResponse<TaskResponse>> create(
            @Valid @RequestBody TaskRequest request,
            @AuthenticationPrincipal User user) {

        TaskResponse task = taskService.create(request, user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Tarea creada", task));
    }

    @GetMapping
    @Operation(summary = "Listar tareas con filtros opcionales y paginación")
    public ResponseEntity<ApiResponse<Page<TaskResponse>>> findAll(
            @AuthenticationPrincipal User user,
            @Parameter(description = "Filtrar por estado") @RequestParam(required = false) TaskStatus status,
            @Parameter(description = "Filtrar por prioridad") @RequestParam(required = false) Priority priority,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {

        Page<TaskResponse> tasks = taskService.findAll(user, status, priority, pageable);
        return ResponseEntity.ok(ApiResponse.ok("Tareas obtenidas", tasks));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tarea por ID")
    public ResponseEntity<ApiResponse<TaskResponse>> findById(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {

        TaskResponse task = taskService.findById(id, user);
        return ResponseEntity.ok(ApiResponse.ok("Tarea encontrada", task));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tarea completa")
    public ResponseEntity<ApiResponse<TaskResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request,
            @AuthenticationPrincipal User user) {

        TaskResponse task = taskService.update(id, request, user);
        return ResponseEntity.ok(ApiResponse.ok("Tarea actualizada", task));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tarea")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {

        taskService.delete(id, user);
        return ResponseEntity.ok(ApiResponse.ok("Tarea eliminada", null));
    }
}