package com.task_manager.app.service;

import com.task_manager.app.exception.UserNotFoundException;
import com.task_manager.app.model.User;
import com.task_manager.app.model.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
@Transactional
public class AuthService {

    private final Map<String, String> resetCodes = new HashMap<>();

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String email, String password) throws UserNotFoundException {
        User user = userService.findByEmail(email);
        //User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Incorrect password");
        }
        return user;
    }

    public User register(String email, String username, String password) throws UserNotFoundException {
        User user = new User();

        user.setEmail(email);
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        user.setCreatedAt(Date.from(Instant.now()));

        return userService.addUser(user);
        //return userRepository.save(user);
    }

    public String generateResetCode(String email) {
        User user = userService.findByEmail(email);
        //    User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        resetCodes.put(email, code);
    }

    public void resetPassword(String email, String code, String newPassword, String confirmPassword) throws UserNotFoundException {
        User user = userService.findByEmail(email);
        //User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        String storedCode = resetCodes.get(email);
        if (storedCode == null || !storedCode.equals(code)) {
            throw new RuntimeException("Incorrect code");
        }
        if (newPassword.equals(confirmPassword)) {
            throw new RuntimeException("Passwords do not match");
        }

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        resetCodes.remove(email);

    }
}
