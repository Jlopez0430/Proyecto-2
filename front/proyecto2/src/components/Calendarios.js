import React, { useState, useEffect } from 'react';
import api from '../services/api';
import CalendarForm from './CalendarForm';
import { useNavigate } from 'react-router-dom';

function Calendarios({ setIsAuthenticated }) {
    const [calendarios, setCalendarios] = useState([]);
    const navigate = useNavigate();

    const fetchCalendarios = async () => {
        try {
            const userId = localStorage.getItem('userId');
            if (!userId) {
                throw new Error("No se encontró un userId en el localStorage.");
            }

            const response = await api.get(`/users/${userId}/calendarios`);
            setCalendarios(response.data);
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

    useEffect(() => {
        fetchCalendarios();
    }, []);

    return (
        <div>
            <h1>Tus Calendarios</h1>
            <button onClick={handleLogout}>Cerrar Sesión</button>
            <CalendarForm fetchCalendarios={fetchCalendarios} />
            <ul>
                {calendarios.map((calendar) => (
                    <li key={calendar.id}>{calendar.name}</li>
                ))}
            </ul>
        </div>
    );
}

export default Calendarios;