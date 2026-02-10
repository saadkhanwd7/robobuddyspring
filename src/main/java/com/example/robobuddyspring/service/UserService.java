package com.example.robobuddyspring.service;


import com.example.robobuddyspring.model.Recurrence;
import com.example.robobuddyspring.model.Task;
import com.example.robobuddyspring.model.User;
import com.example.robobuddyspring.model.UserDto;
import com.example.robobuddyspring.repository.TaskRepository;
import com.example.robobuddyspring.repository.UserRepository;
import com.example.robobuddyspring.util.IdUtil;
import com.example.robobuddyspring.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;

    }

    public User createUser(UserDto userdto) {


        String username = userdto.getUsername();
        String rawPassword = userdto.getPassword();


        String hashedPassword = PasswordUtil.hash(rawPassword);

        User user = new User(
                IdUtil.uuid(),
                username,
                hashedPassword
        );
        return userRepository.save(user);
    }

    public User login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);
        if (user == null) return null;

        if (!PasswordUtil.matches(rawPassword, user.getPasswordHash())) {
            return null;
        }
        return user;
    }



}
