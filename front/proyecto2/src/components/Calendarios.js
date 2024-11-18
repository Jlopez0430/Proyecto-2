import React, { useState, useEffect } from 'react';
import api from '../services/api';
import { useNavigate } from 'react-router-dom';

function Calendarios({ setIsAuthenticated }) {
    const [calendarios, setCalendarios] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [loadingRecordatorio, setLoadingRecordatorio] = useState(null); // Estado para la carga de recordatorio
    const navigate = useNavigate();

    // Función para obtener los calendarios desde la API
    const fetchCalendarios = async () => {
        setLoading(true);
        setError(null);
        try {
            const userId = localStorage.getItem('userId');
            if (!userId) {
                throw new Error("No se encontró un userId en el localStorage.");
            }

            const response = await api.get(`/calendario/${userId}`);
            console.log("Respuesta de la API:", response.data);

            if (Array.isArray(response.data)) {
                setCalendarios(response.data);
            } else {
                console.warn("La respuesta no es un array. Forzando a array vacío.");
                setCalendarios([]);
            }
        } catch (error) {
            console.error("Error al obtener los calendarios:", error);
            setError("No se pudieron cargar los calendarios. Intenta nuevamente.");
            setCalendarios([]);
        } finally {
            setLoading(false);
        }
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('userId');
        setIsAuthenticated(false);
        navigate("/login", { replace: true });
    };

    const handleBack = () => {
        navigate(-1);
    };

    // Función para manejar la creación de recordatorio
    const handleCreateRecordatorio = async (calendarId, create) => {
        setLoadingRecordatorio(calendarId); // Mostrar estado de carga por calendario

        try {
            if (create) {
                // Crear el recordatorio
                const response = await api.post(`/recordatorio/${calendarId}`);
                if (response.status === 200) {
                    alert("Recordatorio creado exitosamente.");
                } else {
                    alert("Hubo un problema al crear el recordatorio.");
                }
            } else {
                // No hacer nada si el usuario elige "No"
                alert("No se creó el recordatorio.");
            }
        } catch (error) {
            console.error("Error al crear el recordatorio:", error);
            alert("Hubo un problema al crear el recordatorio.");
        } finally {
            setLoadingRecordatorio(null); // Ocultar estado de carga
        }
    };

    useEffect(() => {
        fetchCalendarios();
    }, []);

    return (
        <div>
            <h1>Tus Calendarios</h1>
            <button onClick={handleLogout}>Cerrar Sesión</button>
            <button onClick={handleBack}>Volver</button>

            {loading && <p>Cargando calendarios...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}

            {!loading && !error && (
                <ul>
                    {calendarios.length > 0 ? (
                        calendarios.map((calendar) => (
                            <li key={calendar.id}>
                                <strong>{calendar.name}</strong>
                                <p>Descripción: {calendar.descripcion}</p>
                                <p>Fecha: {calendar.fecha}</p>
                                <p>Hora: {calendar.hora}</p>
                                <p>Importancia: {calendar.importancia}</p>

                                {/* Preguntar si crear un recordatorio */}
                                <p>¿Crear recordatorio?</p>
                                <button
                                    disabled={loadingRecordatorio === calendar.id}
                                    onClick={() => handleCreateRecordatorio(calendar.id, true)}
                                >
                                    Sí
                                </button>
                                <button
                                    disabled={loadingRecordatorio === calendar.id}
                                    onClick={() => handleCreateRecordatorio(calendar.id, false)}
                                >
                                    No
                                </button>

                                {loadingRecordatorio === calendar.id && <p>Creando recordatorio...</p>}
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
