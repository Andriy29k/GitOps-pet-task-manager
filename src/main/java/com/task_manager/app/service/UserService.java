package com.task_manager.app.service;

import com.task_manager.app.exception.DuplicateEmailException;
import com.task_manager.app.exception.UserNotFoundException;
import com.task_manager.app.model.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {

    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<User> getUsers() {
        return List.copyOf(users.values());
    }

    public User addUser(User user) {
        emailCheck(user.getEmail());
        user.setId(idGenerator.getAndIncrement());
        users.put(user.getId(), user);
        return user;
    }

    public User updateUser(long id, User changes) {
        User existing = findById(id);
        existing.setUsername(changes.getUsername());
        existing.setEmail(changes.getEmail());
        existing.setRole(changes.getRole());
        return existing;
    }

    public void removeUser(long id) {
        if (users.remove(id) == null) {
            throw new UserNotFoundException(id);
        }
    }

    public User findById(long id) {
        User user = users.get(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    private void emailCheck(String email) {
        boolean exists = users.values().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
        if (exists) {
            throw new DuplicateEmailException(email);
        }
    }
}
