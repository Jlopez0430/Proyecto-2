import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

function Register() {
    const [username, setusername] = useState('');
    const [password, setpassword] = useState('');

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            await axios.post('/api/users', { username, password });
            alert("Usuario registrado exitosamente.");
        } catch (error) {
            console.error("Error en el registro:", error);
        }
    };

    return (
        <div>
            <h2>Registro</h2>
            <form onSubmit={handleRegister}>
                <input type="text" value={username} onChange={(e) => setusername(e.target.value)} placeholder="username" required />
                <input type="password" value={password} onChange={(e) => setpassword(e.target.value)} placeholder="password" required />
                <button type="submit">Registrarse</button>
            </form>
            <p>¿Ya tienes cuenta? <Link to="/login">Inicia sesión aquí</Link></p>
        </div>
    );
}

export default Register;
