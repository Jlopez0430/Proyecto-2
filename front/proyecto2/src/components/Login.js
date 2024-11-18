import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

function Login({ setIsAuthenticated }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/api/session/login', { username, password });
            if (response.status === 200) {
                localStorage.setItem('token', response.data.token);
                localStorage.setItem('userId', response.data.userId);
                setIsAuthenticated(true);
                console.log("token", response.data.token);
                console.log("id", response.data.userId);
                navigate("/menu");
            }
        } catch (error) {
            console.error("Login failed:", error);
        }
    };

    return (
        <div>
            <h2>Iniciar Sesión</h2>
            <form onSubmit={handleLogin}>
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="username" required />
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="password" required />
                <button type="submit">Login</button>
            </form>
            <p>¿No tienes cuenta? <Link to="/register">Regístrate aquí</Link></p>
        </div>
    );
}

export default Login;
