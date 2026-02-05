package com.example.robobuddyspring.repository;

import com.example.robobuddyspring.model.Robot;

public interface RobotRepository {
    Robot findByUserId(String userId);
    void save(String userId, Robot robot);
    void update(String userId, Robot robot);
    void delete(String userId);
}
