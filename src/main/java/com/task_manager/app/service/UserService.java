package com.task_manager.app.service;

import com.task_manager.app.exception.DuplicateEmailException;
import com.task_manager.app.exception.EmailNotFound;
import com.task_manager.app.exception.UserNotFoundException;
import com.task_manager.app.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<User> getUsers() {
        return List.copyOf(users.values());
    }

    public User addUser(User user) throws DuplicateEmailException {
        emailCheck(user.getEmail());
        user.setId(idGenerator.getAndIncrement());
        users.put(user.getId(), user);
        return user;
    }

    public User updateUser(long id, User changes) throws UserNotFoundException {
        User existing = findById(id);
        existing.setUsername(changes.getUsername());
        existing.setEmail(changes.getEmail());
        existing.setRole(changes.getRole());
        return existing;
    }

    public void removeUser(long id) throws UserNotFoundException {
        if (users.remove(id) == null) {
            throw new UserNotFoundException(id);
        }
    }

    public User findById(long id) throws UserNotFoundException {
        User user = users.get(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    private void emailCheck(String email) throws DuplicateEmailException {
        boolean exists = users.values().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
        if (exists) {
            throw new DuplicateEmailException(email);
        }
    }

    public User findByEmail(String email) {
        User exist = users.containsValue(email) ? users.get(email) : null;
        if (exist == null) {
            throw new EmailNotFound(email);
        }
        return exist;
    }
}
