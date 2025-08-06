package com.example.demo.Repository;

import com.example.demo.Model.Task;
import com.example.demo.enums.Priority;
import com.example.demo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUserIdAndStatusAndEndTimeAfter(Long userId, Status status, LocalDateTime after);

    List<Task> findByPriority(Priority priority);

    List<Task> findAllByOrderByPriorityAsc();

    List<Task> findByUserId(Long userId);





}
