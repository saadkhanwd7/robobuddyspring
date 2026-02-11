package com.example.robobuddyspring.controller;

import com.example.robobuddyspring.model.Robot;
import com.example.robobuddyspring.model.Task;
import com.example.robobuddyspring.model.User;
import com.example.robobuddyspring.model.UserDto;
import com.example.robobuddyspring.repository.UserRepository;
import com.example.robobuddyspring.service.RobotService;
import com.example.robobuddyspring.service.TaskService;
import com.example.robobuddyspring.service.UserService;
import com.example.robobuddyspring.repository.RobotRepository;
import com.example.robobuddyspring.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;
    private final TaskRepository taskRepo;
    private final UserRepository userRepository;
    private final RobotRepository robotRepo;
    private final RobotService robotService;

    public UserController(
            UserService userService,
            TaskService taskService,
            TaskRepository taskRepo,
            UserRepository userRepository,
            RobotRepository robotRepo, RobotService robotService
    ) {
        this.userService = userService;
        this.taskService = taskService;
        this.taskRepo = taskRepo;
        this.userRepository = userRepository;
        this.robotRepo = robotRepo;
        this.robotService = robotService;
    }

    // Create user
    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {
       return userService.createUser(userDto);
    }

    // Get user
    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return userRepository.findById(userId);
    }

    // Get user's tasks
    @GetMapping("/{userId}/tasks")
    public List<Task> getUserTasks(@PathVariable String userId) {
        return taskRepo.findByUserId(userId);
    }

    // Get user's robot
    @GetMapping("/{userId}/robot")
    public Robot getUserRobot(@PathVariable String userId) {
        return robotRepo.findByUserId(userId);
    }

    // 2️⃣ Trigger end-of-day bonfire logic
    @PostMapping("/{userId}/bonfire")
    public Robot applyBonfire(@PathVariable String userId) {
        Robot robot = robotRepo.findByUserId(userId);
        robotService.getBonfireWarmth(userId,robot);
        return robot;
    }
}
