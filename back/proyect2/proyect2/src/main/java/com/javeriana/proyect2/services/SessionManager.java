package com.javeriana.proyect2.services;

import com.javeriana.proyect2.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionManager {
    private User loggedInUser;
    private Long loggedInUser1;

    public Long getLoggedInUser1() {
        return loggedInUser1;
    }

    public void login1(Long userId) {
        this.loggedInUser1 = userId;
    }

    public void logout1() {
        this.loggedInUser1 = null;
    }

    public boolean isLoggedIn1() {
        return this.loggedInUser1 != null;
    }

    public Long getUser1() {
        return loggedInUser1;
    }

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

    public User getUser() {
        return loggedInUser;
    }
}