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
            Optional<User> existingUser = Optional.ofNullable(userRepository.findByusername(user.getusername()));
            if (existingUser.isPresent()) {
                throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
            }
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new Exception("Error en el registro del usuario: " + e.getMessage());
        }
    }

    public List<Calendario> getCalendariosByUserId(Long id) {
        // Verificar si el usuario está logueado
        User user = userRepository.findByid(id);
        return user.getCalendarios();
    }

    // Retorna el usuario autenticado en lugar de un booleano
    public Optional<User> login(String userName, String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByusername(userName));
        if (user.isPresent() && user.get().getpassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User getUserId(Long userId){
        return userRepository.findByid(userId);
    }

}