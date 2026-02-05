package com.example.robobuddyspring.repository;

import com.example.robobuddyspring.model.Robot;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository

public class InMemoryRobotRepository implements RobotRepository {
    private final Map<String, Robot> db = new HashMap<>();

    @Override
    public Robot findByUserId(String userId) {
        return db.get(userId);
    }

    @Override
    public void save(String userId, Robot robot) {
        db.put(userId, robot);
    }

    @Override
    public void update(String userId, Robot robot) {
        db.put(userId, robot);
    }

    @Override
    public void delete(String userId) {
        db.remove(userId);
    }
}
