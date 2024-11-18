import React, { useState, useEffect } from 'react';
import api from '../services/api';
import { useNavigate } from 'react-router-dom';

function VerCalendarios({ setIsAuthenticated }) {
    const [calendarios, setCalendarios] = useState([]);
    const navigate = useNavigate();

    // Función para obtener los calendarios del usuario
    const fetchCalendarios = async () => {
        try {
            const userId1 = localStorage.getItem('userId');
            const userId = +userId1; // Convertir a número
            if (!userId) {
                throw new Error("No se encontró un userId en el localStorage.");
            }

            const response = await api.get(`/usuarios/${userId}`);
            const calendariosData = response.data;

            // Verifica si la respuesta es un array
            if (Array.isArray(calendariosData)) {
                setCalendarios(calendariosData);
            } else {
                console.warn("La respuesta no es un array. Datos:", calendariosData);
                setCalendarios([]); // Asigna un array vacío en caso de que la respuesta no sea un array
            }
        } catch (error) {
            console.error("Error fetching calendars:", error);
            setCalendarios([]); // Si hay un error, asegura que calendarios sea un array vacío
        }
    };


    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('userId');
        setIsAuthenticated(false);
        navigate("/login");
    };

    // Obtener los calendarios cuando el componente se monta
    useEffect(() => {
        fetchCalendarios();
    }, []);

    return (
        <div>
            <h1>Tus Calendarios</h1>
            <button onClick={handleLogout}>Cerrar Sesión</button>
            <ul>
                {calendarios.length > 0 ? (
                    calendarios.map((calendar) => (
                        <li key={calendar.id}>{calendar.name}</li>
                    ))
                ) : (
                    <p>No tienes calendarios disponibles.</p>
                )}
            </ul>
        </div>
    );
}

export default VerCalendarios;
