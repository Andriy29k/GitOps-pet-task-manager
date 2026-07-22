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
    long id;
    String username;
    String password_hash;
    String email;
    Role role;
    Date created_at;
}
