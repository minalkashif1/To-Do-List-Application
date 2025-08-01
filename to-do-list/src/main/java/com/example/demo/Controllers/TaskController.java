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
    public List<Task> getTasks()
    {
        return service.getTasks();
    }

    @GetMapping("/{task_id}")
    public Task getTaskById(@PathVariable long task_id)
    {
        return service.getTaskById(task_id);
    }

    @PostMapping
    public void addTask(@RequestBody Task task)
    {
        service.addTask(task);
    }

    @PostMapping("/{taskId}/user/{userId}")
    public ResponseEntity<String> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        service.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok("Task assigned successfully.");
    }

    @PostMapping("/{taskId}/status/{status}")
    public ResponseEntity<String> updateTaskStatus(
            @PathVariable Long taskId,
            @PathVariable Status status) {

        service.updateTaskStatus(taskId, status);
        return ResponseEntity.ok("Task status updated successfully.");
    }

    @GetMapping("/priority/{priority}")
    public List<Task> getTasksByPriority(@PathVariable Priority priority) {
        return service.getTasksByPriority(priority);
    }

    @GetMapping("/sorted/priority")
    public List<Task> getTasksSortedByPriority() {
        return service.getAllTasksSortedByPriority();
    }

    @GetMapping("/USER/{userId}")
    public List<Task> getTasksByUserId(@PathVariable Long userId) {
        return service.getTasksByUserId(userId);
    }




/*
    @RequestMapping("/task/update")
    public void updateTask(Task task)
    {
        service.updateTask(task);
    }

    @RequestMapping("/task/{id}")
    public void deleteTaskById(Long t_id)
    {
        service.deleteTaskById(t_id);
    }
*/
}
