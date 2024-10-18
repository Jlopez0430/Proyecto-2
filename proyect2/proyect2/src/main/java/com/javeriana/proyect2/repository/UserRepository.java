package com.javeriana.proyect2.repository;

import com.javeriana.proyect2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javeriana.proyect2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}