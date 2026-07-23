package com.task_manager.app.service;

import com.task_manager.app.exception.TaskNotFoundException;
import com.task_manager.app.model.Task;
import com.task_manager.app.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Task> getTasks() {
        return List.copyOf(tasks.values());
    }

    public Task updateTask(Task task) throws TaskNotFoundException {
        Task existingTask = findById(task.getId());
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setUser(task.getUser());
        existingTask.setCategory(task.getCategory());
        return existingTask;
    }

    public void assignTask(long taskId, User user) throws TaskNotFoundException {
        Task task = findById(taskId);
        if (task.getUser() == null) {
            task.setUser(user);
        }
    }

    private Task findById(long taskId) throws TaskNotFoundException {
        if(tasks.get(taskId) == null) {
            throw new TaskNotFoundException(taskId);
        } else {
            return tasks.get(taskId);
        }
    }

    public Task addTask(Task task) {
        task.setId(idGenerator.getAndIncrement());
        tasks.put(task.getId(), task);
        return task;
    }

    public void deleteTask(long id) throws TaskNotFoundException {
        if(tasks.remove(id) == null) {
            throw new TaskNotFoundException(id);
        }
    }


}
