package com.task_manager.app.service;

import com.task_manager.app.model.Comment;
import com.task_manager.app.model.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CommentService {

    private final Map<Long, Comment> comments = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Comment> getComments() {
        return List.copyOf(comments.values());
    }

}
