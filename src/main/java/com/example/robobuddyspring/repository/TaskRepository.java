package com.example.robobuddyspring.repository;

import com.example.robobuddyspring.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    void save(String userId, Task task);

    List<Task> findByUserId(String userId);

    Optional<Task> findByTaskId(String userId, int taskId);

    void update(String userId, Task updatedTask);

    void delete(String userId, int taskId);
}
