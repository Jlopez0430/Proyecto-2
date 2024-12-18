import React, { useState, useEffect } from 'react';
import api from '../services/api';
import CalendarForm from './CalendarForm';
import { useNavigate } from 'react-router-dom';

function Calendarios({ setIsAuthenticated }) {
    const [calendarios, setCalendarios] = useState([]);
    const navigate = useNavigate();

    // Función para obtener los calendarios del usuario desde el backend
    const fetchCalendarios = async () => {
        try {
            const userId1 = localStorage.getItem('userId');
            const userId = +userId1; // Asegurarse de convertir userId a número
            if (!userId) {
                throw new Error("No se encontró un userId en el localStorage.");
            }

            // Uso de backticks para interpolar userId en la URL
            const response = await api.get(`/users/${userId}/calendarios`);
            setCalendarios(response.data); // Guardar los calendarios en el estado
        } catch (error) {
            console.error("Error al obtener los calendarios:", error);
        }
    };

    // Función para cerrar sesión
    const handleLogout = () => {
        navigate("/menu");
    };

    // Obtener los calendarios cuando el componente se monta
    useEffect(() => {
        fetchCalendarios();
    }, []);

    return (
        <div>
            <h1>Crear Calendarios</h1>
            {/* Formulario para crear nuevos calendarios */}
            <CalendarForm fetchCalendarios={fetchCalendarios}/>
            <ul>
                {/* Lista de calendarios */}
                {calendarios.map((calendar) => (
                    <li key={calendar.id}>{calendar.name}</li>
                ))}
            </ul>
            <button onClick={handleLogout}>Volver al Menú</button>
        </div>
    );
}

export default Calendarios;