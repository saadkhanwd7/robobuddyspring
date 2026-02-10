package com.example.robobuddyspring.service;

import com.example.robobuddyspring.model.Robot;
import com.example.robobuddyspring.model.RobotAction;
import com.example.robobuddyspring.model.Task;
import com.example.robobuddyspring.repository.RobotRepository;
import org.springframework.stereotype.Service;

@Service
public class RobotService {


    private final RobotRepository robotRepo;

    public RobotService(RobotRepository robotRepo) {
        this.robotRepo = robotRepo;
    }


    /**
     *
     * Sets the robot's current task and updates energy/feeling immediately.
     */
    public void startTask(Task task, Robot robot) {
        if (task == null || robot == null) return;

        // Link task to robot
        if (robot.getCurrentTask() != null) {
            throw new IllegalStateException("Robot is already busy");
        }

        robot.setCurrentTask(task);
        robot.setCurrentRobotAction(task.getRobotAction());

        // Update energy based on task
        switch (task.getRobotAction()) {
            case EAT -> robot.setEnergy(Math.min(robot.getEnergy() + 10, 100));
            case SLEEP -> robot.setEnergy(Math.min(robot.getEnergy() + 20, 100));
            case WORKOUT -> robot.setEnergy(Math.max(robot.getEnergy() - 15, 0));
            case MEDITATE -> robot.setEnergy(Math.min(robot.getEnergy() + 6, 100));
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
            case RANDOM -> robot.setFeeling("neutral");
        }


        // Optional: console feedback
        System.out.println(robot.getName() + " started: " + robot.getCurrentRobotAction());
        System.out.println("Energy: " + robot.getEnergy() + ", Feeling: " + robot.getFeeling());
        robotRepo.save(task.getUserId(), robot);
    }

    public void skipTask( Task task , Robot robot){

        robot.setFeeling("bored");
        robot.setEnergy(robot.getEnergy() - 3);
        robot.setWarmth(Math.max(robot.getWarmth() - 0.5  , 1  ));
        robot.setTasksSkippedToday(robot.getTasksSkippedToday() + 1);
        robot.setCurrentRobotAction(RobotAction.BONFIRE);

        robotRepo.update(task.getUserId(), robot);
    }


    public void completeTask(String userId, Task task , Robot robot){

        if (!userId.equals(task.getUserId())) return;

        robot.setTasksCompletedToday(robot.getTasksCompletedToday() + 1);
        robot.setWarmth(Math.min(robot.getWarmth() + 1, 100));
        robot.setCurrentTask(null);
        robot.setCurrentRobotAction(RobotAction.BONFIRE);
        robot.setFeeling("warm");

        robotRepo.update(task.getUserId(), robot);
    }

    // Simple helper for completing tasks
    public void addEnergy(Robot robot, int delta) {
        robot.setEnergy(Math.max(Math.min(robot.getEnergy() + delta, 100), 0));
    }

    public void setFeeling(Robot robot, String feeling) {
        robot.setFeeling(feeling);
    }
}
