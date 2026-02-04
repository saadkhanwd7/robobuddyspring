package com.example.robobuddyspring.repository;


import com.example.robobuddyspring.model.Task;

import java.util.*;

public class InMemoryTaskRepository {

    // Fake DB: userId -> list of tasks
    private final Map<String, List<Task>> tasksByUserId = new HashMap<>();

    // Save a task for a user
    public void save(String userId, Task task) {
        tasksByUserId
                .computeIfAbsent(userId, k -> new ArrayList<>())
                .add(task);
    }

    // Get all tasks for a user
    public List<Task> findByUserId(String userId) {
        return tasksByUserId.getOrDefault(userId, new ArrayList<>());
    }

    // Find a specific task by ID
    public Optional<Task> findByTaskId(String userId, int taskId) {
        return findByUserId(userId).stream()
                .filter(task -> task.getTaskId() == taskId)
                .findFirst();
    }

    // Update task (in-memory, so nothing fancy)
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
    public void delete(String userId, int taskId) {
        List<Task> tasks = findByUserId(userId);
        tasks.removeIf(task -> task.getTaskId() == taskId);
    }
}
