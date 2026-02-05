package com.example.robobuddyspring.service;

import com.example.robobuddyspring.model.Robot;
import com.example.robobuddyspring.model.RobotAction;
import com.example.robobuddyspring.model.Task;
import com.example.robobuddyspring.model.TaskStatus;
import com.example.robobuddyspring.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepo;
    private final RobotService robotService;

    public TaskService(TaskRepository taskRepo, RobotService robotService) {
        this.taskRepo = taskRepo;
        this.robotService = robotService;
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

        // Mark task as completed
        task.setStatus(TaskStatus.COMPLETED);

        // Update robot daily stats
        robot.setTasksCompletedToday(robot.getTasksCompletedToday() + 1);

        // Reward robot
        robot.addXp(10);
        // robot.addRoboCredits(5); // optional
        robot.setEnergy(Math.min(robot.getEnergy() + 10, 100));

        // Feeling can optionally be updated again after completion
        if (task.getRobotAction() == RobotAction.WORKOUT) {
            robot.setFeeling("strong");
        }

        // Update robot current task and action
        robot.setCurrentTask(task);
        robot.setCurrentRobotAction(task.getRobotAction());

        // Persist task
        taskRepo.update(userId, task);
    }

    public List<Task> getTasksForUser(String userId) {
        return taskRepo.findByUserId(userId);
    }
}
