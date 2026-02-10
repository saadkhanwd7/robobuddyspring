package com.example.robobuddyspring.model;

import java.time.LocalDateTime;
import java.util.List;import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDto {

    private String username;
    private String password;


    public UserDto(String username, String password) {

        this.username = username;
        this.password = password;
//        this.createdAt = LocalDateTime.now();
    }




}