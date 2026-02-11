package com.example.robobuddyspring.controller;

import com.example.robobuddyspring.model.Robot;
import com.example.robobuddyspring.service.RobotService;
import com.example.robobuddyspring.repository.RobotRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/robot")
public class RobotController {

    private final RobotRepository robotRepo;
    private final RobotService robotService;

    public RobotController(RobotRepository robotRepo, RobotService robotService) {
        this.robotRepo = robotRepo;
        this.robotService = robotService;
    }

    // 1️⃣ Get robot state
    @GetMapping("/{userId}")
    public Robot getRobot(@PathVariable String userId) {
        return robotRepo.findByUserId(userId);
    }


}
