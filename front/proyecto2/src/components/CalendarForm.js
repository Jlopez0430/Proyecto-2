import React, { useState } from 'react';
import axios from 'axios';

function CalendarForm({ fetchCalendarios }) {
    const [name, setName] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('/api/calendario', { name });
            fetchCalendarios(); // Refresh the calendar list
            setName('');
        } catch (error) {
            console.error("Error creating calendar:", error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Calendar Name" required />
            <button type="submit">Create Calendar</button>
        </form>
    );
}

export default CalendarForm;
