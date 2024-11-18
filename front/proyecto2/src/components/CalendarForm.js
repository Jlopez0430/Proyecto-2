import React, { useState } from 'react';
import api from '../services/api';
import axios from "axios";

function CalendarForm({ fetchCalendarios }) {
    const [name, setName] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const [fecha, setFecha] = useState('');
    const [hora, setHora] = useState('');
    const [importancia, setImportancia] = useState('');
    const [crearRecordatorio, setCrearRecordatorio] = useState(null); // Estado para manejar la creación del recordatorio

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const userId1 = localStorage.getItem('userId');
            const userId = +userId1;
            if (!userId) {
                throw new Error("No se encontró un userId en el localStorage. Inicie sesión nuevamente.");
            }

            // Crear el calendario
            const response = await api.post(`/calendario/${userId}`, { name, descripcion, fecha, hora, importancia });
            console.log("Calendario creado:", response.data);

            // Si el usuario selecciona "Sí", crear el recordatorio
            if (crearRecordatorio) {
                await api.post(`/recordatorio/${response.data.id}`);
                alert("Recordatorio creado exitosamente.");
            }

            // Refrescar la lista de calendarios
            fetchCalendarios();
            alert("Calendario creado exitosamente.");

            // Resetear formulario
            setName('');
            setDescripcion('');
            setFecha('');
            setHora('');
            setImportancia('');
            setCrearRecordatorio(null); // Resetear el estado del recordatorio

        } catch (error) {
            console.error("Error creating calendar:", error);
            alert("Hubo un error al crear el calendario.");
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Nombre del Calendario" required />
            <input type="text" value={descripcion} onChange={(e) => setDescripcion(e.target.value)} placeholder="Descripción" required />
            <input type="date" value={fecha} onChange={(e) => setFecha(e.target.value)} placeholder="Fecha" required />
            <input type="time" value={hora} onChange={(e) => setHora(e.target.value)} placeholder="Hora" required />
            <input type="text" value={importancia} onChange={(e) => setImportancia(e.target.value)} placeholder="Importancia" required />

            {/* Preguntar si desea crear un recordatorio */}
            <div>
                <p>¿Crear recordatorio?</p>
                <button type="button" onClick={() => setCrearRecordatorio(true)}>Sí</button>
                <button type="button" onClick={() => setCrearRecordatorio(false)}>No</button>
            </div>

            <button type="submit">Crear Calendario</button>
        </form>
    );
}

export default CalendarForm;
