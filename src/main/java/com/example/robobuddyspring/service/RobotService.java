package com.example.robobuddyspring.service;

import com.example.robobuddyspring.model.Robot;
import com.example.robobuddyspring.model.RobotAction;
import com.example.robobuddyspring.model.Task;
import org.springframework.stereotype.Service;

@Service
public class RobotService {

    /**
     * Sets the robot's current task and updates energy/feeling immediately.
     */
    public void startTask(Task task, Robot robot) {
        if (task == null || robot == null) return;

        // Link task to robot
        robot.setCurrentTask(task);
        robot.setCurrentRobotAction(task.getRobotAction());

        // Update energy based on task
        switch (task.getRobotAction()) {
            case EAT -> robot.setEnergy(Math.min(robot.getEnergy() + 10, 100));
            case SLEEP -> robot.setEnergy(Math.min(robot.getEnergy() + 20, 100));
            case WORKOUT -> robot.setEnergy(Math.max(robot.getEnergy() - 15, 0));
            case MEDITATE -> robot.setEnergy(Math.min(robot.getEnergy() + 5, 100));
            case SHOWER -> robot.setEnergy(Math.min(robot.getEnergy() + 5, 100));
            case PLAY -> robot.setEnergy(Math.max(robot.getEnergy() - 5, 0));
            case READ -> robot.setEnergy(Math.max(robot.getEnergy() - 2, 0));
            case RANDOM -> robot.setEnergy(Math.max(robot.getEnergy() - 3, 0));
        }

        // Update feeling immediately
        switch (task.getRobotAction()) {
            case EAT -> robot.setFeeling("energized");
            case SLEEP -> robot.setFeeling("rested");
            case WORKOUT -> robot.setFeeling("strong");
            case MEDITATE -> robot.setFeeling("calm");
            case SHOWER -> robot.setFeeling("fresh");
            case PLAY -> robot.setFeeling("happy");
            case READ -> robot.setFeeling("focused");
            case RANDOM -> robot.setFeeling("curious");
        }

        // Optional: console feedback
        System.out.println(robot.getName() + " started: " + robot.getCurrentRobotAction());
        System.out.println("Energy: " + robot.getEnergy() + ", Feeling: " + robot.getFeeling());
    }

    // Simple helper for completing tasks
    public void addEnergy(Robot robot, int delta) {
        robot.setEnergy(Math.max(Math.min(robot.getEnergy() + delta, 100), 0));
    }

    public void setFeeling(Robot robot, String feeling) {
        robot.setFeeling(feeling);
    }
}
