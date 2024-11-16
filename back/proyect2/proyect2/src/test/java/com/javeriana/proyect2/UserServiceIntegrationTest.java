package com.javeriana.proyect2;

import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.repository.UserRepository;
import com.javeriana.proyect2.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser_ShouldPersistInDatabase() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("securepassword");
        userService.createUser(user);

        Optional<User> foundUser = userRepository.findById(user.getId());
        assertTrue(foundUser.isPresent());
        assertEquals("John Doe", foundUser.get().getName());
    }

    @Test
    void testUpdateUser_ShouldPersistInDatabase() {
        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("password123");
        userService.createUser(user);

        Optional<User> foundUser = userRepository.findById(user.getId());
        assertTrue(foundUser.isPresent());

        // Llamada correcta al método updateUser
        userService.updateUser(foundUser.get().getId(), foundUser.get());

        Optional<User> updatedUser = userRepository.findById(user.getId());
        assertTrue(updatedUser.isPresent());
        assertEquals("Jane Doe", updatedUser.get().getName());
    }

    @Test
    void testDeleteUser_ShouldPersistInDatabase() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("securepassword");
        userService.createUser(user);

        Optional<User> foundUser = userRepository.findById(user.getId());
        assertTrue(foundUser.isPresent());

        userService.deleteUser(user.getId());

        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertTrue(deletedUser.isEmpty());
    }
}
