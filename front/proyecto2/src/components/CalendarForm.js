import React, { useState } from 'react';
import api from '../services/api';
import axios from "axios";

function CalendarForm({ fetchCalendarios }) {
    const [name, setName] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const [fecha, setFecha] = useState('');
    const [hora, setHora] = useState('');
    const [importancia, setImportancia] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const userId1 = localStorage.getItem('userId');
            const userId = +userId1;
            if (!userId) {
                throw new Error("No se encontró un userId en el localStorage. Inicie sesión nuevamente.");
            } else{
                console.log("si guarda");
                // Refresca la lista de calendarios
                console.log("falla aqui 1");
                setName(name);
                console.log("falla aqui 2", name);
                setDescripcion(descripcion);
                console.log("falla aqui 3", descripcion);
                setFecha(fecha);
                console.log("falla aqui 4", fecha);
                setHora(hora);
                console.log("falla aqui 5", hora);
                setImportancia(importancia);
                console.log("falla aqui 6", importancia);
                 await api.post(`/calendario/${userId}`, { name, descripcion,fecha,hora, importancia });
                 console.log("si sirve?")
            }

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
