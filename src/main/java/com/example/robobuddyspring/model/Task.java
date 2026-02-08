package com.example.robobuddyspring.model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter


public class Task{
    private int taskId;
    private String taskName;
    private String description;
    private String userId;
    private TaskStatus status;
    private RobotAction robotAction;
    private LocalTime scheduledTime; // when the task should happen
    private Recurrence recurrence;


    public Task(String userId, String taskName, String description, LocalTime scheduledTime, Recurrence recurrence) {
        this.userId = userId;
        this.taskName = taskName;
        this.description = description;
        this.scheduledTime = scheduledTime;
        this.recurrence = recurrence;
    }


}