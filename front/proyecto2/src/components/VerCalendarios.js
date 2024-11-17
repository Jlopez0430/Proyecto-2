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
            setCalendarios(response.data);  // Guardar los calendarios en el estado
        } catch (error) {
            console.error("Error fetching calendars:", error);
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
