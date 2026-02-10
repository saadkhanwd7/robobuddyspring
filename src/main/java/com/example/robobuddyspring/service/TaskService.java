package com.example.robobuddyspring.service;

import com.example.robobuddyspring.model.*;
import com.example.robobuddyspring.repository.TaskRepository;
import com.example.robobuddyspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class TaskService {


    private final TaskRepository taskRepo;
    private final RobotService robotService;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepo, RobotService robotService, UserRepository userRepository) {
        this.taskRepo = taskRepo;
        this.robotService = robotService;
        this.userRepository = userRepository;
    }


    public Task createTask(String userId,Task task) {

        String taskName = task.getTaskName();
        String description = task.getDescription();
        LocalTime scheduledTime = task.getScheduledTime();
        Recurrence recurrence = task.getRecurrence();

        Task task1 = new Task(userId, taskName, description, scheduledTime, recurrence);

        taskRepo.save(userId, task1);
        return task;
    }

    /**
     * Starts a task for the user and updates robot's state immediately.
     *
     * @return
     */
    public Task startTask(Task task) {
        if (task == null) return task;

        String userId  = task.getUserId();
        User user = userRepository.findById(userId);

        Robot robot = user.getRobot();

        // Update robot energy and feeling immediately
        robotService.startTask(task, robot);


        // Update task status
        task.setStatus(TaskStatus.IN_PROGRESS);


        // Persist task if needed
        taskRepo.update(userId, task);
        return task;
    }


    /**
     * Completes a task and rewards the robot.
     */
    public void completeTask(String userId, Task task, Robot robot) {

        if (task == null || robot == null || userId == null) return;


        robotService.completeTask(userId,task,robot);

        if (task.getStatus() != TaskStatus.COMPLETED) {
            // Mark task as completed
            task.setStatus(TaskStatus.COMPLETED);

            taskRepo.update(userId, task);
        }
    }

    public void skipTask(String userId, Task task , Robot robot){

        if (task == null || robot == null || userId == null) return;

        if (task.getStatus() != TaskStatus.SKIPPED)
            task.setStatus(TaskStatus.SKIPPED);


        robotService.skipTask(task, robot);
        taskRepo.update(userId, task);


    }

    public List<Task> getTasksForUser(String userId) {
        return taskRepo.findByUserId(userId);
    }
}
