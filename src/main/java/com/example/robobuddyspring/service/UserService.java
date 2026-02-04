package com.example.robobuddyspring.service;


import com.example.robobuddyspring.model.User;
import com.example.robobuddyspring.repository.UserRepository;
import com.example.robobuddyspring.util.IdUtil;
import com.example.robobuddyspring.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String rawPassword) {
        String hash = PasswordUtil.hash(rawPassword);
        User user = new User(
                IdUtil.uuid(),
                username,
                hash
        );
        userRepository.save(user);
        return user;
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
