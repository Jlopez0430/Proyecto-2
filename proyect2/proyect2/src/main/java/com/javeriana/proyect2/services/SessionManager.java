package com.javeriana.proyect2.services;

import com.javeriana.proyect2.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionManager {
    private User loggedInUser;

    public Optional<User> getLoggedInUser() {
        return Optional.ofNullable(loggedInUser);
    }

    public void login(User user) {
        this.loggedInUser = user;
    }

    public void logout() {
        this.loggedInUser = null;
    }

    public boolean isLoggedIn() {
        return this.loggedInUser != null;
    }
}
