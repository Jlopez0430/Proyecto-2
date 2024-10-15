package com.javeriana.proyect2.model;

import java.util.ArrayList;
import java.util.List;

public class UserListManager {

    private List<User> userList;

    public UserListManager() {
        this.userList = new ArrayList<>();
    }

    // Método para agregar un usuario si no está duplicado
    public boolean addUser(User user) {
        if (getUserByName(user.getName()) != null || getUserByEmail(user.getEmail()) != null) {
            // El usuario ya existe, no agregar
            return false;
        }
        userList.add(user);
        return true;
    }

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        return userList;
    }

    // Método para buscar usuario por nombre
    public User getUserByName(String name) {
        for (User user : userList) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    // Método para buscar usuario por email
    public User getUserByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}

