package com.task_manager.app.model;

import com.task_manager.app.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;
    private String username;
    private String passwordHash;
    private String email;
    private Role role;
    private Date createdAt;
}
