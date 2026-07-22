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
    long id;
    String title;
    String description;
    Status status;
    Priority priority;
    Date dueDate;
    User user_id;
    Category category_id;
    Date created_at;
}
