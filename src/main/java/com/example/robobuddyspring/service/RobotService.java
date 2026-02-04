package com.example.robobuddyspring.service;


import com.example.robobuddyspring.model.Robot;
import com.example.robobuddyspring.model.Task;
import com.example.robobuddyspring.model.TaskStatus;

public class RobotService {

    /**
     * Starts a task for a robot.
     * - Sets the robot's current task and action.
     * - Updates task status to IN_PROGRESS.
     * - Can modify robot stats (energy, feeling, etc.) later.
     */
    public void startTask(Task task, Robot robot) {
        if (task == null || robot == null) {
            System.out.println("Task or Robot is null. Cannot start.");
            return;
        }

        // 1️⃣ Link task to robot
        robot.setCurrentRobotAction(task.getRobotAction()); // sets currentTask and currentRobotAction

        // 2️⃣ Update task status
        task.setStatus(TaskStatus.IN_PROGRESS);

        // 3️⃣ Optional: modify robot stats for starting a task
        // Example: reduce energy slightly
        robot.setEnergy(Math.max(robot.getEnergy() - 5, 0));

        // 4️⃣ Optional: set feeling based on energy
        if (robot.getEnergy() < 20) {
            robot.setFeeling("tired");
        } else {
            robot.setFeeling("active");
        }

        // 5️⃣ Feedback (for console/testing)

        System.out.println(robot.getName() + " is now performing: " + robot.getCurrentRobotAction());
        System.out.println("Task \"" + task.getTaskName() + "\" status: " + task.getStatus());
        System.out.println("Robot energy: " + robot.getEnergy() + ", feeling: " + robot.getFeeling());
    }

}
