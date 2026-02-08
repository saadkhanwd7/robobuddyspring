package com.example.robobuddyspring.service;

import com.example.robobuddyspring.model.*;
import com.example.robobuddyspring.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class TaskService {


    private final TaskRepository taskRepo;
    private final RobotService robotService;

    public TaskService(TaskRepository taskRepo, RobotService robotService) {
        this.taskRepo = taskRepo;
        this.robotService = robotService;
    }


    public Task createTask(String userId, String taskName, String description, LocalTime scheduledTime, Recurrence recurrence) {

        Task task = new Task(userId, taskName, description, scheduledTime, recurrence);

        taskRepo.save(userId, task);
        return task;
    }

    /**
     * Starts a task for the user and updates robot's state immediately.
     */
    public void startTask(String userId, Task task, Robot robot) {
        if (task == null || robot == null || userId == null) return;

        // Update task status
        task.setStatus(TaskStatus.IN_PROGRESS);

        // Update robot energy and feeling immediately
        robotService.startTask(task, robot);

        // Persist task if needed
        taskRepo.update(userId, task);
    }


    /**
     * Completes a task and rewards the robot.
     */
    public void completeTask(String userId, Task task, Robot robot) {

        if (task == null || robot == null || userId == null) return;

        if (task.getStatus() != TaskStatus.COMPLETED) {
            // Mark task as completed
            task.setStatus(TaskStatus.COMPLETED);


            robotService.completeTask(userId,task,robot);


            taskRepo.update(userId, task);
        }
    }

    public void skipTask(String userId, Task task , Robot robot){

        if (task == null || robot == null || userId == null) return;

        if (task.getStatus() != TaskStatus.SKIPPED)
            task.setStatus(TaskStatus.SKIPPED);


        taskRepo.update(userId, task);
        robotService.skipTask(task, robot);

    }

    public List<Task> getTasksForUser(String userId) {
        return taskRepo.findByUserId(userId);
    }
}
