package com.example.robobuddyspring.controller;

import com.example.robobuddyspring.model.Robot;
import com.example.robobuddyspring.model.Task;
import com.example.robobuddyspring.service.RobotService;
import com.example.robobuddyspring.service.TaskService;
import com.example.robobuddyspring.repository.RobotRepository;
import com.example.robobuddyspring.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;
    private final RobotService robotService;
    private final TaskRepository taskRepo;
    private final RobotRepository robotRepo;

    public TaskController(
            TaskService taskService,
            RobotService robotService,
            TaskRepository taskRepo,
            RobotRepository robotRepo
    ) {
        this.taskService = taskService;
        this.robotService = robotService;
        this.taskRepo = taskRepo;
        this.robotRepo = robotRepo;
    }


    //create task
    @PostMapping("/tasks/{userId}/create")
    public Task createTask(@PathVariable String userId,@RequestBody Task task){

        return taskService.createTask(userId,task);

    }




    //start task
    @PutMapping("/tasks/start")
    public Task startTask(@RequestBody Task task){

        return taskService.startTask(task);

    }




    //  Get all tasks for a user
    @GetMapping("/tasks/{userId}")
    public List<Task> getTasks(@PathVariable String userId) {
        return taskRepo.findByUserId(userId);
    }

    // Complete task
    @PostMapping("/tasks/{userId}/complete")
    public String completeTask(
            @PathVariable String userId,
            @RequestBody Task task
    ) {
        Robot robot = robotRepo.findByUserId(userId);
        taskService.completeTask(userId, task, robot);
        return "Task completed";
    }
    // skip task
    @PostMapping("/tasks/{userId}/skip")
    public String skipTask(
            @PathVariable String userId,
            @RequestBody Task task
    ) {
        Robot robot = robotRepo.findByUserId(userId);
        taskService.skipTask(userId, task, robot);
        return "Task skipped";
    }

    //  Get robot state
    @GetMapping("/robot/{userId}")
    public Robot getRobot(@PathVariable String userId) {
        return robotRepo.findByUserId(userId);
    }


}
