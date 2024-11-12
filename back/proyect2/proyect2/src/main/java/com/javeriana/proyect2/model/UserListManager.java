package com.javeriana.proyect2.model;

import java.util.ArrayList;
import java.util.List;

public class UserListManager {

    private List<User> userList;

    public UserListManager() {
        this.userList = new ArrayList<>();
    }

    public boolean addUser(User user) {
        if (getUserByName(user.getusername()) != null) {
            return false;
        }
        userList.add(user);
        return true;
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public User getUserByName(String name) {
        for (User user : userList) {
            if (user.getusername().equals(name)) {
                return user;
            }
        }
        return null;
    }
}