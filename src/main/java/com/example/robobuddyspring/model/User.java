package com.example.robobuddyspring.model;

import java.time.LocalDateTime;
import java.util.List;import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User {

    private String userid;
    private String name;
    private Robot robot;
    private List<Task> dailyTasks;
    private String username;
    private String passwordHash;
    private LocalDateTime createdAt;

    public User(String userid, String username, String passwordHash) {
        this.userid = userid;
        this.username = username;
        this.passwordHash = passwordHash;
        this.createdAt = LocalDateTime.now();
    }

    public User(String userid) {
        this.userid = userid;
    }







}
