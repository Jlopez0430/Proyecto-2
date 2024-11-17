import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Calendarios from './components/Calendarios';
import PrivateRoute from './components/PrivateRoute';
import Menu from './components/Menu';

import './App.css';
import Eliminar from "./components/Eliminar";

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(
        !!localStorage.getItem("token")
    );

    return (
        <Router>
            <Routes>
                <Route path="/" element={<Navigate to="/login" />} />
                <Route path="/login" element={<Login setIsAuthenticated={setIsAuthenticated} />} />
                <Route path="/register" element={<Register />} />
                <Route
                    path="/calendarios/crear"
                    element={<PrivateRoute isAuthenticated={isAuthenticated}><Calendarios setIsAuthenticated={setIsAuthenticated} /></PrivateRoute>}
                />
                <Route
                    path="/calendarios"
                    element={<PrivateRoute isAuthenticated={isAuthenticated}><Calendarios setIsAuthenticated={setIsAuthenticated} /></PrivateRoute>}
                />
                <Route path="/menu" element={<Menu />} />
                <Route path="/servicios" element={<Eliminar />} />
            </Routes>
        </Router>
    );
}

export default App;
