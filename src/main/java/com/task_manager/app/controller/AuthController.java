package com.task_manager.app.controller;

import com.task_manager.app.dto.LoginRequest;
import com.task_manager.app.dto.RegisterRequest;
import com.task_manager.app.dto.ResetPasswordRequest;
import com.task_manager.app.model.User;
import com.task_manager.app.service.AuthService;
import com.task_manager.app.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;


    public AuthController(UserService userService, AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest registerRequest) {
        return authService.register();
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest) {
        return authService.login();
    }

    @PostMapping("/reset-code")
    public String generateResetCode(@RequestParam String email) {
        return authService.generateResetCode(email);
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        authService.resetPassword();
    }

}
