package com.javeriana.proyect2.services;

import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.model.UserListManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserListManager userListManager;

    public UserService() {
        this.userListManager = new UserListManager(); // Inicializamos el gestor de usuarios
    }

    public User createUser(User user) {
        boolean userAdded = userListManager.addUser(user);
        if (!userAdded) {
            throw new IllegalArgumentException("El usuario ya está creado con ese nombre o email.");
        }
        return user;
    }

    public List<User> getAllUser() {
        return userListManager.getAllUsers();
    }

    public User getUserById(Long id) {
        return userListManager.getAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
        }
        return existingUser;
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user != null) {
            userListManager.getAllUsers().remove(user);
        }
    }
}

