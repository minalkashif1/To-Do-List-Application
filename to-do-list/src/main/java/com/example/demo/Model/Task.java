package com.example.demo.Model;

import com.example.demo.enums.Priority;
import com.example.demo.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "taskId")
    private Long id;

    private String description;
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private Status status= Status.PENDING;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime expectedCompletionTime;



    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties("tasks")
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    @PrePersist
    protected void onCreate() {
        this.startTime = LocalDateTime.now();

        if (this.status == null) {
            this.status = Status.PENDING;
        }

        if (this.expectedCompletionTime == null) {
            if (this.priority != null) {
                switch (this.priority) {
                    case HIGH:
                        this.expectedCompletionTime = this.startTime.plusDays(1);
                        break;
                    case MEDIUM:
                        this.expectedCompletionTime = this.startTime.plusDays(3);
                        break;
                    case LOW:
                    default:
                        this.expectedCompletionTime = this.startTime.plusDays(5);
                        break;
                }
            } else {
                this.expectedCompletionTime = this.startTime.plusDays(3);
            }
        }
    }



}
