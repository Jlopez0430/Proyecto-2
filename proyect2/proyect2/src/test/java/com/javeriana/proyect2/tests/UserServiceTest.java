package com.javeriana.proyect2.tests;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SessionManager sessionManager;

    @InjectMocks
    private UserService userService;

    // Inicializa los mocks antes de ejecutar las pruebas
    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    // Test 1: Eliminar usuario exitosamente
    @Test
    void testDeleteUser_ShouldRemoveUserSuccessfully() {
        // Creamos un usuario simulado
        User user = new User();
        user.setId(1L);

        // Simulamos que el repositorio encuentra al usuario
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Ejecutamos el método deleteUser del servicio
        userService.deleteUser(1L);

        // Verificamos que el repositorio ha llamado al método delete con el usuario correcto
        verify(userRepository, times(1)).delete(user);
    }

    // Test 2: Fallar si el usuario no es encontrado
    @Test
    void testDeleteUser_ShouldFailIfUserNotFound() {
        // Simulamos que el repositorio no encuentra al usuario
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Ejecutamos el método deleteUser y esperamos una excepción
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteUser(1L);
        });

        // Verificamos que el mensaje de la excepción es correcto
        assertEquals("User not found", exception.getMessage());
    }

    // Test 3: Login exitoso del usuario
    @Test
    void testLogin_ShouldLoginUserSuccessfully() {
        // Creamos un usuario simulado con nombre y contraseña
        User user = new User();
        user.setName("Juan");
        user.setPassword("password123");

        // Simulamos que el repositorio encuentra un usuario con el nombre correcto
        when(userRepository.findByName("Juan")).thenReturn(user);

        // Simulamos que el password es correcto y que sessionManager se maneja correctamente
        when(sessionManager.getUser()).thenReturn(user);

        // Ejecutamos el método de login
        boolean loginSuccess = userService.login("Juan", "password123");

        // Validamos que el login fue exitoso
        assertTrue(loginSuccess);

        // Verificamos que el sessionManager llamó al método login con el usuario
        verify(sessionManager, times(1)).login(user);
    }

    // Test 4: Fallar el login cuando la contraseña es incorrecta
    @Test
    void testLogin_ShouldFailWhenPasswordIsIncorrect() {
        User user = new User();
        user.setName("Juan");
        user.setPassword("wrongpassword");

        // Simulamos que el repositorio encuentra un usuario con el nombre correcto
        when(userRepository.findByName("Juan")).thenReturn(user);

        // Ejecutamos el método login con la contraseña incorrecta
        boolean loginSuccess = userService.login("Juan", "password123");

        // Validamos que el login fue fallido
        assertFalse(loginSuccess);

        // Verificamos que no se intentó hacer login en la sesión
        verify(sessionManager, never()).login(user);
    }

    // Test 5: Crear un usuario que ya existe debería lanzar excepción
    @Test
    void testCreateUser_ShouldThrowExceptionWhenUserExists() {
        User user = new User();
        user.setName("Juan");
        user.setEmail("juan@example.com");

        // Simulamos que ya existe un usuario con ese nombre o email
        when(userRepository.save(any(User.class))).thenThrow(new IllegalArgumentException("El usuario ya está creado con ese nombre o email."));

        // Ejecutamos el método createUser y esperamos una excepción
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(user);
        });

        // Verificamos que el mensaje de la excepción es correcto
        assertEquals("El usuario ya está creado con ese nombre o email.", exception.getMessage());
    }

    // Servicio de usuario
    static class UserService {

        private final UserRepository userRepository;
        private final SessionManager sessionManager;

        // Constructor del servicio
        public UserService(UserRepository userRepository, SessionManager sessionManager) {
            this.userRepository = userRepository;
            this.sessionManager = sessionManager;
        }

        // Método para eliminar un usuario
        public void deleteUser(Long id) {
            // Buscar al usuario por ID y lanzar una excepción si no lo encuentra
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Eliminar el usuario
            userRepository.delete(user);
        }

        // Método para login del usuario
        public boolean login(String name, String password) {
            // Buscar el usuario por nombre
            User user = userRepository.findByName(name);

            // Verificar que el usuario no es nulo y que el password coincide
            if (user != null && user.getPassword().equals(password)) {
                // Iniciar sesión con sessionManager
                sessionManager.login(user);
                return true;
            }
            return false;
        }

        // Método para crear un usuario
        public void createUser(User user) {
            try {
                userRepository.save(user);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("El usuario ya está creado con ese nombre o email.");
            }
        }
    }

    // Clases y repositorios simulados
    static class User {
        private Long id;
        private String name;
        private String password;
        private String email;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    interface UserRepository {
        Optional<User> findById(Long id);
        void delete(User user);
        User findByName(String name);
        User save(User user);
    }

    interface SessionManager {
        void login(User user);
        User getUser();
    }
}