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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) throws Exception {
        try {
            // Verificar si el nombre de usuario ya existe
            Optional<User> existingUser = Optional.ofNullable(userRepository.findByUserName(user.getUsername()));
            if (existingUser.isPresent()) {
                throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
            }
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new Exception("Error en el registro del usuario: " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserName(username));
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        try {
            return userRepository.findById(id)
                    .orElseThrow(() -> new Exception("Creature not found"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // Puedes lanzar una excepción personalizada o retornar `null`.
        }
    }

    public void deleteCreature(Long id) {
        User creature = getUserById(id);
        userRepository.delete(creature);
    }
}
