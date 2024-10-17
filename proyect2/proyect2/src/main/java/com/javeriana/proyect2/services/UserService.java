package com.javeriana.proyect2.services;

import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.model.UserListManager;
import com.javeriana.proyect2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserListManager userListManager;
    private final SessionManager sessionManager;
    private final UserRepository userRepository;

    @Autowired
    public UserService(SessionManager sessionManager, UserRepository userRepository) {
        this.userListManager = new UserListManager();
        this.sessionManager = sessionManager;
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        boolean userAdded = userListManager.addUser(user);
        if (!userAdded) {
            throw new IllegalArgumentException("El usuario ya está creado con ese nombre o email.");
        }
        return userRepository.save(user);
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

    // Método para iniciar sesión
    public boolean login(String name, String password) {
        User user = userListManager.getUserByName(name);
        if (user != null && user.getPassword().equals(password)) {
            sessionManager.login(user);  // Guardamos el usuario en sesión
            return true;
        }
        return false;
    }

    public void logout() {
        sessionManager.logout();
    }

    public List<Calendario> getCalendariosByUserId(Long userId) {
        User user = getUserById(userId);
        if (user != null) {
            return user.getCalendarios();
        } else {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
    }



}
