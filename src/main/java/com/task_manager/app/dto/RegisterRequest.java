package com.task_manager.app.dto;

public record RegisterRequest(
        long id,
        String username,
        String passwordHash,
        String email
) {

}
