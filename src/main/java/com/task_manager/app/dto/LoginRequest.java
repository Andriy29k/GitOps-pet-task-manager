package com.task_manager.app.dto;

public record LoginRequest(
        String email,
        String passwordHash
) {
}
