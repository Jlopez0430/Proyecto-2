import React, { useState, useEffect } from 'react';
import axios from 'axios';
import CalendarForm from './CalendarForm';

function Calendarios() {
    const [calendarios, setCalendarios] = useState([]);

    const fetchCalendarios = async () => {
        try {
            const response = await axios.get('/api/users/{userId}/calendarios');
            setCalendarios(response.data);
        } catch (error) {
            console.error("Error fetching calendars:", error);
        }
    };

    useEffect(() => {
        fetchCalendarios();
    }, []);

    return (
        <div>
            <h1>Your Calendars</h1>
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
