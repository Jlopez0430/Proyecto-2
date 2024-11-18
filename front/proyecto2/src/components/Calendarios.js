import React, { useState, useEffect } from 'react';
import api from '../services/api';
import CalendarForm from './CalendarForm';
import { useNavigate } from 'react-router-dom';

function Calendarios({ setIsAuthenticated }) {
    const [calendarios, setCalendarios] = useState([]); // Siempre inicializar como un array vacío
    const [loading, setLoading] = useState(true); // Estado para mostrar indicador de carga
    const [error, setError] = useState(null); // Estado para manejar errores
    const navigate = useNavigate();

    // Función para obtener los calendarios desde la API
    const fetchCalendarios = async () => {
        setLoading(true); // Mostrar indicador de carga
        setError(null); // Reiniciar errores
        try {
            const userId = localStorage.getItem('userId'); // Obtener el userId desde localStorage
            if (!userId) {
                throw new Error("No se encontró un userId en el localStorage.");
            }

            // Solicitar datos de la API
            const response = await api.get(`/calendario/${userId}`);
            console.log("Respuesta de la API:", response.data);

            // Validar y forzar que los datos sean un array
            if (Array.isArray(response.data)) {
                setCalendarios(response.data); // Actualizamos calendarios con los datos recibidos
            } else {
                console.warn("La respuesta no es un array. Forzando a array vacío.");
                setCalendarios([]); // Forzar a un array vacío si no es válido
            }
        } catch (error) {
            console.error("Error al obtener los calendarios:", error);
            setError("No se pudieron cargar los calendarios. Intenta nuevamente.");
            setCalendarios([]); // Reseteamos calendarios a un array vacío en caso de error
        } finally {
            setLoading(false); // Ocultamos indicador de carga
        }
    };

    const handleLogout = () => {
        // Cerramos sesión limpiando el almacenamiento local y redirigiendo al login
        localStorage.removeItem('token');
        localStorage.removeItem('userId');
        setIsAuthenticated(false);
        navigate("/login", { replace: true }); // replace evita volver al historial anterior
    };

    const handleBack = () => {
        navigate(-1); // Retrocede a la página anterior
    };

    // Llamar a fetchCalendarios al montar el componente
    useEffect(() => {
        fetchCalendarios();
    }, []);

    return (
        <div>
            <h1>Tus Calendarios</h1>
            <button onClick={handleLogout}>Cerrar Sesión</button>
            <button onClick={handleBack}>Volver</button>

            {/* Mostrar indicador de carga */}
            {loading && <p>Cargando calendarios...</p>}

            {/* Mostrar errores si ocurren */}
            {error && <p style={{ color: 'red' }}>{error}</p>}

            {/* Mostrar lista de calendarios */}
            {!loading && !error && (
                <ul>
                    {calendarios.length > 0 ? (
                        calendarios.map((calendar) => (
                            <li key={calendar.id}>
                                <strong>{calendar.name}</strong>
                            </li>
                        ))
                    ) : (
                        <p>No tienes calendarios disponibles.</p>
                    )}
                </ul>
            )}
        </div>
    );
}

export default Calendarios;