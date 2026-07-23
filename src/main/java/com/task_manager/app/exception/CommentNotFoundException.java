package com.task_manager.app.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(long id) {
        super("Comment with id " + id + " not found");
    }
}
