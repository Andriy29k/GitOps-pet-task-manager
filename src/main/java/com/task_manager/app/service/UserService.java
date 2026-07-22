package com.task_manager.app.service;

import com.task_manager.app.model.User;

import java.util.ArrayList;

public class UserService {
    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) throws Exception {
        if(!users.contains(user)) {
            emailCheck(user);
            users.add(user);
        }
    }

    public void updateUser(User user, long user_id) throws Exception {
        User temp = null;
        //if (users.contains(user.getId(user_id))) {
        //    user.getUsername() == users.get(user_id).getUsername() ?  : temp.setUsername(user.getUsername());
        //}
    }

    public void removeUser(User user) throws Exception {
        if(users.contains(user)) {
            users.remove(user);
        }
    }

    public User findById(long id) throws Exception {
        User tempUser = users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
        if(tempUser != null) {
            return tempUser;
        } else  {
            throw new Exception("User with id " + id + " not found");
        }
    }

    public void emailCheck(User user) throws Exception {
        for( int i = 0; i < users.size(); i++ ) {
            if(users.get(i).getEmail().equals(user.getEmail())) {
                throw new Exception("User with that email already created.");
            }
        }
    }
}
