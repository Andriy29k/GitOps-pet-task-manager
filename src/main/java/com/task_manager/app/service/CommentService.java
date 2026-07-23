package com.task_manager.app.service;

import com.task_manager.app.exception.CommentNotFoundException;
import com.task_manager.app.model.Comment;
import com.task_manager.app.model.Task;
import com.task_manager.app.model.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
public class CommentService {

    private final Map<Long, Comment> comments = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private final TaskService taskService;
    private final UserService userService;

    public CommentService(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    public List<Comment> getComments() {
        return List.copyOf(comments.values());
    }

    public List<Comment> getCommentsByTaskId(long taskId) {
        taskService.findById(taskId);
        return comments.values().stream()
                .filter(c -> c.getTask().getId() == taskId)
                .collect(Collectors.toList());
    }

    public Comment addComment(long taskId, long userId, String text) {
        Task task = taskService.findById(taskId);
        User user = userService.findById(userId);

        Comment comment = new Comment();
        comment.setId(idGenerator.getAndIncrement());
        comment.setText(text);
        comment.setTask(task);
        comment.setUser(user);
        comment.setCreatedAt(Instant.now());

        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment findById(long id) throws CommentNotFoundException {
        Comment comment = comments.get(id);
        if (comment == null) {
            throw new CommentNotFoundException(id);
        }
        return comment;
    }

    public Comment updateComment(long id, String newText) {
        Comment comment = findById(id);
        comment.setText(newText);
        return comment;
    }

    public void deleteComment(long id) throws CommentNotFoundException {
        if (comments.remove(id) == null) {
            throw new CommentNotFoundException(id);
        }
    }
}
