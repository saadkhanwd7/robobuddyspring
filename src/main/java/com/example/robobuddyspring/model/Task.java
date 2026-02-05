package com.example.robobuddyspring.model;
import lombok.Getter;
import lombok.Setter;
import com.example.robobuddyspring.model.RobotAction;

import java.time.LocalTime;
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





}