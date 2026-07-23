package com.task_manager.app.exception;

public class EmailNotFound extends RuntimeException {
    public EmailNotFound(String email) {
        super("Email Not Found: " + email);
    }
}
