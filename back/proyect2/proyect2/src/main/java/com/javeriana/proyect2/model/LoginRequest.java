package com.javeriana.proyect2.model;

public class LoginRequest {
    private String name;
    private String password;

    // Getters y Setters
    public String getUserName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
