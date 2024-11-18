import React, { useState } from 'react';
import api from '../services/api';
import axios from "axios";

function EliminarForm({ fetchCalendarios }) {
    const [id, setId] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const userId1 = localStorage.getItem('userId');
            const userId = +userId1;

            if (!userId) {
                throw new Error("No se encontró un userId en el localStorage. Inicie sesión nuevamente.");
            } else{
                console.log("si guarda");
                setId(id);

                const id2 = +id;
                await api.delete(`/calendario/delete/${id2}`);
                console.log("si sirve?")
            }

        } catch (error) {
            console.error("Error creating calendar:", error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" value={id} onChange={(e) => setId(e.target.value)}
                   placeholder="Id del Calendario" required/>
            <button type="submit">Eliminar Calendario</button>
        </form>
    );
}

export default EliminarForm;
