import React, { useState, useEffect } from 'react';
import api from '../services/api';
import CalendarForm from './CalendarForm';
import { useNavigate } from 'react-router-dom';

function Calendarios({ setIsAuthenticated }) {
    const [calendarios, setCalendarios] = useState([]); // Estado inicial como array vacío.
    const navigate = useNavigate();

    const fetchCalendarios = async () => {
        try {
            const userId1 = localStorage.getItem('userId');
            const userId = +userId1; // Convertimos userId a número.
            if (!userId) {
                console.error("Error: userId no válido en localStorage.");
                throw new Error("No se encontró un userId válido en el localStorage.");
            }

            const response = await api.get(`/calendario/${userId}`);
            console.log("Respuesta completa de la API:", response);

            // Validamos que la API devuelve un array.
            if (response && Array.isArray(response.data)) {
                console.log("Datos obtenidos:", response.data);
                setCalendarios(response.data);
            } else {
                console.error("La API no devolvió un array. Respuesta:", response.data);
                setCalendarios([]); // Configurar como array vacío en caso de datos no válidos.
            }
        } catch (error) {
            console.error("Error al obtener los calendarios:", error);
            setCalendarios([]); // Configurar como array vacío en caso de error.
        }
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('userId');
        setIsAuthenticated(false);
        navigate("/login");
    };

    useEffect(() => {
        fetchCalendarios();
    }, []);

    return (
        <div>
            <h1>Tus Calendarios</h1>
            <button onClick={handleLogout}>Cerrar Sesión</button>
            <CalendarForm fetchCalendarios={fetchCalendarios} />
            <ul>
                {Array.isArray(calendarios) && calendarios.length > 0 ? (
                    calendarios.map((calendar) => (
                        <li key={calendar.id}>
                            <strong>{calendar.name}</strong>
                            <br />
                            {calendar.descripcion}
                            <br />
                            Fecha: {calendar.fecha}, Hora: {calendar.hora}, Importancia: {calendar.importancia}
                        </li>
                    ))
                ) : (
                    <li>No hay calendarios disponibles.</li>
                )}
            </ul>
        </div>
    );
}

export default Calendarios;
