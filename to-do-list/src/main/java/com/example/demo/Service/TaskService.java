package com.example.demo.Service;

import com.example.demo.Model.Task;
import com.example.demo.Model.User;
import com.example.demo.Repository.TaskRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.enums.Priority;
import com.example.demo.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> getTasks()
    {
        return taskRepository.findAll();
    }

    public Task getTaskById(long t_id)
    {
        return taskRepository.findById(t_id).orElse(new Task());
    }

    public void addTask(Task task)
    {
        taskRepository.save(task);
    }

    public void updateTaskStatus(Long taskId, Status status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(status);

        if (status == Status.COMPLETED) {
            task.setEndTime(LocalDateTime.now());
        }

        taskRepository.save(task);
    }


    public void assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        task.setUser(user);
        taskRepository.save(task);
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    public List<Task> getAllTasksSortedByPriority() {
        return taskRepository.findAllByOrderByPriorityAsc();
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }


/*
    public void updateTask(Task task)
    {
        taskRepository.save(task);
    }

    public void deleteTaskById(Long t_id)
    {
        taskRepository.deleteById(t_id);
    }
*/
}
