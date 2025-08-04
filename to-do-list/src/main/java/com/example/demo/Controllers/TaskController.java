package com.example.demo.Controllers;

import com.example.demo.Model.Task;
import com.example.demo.Service.TaskService;
import com.example.demo.enums.Priority;
import com.example.demo.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService service;

    @GetMapping
    public List<Task> getTasks() {
        return service.getTasks();
    }

    @GetMapping("/{task_id}")
    public Task getTaskById(@PathVariable long task_id) {
        return service.getTaskById(task_id);
    }

    @PostMapping
    public void addTask(@RequestBody Task task) {
        service.addTask(task);
    }

    @PostMapping("/{taskId}/user/{userId}")
    public ResponseEntity<Task> assignTaskToUser(
            @PathVariable("taskId") Long taskId,
            @PathVariable("userId") Long userId) {

        Task updatedTask = service.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok(updatedTask);
    }


    @PostMapping("/{taskId}/status/{status}")
    public ResponseEntity<String> updateTaskStatus(
            @PathVariable("taskId") Long taskId,
            @PathVariable("status") Status status) {

        service.updateTaskStatus(taskId, status);
        return ResponseEntity.ok("Task status updated successfully.");
    }

    @GetMapping("/priority/{priority}")
    public List<Task> getTasksByPriority(@PathVariable("priority") Priority priority) {
        return service.getTasksByPriority(priority);
    }

    @GetMapping("/sorted/priority")
    public List<Task> getTasksSortedByPriority() {
        return service.getAllTasksSortedByPriority();
    }

    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUserId(@PathVariable("userId") Long userId) {
        return service.getTasksByUserId(userId);
    }


}

