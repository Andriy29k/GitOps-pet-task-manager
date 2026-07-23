package com.task_manager.app.dto;

public record ResetPasswordRequest(String email, String password, String newPassword, String confirmPassword) {
}
