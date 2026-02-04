package com.example.robobuddyspring.repository;


import com.example.robobuddyspring.model.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

    // Fake DB: userId -> list of tasks
    private final Map<String, List<Task>> tasksByUserId = new HashMap<>();

    // Save a task for a user
    @Override
    public void save(String userId, Task task) {
        tasksByUserId
                .computeIfAbsent(userId, k -> new ArrayList<>())
                .add(task);
    }

    // Get all tasks for a user
    @Override
    public List<Task> findByUserId(String userId) {
        return tasksByUserId.getOrDefault(userId, new ArrayList<>());
    }

    // Find a specific task by ID
    @Override
    public Optional<Task> findByTaskId(String userId, int taskId) {
        return findByUserId(userId).stream()
                .filter(task -> task.getTaskId() == taskId)
                .findFirst();
    }

    // Update task (in-memory, so nothing fancy)
    @Override
    public void update(String userId, Task updatedTask) {
        List<Task> tasks = findByUserId(userId);

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId() == updatedTask.getTaskId()) {
                tasks.set(i, updatedTask);
                return;
            }
        }
    }

    // Delete a task
    @Override
    public void delete(String userId, int taskId) {
        List<Task> tasks = findByUserId(userId);
        tasks.removeIf(task -> task.getTaskId() == taskId);
    }
}
