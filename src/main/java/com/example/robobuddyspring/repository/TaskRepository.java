package com.example.robobuddyspring.repository;

import com.example.robobuddyspring.model.Task;

import java.util.List;

public interface TaskRepository {

    void save(Task task);

    List<Task> findByUserId(String userId);
}
