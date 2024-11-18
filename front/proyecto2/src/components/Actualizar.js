import React, { useState, useEffect } from 'react';
import api from '../services/api';
import ActualizarForm from './ActualizarForm';
import { useNavigate } from 'react-router-dom';

function Actualizar({ setIsAuthenticated }) {
    const [actualizar, setActualizar] = useState([]);
    const navigate = useNavigate();

    // Función para obtener los calendarios del usuario desde el backend
    const fetchActualizar = async () => {
        try {
            const userId1 = localStorage.getItem('userId');
            const userId = +userId1; // Asegurarse de convertir userId a número
            if (!userId) {
                throw new Error("No se encontró un userId en el localStorage.");
            }

            // Uso de backticks para interpolar userId en la URL
            const response = await api.get(`/users/${userId}/calendarios`);
            setActualizar(response.data); // Guardar los calendarios en el estado
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
        fetchActualizar();
    }, []);

    return (
        <div>
            <h1>Actualizar Calendarios</h1>
            {/* Formulario para crear nuevos calendarios */}
            <ActualizarForm fetchActualizar={fetchActualizar()}/>
            <ul>
                {/* Lista de calendarios */}
                {actualizar.map((actu) => (
                    <li key={actu.id}>{actu.name}</li>
                ))}
            </ul>
            <button onClick={handleLogout}>Volver al Menú</button>
        </div>
    );
}

export default Actualizar;