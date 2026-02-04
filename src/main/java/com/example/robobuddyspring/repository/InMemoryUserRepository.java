package com.example.robobuddyspring.repository;

import com.example.robobuddyspring.model.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> usersByUsername = new HashMap<>();

    @Override
    public void save(User user) {
        usersByUsername.put(user.getUsername(), user);
    }

    @Override
    public User findByUsername(String username) {
        return usersByUsername.get(username);
    }

    @Override
    public User findById(String id) {
        return usersByUsername.values()
                .stream()
                .filter(u -> u.getUserid().equals(id))
                .findFirst()
                .orElse(null);
    }
}
