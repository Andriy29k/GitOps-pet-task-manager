package com.task_manager.app.model;

import com.task_manager.app.model.enums.Priority;
import com.task_manager.app.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Date dueDate;
    private User user;
    private Category category;
    private Date createdAt;
}
