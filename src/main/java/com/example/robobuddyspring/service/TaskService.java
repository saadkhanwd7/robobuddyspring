package com.example.robobuddyspring.service;


import com.example.robobuddyspring.model.Robot;
import com.example.robobuddyspring.model.Task;
import com.example.robobuddyspring.model.TaskStatus;
import com.example.robobuddyspring.repository.InMemoryTaskRepository;

import java.util.List;


public class TaskService {


    private final InMemoryTaskRepository taskRepo;

    public TaskService(InMemoryTaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }
    public void completeTask(String userId, Task task, Robot robot) {
        if (task == null || robot == null ||userId == null)
            return;

        // 1️⃣ Mark task as completed
        task.setStatus(TaskStatus.COMPLETED);

        // 2️⃣ Update robot daily stats
        robot.setTasksCompletedToday(robot.getTasksCompletedToday() + 1);

        // 3️⃣ Reward robot
        robot.addXp(10);    
        robot.addRoboCredits(5);
        robot.setEnergy(Math.min(robot.getEnergy() + 10, 100));
        robot.setFeeling("happy");

        // 4️⃣ Update robot's current action
        robot.setCurrentTask(task);       // assign task to robot
        robot.setCurrentRobotAction();    // update action based on task

        // 5️⃣ Persist task in the repository
        taskRepo.update(userId, task);
    }
    public List<Task> getTasksForUser(String userId) {
        return taskRepo.findByUserId(userId);
    }

}
