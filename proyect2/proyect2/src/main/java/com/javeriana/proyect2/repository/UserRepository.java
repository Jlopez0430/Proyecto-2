package com.javeriana.proyect2.repository;

import com.javeriana.proyect2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}