package com.example.robobuddyspring.model;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class Robot {

    private String id;             // Unique robot ID
    private String name;           // Robot/character name
    private int level;             // Current level
    private int xp;                // Experience points
    private double warmth;            // Health (100 = full, 0 = broken)
    private int energy;
    private String feeling;
    private int embers;
    private int orbs;
    private int daysCompleted;     // Total days with all tasks completed
    private int tasksCompletedToday; // Count of tasks completed today
    private int tasksSkippedToday;   // Count of tasks skipped today
    private RobotAction currentRobotAction;
    private Task currentTask;



    // Constructors
    public Robot() {
        this.level = 1;
        this.xp = 0;
        this.warmth = 100;
        this.daysCompleted = 0;
        this.tasksCompletedToday = 0;
        this.tasksSkippedToday = 0;
        this.energy = 100;      // full energy by default
        this.feeling = "neutral"; // default mood
        this.embers = 0;   // start with nothing
        this.orbs = 0;          // start with nothing
    }


    public Robot(String id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    // Getters & Setters



    public void addEmbers(int embers) {
        this.embers += embers;
    }
    public void subtractEmbers(int embers) {
        this.embers -= embers;
    }



    public void setCurrentRobotAction(RobotAction currentRobotAction) {
        this.currentRobotAction = currentRobotAction;
    }

    // Convenience methods
    public void addXp(int amount) { this.xp += amount; }
    public void addWarmth(float amount) { this.warmth += amount; }
    public void subtractWarmth(float amount) { this.warmth -= amount; }

    public void resetDailyStats() {
        this.tasksCompletedToday = 0;
        this.tasksSkippedToday = 0;
    }


    public void setCurrentRobotAction(){

        if ( currentTask!= null && currentTask.getRobotAction() != null){
            currentRobotAction = currentTask.getRobotAction();
        }

    }
}





