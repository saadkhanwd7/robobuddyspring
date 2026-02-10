package com.example.robobuddyspring.repository;


import com.example.robobuddyspring.model.User;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);

    User findById(String id);
}
