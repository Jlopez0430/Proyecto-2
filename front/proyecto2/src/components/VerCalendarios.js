import React, { useState, useEffect } from 'react';
import api from '../services/api';
import { useNavigate } from 'react-router-dom';

function VerCalendarios() {
    const [calendarios, setCalendarios] = useState([]);
    const navigate = useNavigate();

    // Función para obtener los calendarios del usuario desde el backend
    const fetchCalendarios = async () => {
        try {
            const userId = localStorage.getItem('userId'); // Obtener el userId del localStorage
            if (!userId) {
                throw new Error("No se encontró un userId en el localStorage.");
            }

            // Llamar al backend para obtener los calendarios del usuario
            const response = await api.get(`/calendario/usuarios/${userId}`);
            console.log("Calendarios obtenidos:", response.data);
            setCalendarios(response.data); // Actualizar el estado con los calendarios
        } catch (error) {
            console.error("Error al obtener los calendarios:", error);
        }
    };

    // Función para combinar la fecha y la hora en un solo objeto Date
    const getFormattedDateTime = (fecha, hora) => {
        // Si la fecha y la hora están por separado, combínalas para obtener un formato válido
        const formattedDate = new Date(`${fecha}T${hora}`);
        return formattedDate;
    };

    // Obtener los calendarios cuando el componente se monta
    useEffect(() => {
        fetchCalendarios();
    }, []);

    return (
        <div>
            <h1>Calendarios Disponibles</h1>
            <button onClick={() => navigate('/menu')}>Volver al Menú</button>
            <ul>
                {calendarios.length > 0 ? (
                    calendarios.map((calendario) => {
                        // Verifica si la fecha y hora son válidas
                        const dateTime = getFormattedDateTime(calendario.fecha, calendario.hora);

                        return (
                            <li key={calendario.id}>
                                <p><strong>ID:</strong> {calendario.id}</p> {/* Mostrar el ID */}
                                <p><strong>Nombre:</strong> {calendario.name}</p>
                                <p><strong>Descripción:</strong> {calendario.descripcion}</p>
                                <p><strong>Fecha:</strong> {dateTime.toLocaleDateString()}</p>
                                <p><strong>Hora:</strong> {dateTime.toLocaleTimeString()}</p>
                                <hr />
                            </li>
                        );
                    })
                ) : (
                    <p>No hay calendarios disponibles.</p>
                )}
            </ul>
        </div>
    );
}

export default VerCalendarios;
