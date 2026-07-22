package com.task_manager.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    private Long id;
    private String text;
    private Task task;
    private User user;
    private Date createdAt;
}
