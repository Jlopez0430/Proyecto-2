//package com.javeriana.proyect2.tests;
//
//import com.javeriana.proyect2.model.Calendario;
//import com.javeriana.proyect2.model.User;
//import com.javeriana.proyect2.repository.CalendarioRepository;
//import com.javeriana.proyect2.services.CalendarioService;
//import com.javeriana.proyect2.services.SessionManager;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//import java.util.List;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class CalendarioServiceTest {
//
//    @Mock
//    private CalendarioRepository calendarioRepository;
//
//    @Mock
//    private SessionManager sessionManager;
//
//    @InjectMocks
//    private CalendarioService calendarioService;
//
//    // Inicializa los mocks antes de ejecutar las pruebas
//    public CalendarioServiceTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    // Test 1: Crear un calendario exitosamente
//    @Test
//    void testCreateCalendario_ShouldCreateCalendarioSuccessfully() throws Exception {
//        Calendario calendario = new Calendario();
//        calendario.setName("Evento");
//
//        when(sessionManager.isLoggedIn()).thenReturn(true);
//        when(sessionManager.getUser()).thenReturn(new User()); // Retorna un usuario simulado
//        when(calendarioRepository.save(calendario)).thenReturn(calendario);
//
//        Calendario createdCalendario = calendarioService.createCalendario(calendario);
//
//        assertNotNull(createdCalendario);
//        verify(calendarioRepository, times(1)).save(calendario);
//    }
//
//    // Test 2: Fallar al crear calendario si no está logueado
//    @Test
//    void testCreateCalendario_ShouldFailIfNotLoggedIn() {
//        Calendario calendario = new Calendario();
//        calendario.setName("Evento");
//
//        when(sessionManager.isLoggedIn()).thenReturn(false);
//
//        Exception exception = assertThrows(Exception.class, () -> {
//            calendarioService.createCalendario(calendario);
//        });
//
//        assertEquals("Debe iniciar sesión antes de crear un calendario.", exception.getMessage());
//        verify(calendarioRepository, never()).save(any(Calendario.class));
//    }
//
//    // Test 3: Obtener todos los calendarios exitosamente
//    @Test
//    void testGetAllCalendarios_ShouldReturnAllCalendarios() throws Exception {
//        List<Calendario> calendarios = new ArrayList<>();
//        calendarios.add(new Calendario());
//        calendarios.add(new Calendario());
//
//        when(sessionManager.isLoggedIn()).thenReturn(true);
//        when(calendarioRepository.findAll()).thenReturn(calendarios);
//
//        List<Calendario> result = calendarioService.getAllCalendarios();
//
//        assertEquals(2, result.size());
//        verify(calendarioRepository, times(1)).findAll();
//    }
//
//    // Test 4: Fallar al obtener todos los calendarios si no está logueado
//    @Test
//    void testGetAllCalendarios_ShouldFailIfNotLoggedIn() {
//        when(sessionManager.isLoggedIn()).thenReturn(false);
//
//        Exception exception = assertThrows(Exception.class, () -> {
//            calendarioService.getAllCalendarios();
//        });
//
//        assertEquals("Debe iniciar sesión para ver los calendarios.", exception.getMessage());
//        verify(calendarioRepository, never()).findAll();
//    }
//
//    // Test 5: Obtener un calendario por ID exitosamente
//    @Test
//    void testGetCalendarioById_ShouldReturnCalendario() throws Exception {
//        Calendario calendario = new Calendario();
//        calendario.setId(1L);
//
//        when(sessionManager.isLoggedIn()).thenReturn(true);
//        when(calendarioRepository.findById(1L)).thenReturn(Optional.of(calendario));
//
//        Calendario result = calendarioService.getCalendarioById(1L);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//        verify(calendarioRepository, times(1)).findById(1L);
//    }
//
//    // Test 6: Fallar al obtener un calendario por ID si no está logueado
//    @Test
//    void testGetCalendarioById_ShouldFailIfNotLoggedIn() {
//        when(sessionManager.isLoggedIn()).thenReturn(false);
//
//        Exception exception = assertThrows(Exception.class, () -> {
//            calendarioService.getCalendarioById(1L);
//        });
//
//        assertEquals("Debe iniciar sesión para ver un calendario.", exception.getMessage());
//        verify(calendarioRepository, never()).findById(anyLong());
//    }
//
//    // Test 7: Actualizar un calendario exitosamente
//    @Test
//    void testUpdateCalendario_ShouldUpdateCalendarioSuccessfully() throws Exception {
//        Calendario existingCalendario = new Calendario();
//        existingCalendario.setId(1L);
//        existingCalendario.setName("Original");
//
//        Calendario updatedCalendario = new Calendario();
//        updatedCalendario.setName("Updated");
//
//        when(sessionManager.isLoggedIn()).thenReturn(true);
//        when(calendarioRepository.findById(1L)).thenReturn(Optional.of(existingCalendario));
//        when(calendarioRepository.save(existingCalendario)).thenReturn(existingCalendario);
//
//        Calendario result = calendarioService.updateCalendario(1L, updatedCalendario);
//
//        assertEquals("Updated", result.getName());
//        verify(calendarioRepository, times(1)).save(existingCalendario);
//    }
//
//    // Test 8: Fallar al actualizar calendario si no está logueado
//    @Test
//    void testUpdateCalendario_ShouldFailIfNotLoggedIn() {
//        Calendario updatedCalendario = new Calendario();
//        updatedCalendario.setName("Updated");
//
//        when(sessionManager.isLoggedIn()).thenReturn(false);
//
//        Exception exception = assertThrows(Exception.class, () -> {
//            calendarioService.updateCalendario(1L, updatedCalendario);
//        });
//
//        assertEquals("Debe iniciar sesión para actualizar un calendario.", exception.getMessage());
//        verify(calendarioRepository, never()).save(any(Calendario.class));
//    }
//
//
//
//    // Test 10: Fallar al eliminar calendario si no está logueado
//    @Test
//    void testDeleteCalendario_ShouldFailIfNotLoggedIn() {
//        when(sessionManager.isLoggedIn()).thenReturn(false);
//
//        Exception exception = assertThrows(Exception.class, () -> {
//            calendarioService.deleteCalendario(1L);
//        });
//
//        assertEquals("Debe iniciar sesión para eliminar un calendario.", exception.getMessage());
//        verify(calendarioRepository, never()).delete(any(Calendario.class));
//    }
//}