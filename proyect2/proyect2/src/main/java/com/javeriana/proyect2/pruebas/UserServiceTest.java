import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.repository.CalendarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalendarioServiceIntegrationTest {

    @Mock
    private CalendarioRepository calendarioRepository;

    @Mock
    private SessionManager sessionManager;

    @InjectMocks
    private CalendarioService calendarioService;

    private User mockUser;
    private Calendario mockCalendario;

    @BeforeEach
    void setUp() {
        // Inicializar los mocks y el servicio
        MockitoAnnotations.openMocks(this);

        // Simulamos un usuario logueado
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Juan");

        // Creamos un calendario simulado
        mockCalendario = new Calendario();
        mockCalendario.setId(1L);
        mockCalendario.setName("Reunión de proyecto");
    }
    @Test
    void TestCalendary_LoggedUser() throws Exception {
        // Simular que el usuario está logueado
        when(sessionManager.isLoggedIn()).thenReturn(true);
        when(sessionManager.getUser()).thenReturn(mockUser);

        // Simular el guardado exitoso del calendario
        when(calendarioRepository.save(any(Calendario.class))).thenReturn(mockCalendario);

        // Ejecutar el método para crear el calendario
        Calendario result = calendarioService.createCalendario(mockCalendario);

        // Validar que el calendario fue creado y guardado correctamente
        assertNotNull(result);
        assertEquals(mockCalendario.getName(), result.getName());

        // Verificar que los métodos correctos fueron invocados
        verify(sessionManager, times(1)).isLoggedIn();
        verify(sessionManager, times(1)).getUser();
        verify(calendarioRepository, times(1)).save(mockCalendario);
    }

    @Test
    void TestCreateCalendary_NoLogged() {
        // Simular que el usuario no está logueado
        when(sessionManager.isLoggedIn()).thenReturn(false);

        // Intentar crear un calendario y verificar que lanza una excepción
        Exception exception = assertThrows(Exception.class, () -> {
            calendarioService.createCalendario(mockCalendario);
        });

        // Verificar el mensaje de la excepción
        assertEquals("Debe iniciar sesión antes de crear un calendario.", exception.getMessage());

        // Verificar que el repositorio no intentó guardar el calendario
        verify(calendarioRepository, never()).save(any(Calendario.class));
    }
}