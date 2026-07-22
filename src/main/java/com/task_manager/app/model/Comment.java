package com.task_manager.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    long id;
    String text;
    Task task_id;
    User user_id;
    Date created_at;
}
