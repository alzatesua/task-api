// repository/TaskRepository.java
package com.example.taskapi.repository;

import com.example.taskapi.entity.Task;
import com.example.taskapi.enums.Priority;
import com.example.taskapi.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByUserId(Long userId, Pageable pageable);

    Page<Task> findByUserIdAndStatus(Long userId, TaskStatus status, Pageable pageable);

    Page<Task> findByUserIdAndPriority(Long userId, Priority priority, Pageable pageable);

    Page<Task> findByUserIdAndStatusAndPriority(
            Long userId, TaskStatus status, Priority priority, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId " +
           "AND (:status IS NULL OR t.status = :status) " +
           "AND (:priority IS NULL OR t.priority = :priority)")
    Page<Task> findByUserIdWithFilters(
            @Param("userId") Long userId,
            @Param("status") TaskStatus status,
            @Param("priority") Priority priority,
            Pageable pageable);
}