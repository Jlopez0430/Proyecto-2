import React, { useState } from 'react';
import api from '../services/api';

function CalendarForm({ fetchCalendarios }) {
    const [name, setName] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const [fecha, setFecha] = useState('');
    const [hora, setHora] = useState('');
    const [importancia, setImportancia] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const userId = localStorage.getItem('userId');
            if (!userId) {
                throw new Error("No se encontró un userId en el localStorage. Inicie sesión nuevamente.");
            }

            await api.post(`/users/${userId}/calendarios`, { name, descripcion, fecha, hora, importancia });
            fetchCalendarios(); // Refresca la lista de calendarios
            setName('');
            setDescripcion('');
            setFecha('');
            setHora('');
            setImportancia('');
        } catch (error) {
            console.error("Error creating calendar:", error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Nombre del Calendario" required />
            <input type="text" value={descripcion} onChange={(e) => setDescripcion(e.target.value)} placeholder="Descripción" required />
            <input type="date" value={fecha} onChange={(e) => setFecha(e.target.value)} placeholder="Fecha" required />
            <input type="time" value={hora} onChange={(e) => setHora(e.target.value)} placeholder="Hora" required />
            <input type="text" value={importancia} onChange={(e) => setImportancia(e.target.value)} placeholder="Importancia" required />
            <button type="submit">Crear Calendario</button>
        </form>
    );
}

export default CalendarForm;
