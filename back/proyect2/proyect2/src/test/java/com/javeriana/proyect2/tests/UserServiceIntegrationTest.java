package com.javeriana.proyect2.tests;

import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.repository.UserRepository;
import com.javeriana.proyect2.services.SessionManager;
import com.javeriana.proyect2.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionManager sessionManager;

    @Test
    public void testCreateUser_ShouldCreateUserSuccessfully() {
        User user = new User();
        user.setName("Juan");
        user.setPassword("password123");
        user.setEmail("juan@example.com");

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser.getId());
        assertEquals("Juan", createdUser.getName());

        User foundUser = userRepository.findById(createdUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals("juan@example.com", foundUser.getEmail());
    }


}