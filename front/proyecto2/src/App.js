import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Calendarios from './components/Calendarios';
import Actualizar from './components/Actualizar';
import PrivateRoute from './components/PrivateRoute';
import Menu from './components/Menu';
<<<<<<< HEAD
import VerCalendarios from "./components/VerCalendarios";
import Actualizar from './components/Actualizar';
import Eliminar from "./components/Eliminar";

=======
import VerCalendarios from './components/VerCalendarios';
>>>>>>> dcd405d575fc5dc1fdcbf9d61572cdf87029e359
import './App.css';
import Eliminar from "./components/Eliminar";


function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    return (
        <Router>
            <Routes>
                <Route path="/" element={<Navigate to="/login" />} />
                <Route path="/login" element={<Login setIsAuthenticated={setIsAuthenticated} />} />
                <Route path="/register" element={<Register />} />

                {/* Rutas protegidas */}
                <Route
                    path="/calendarios"
                    element={
                        <PrivateRoute isAuthenticated={isAuthenticated}>
                            <Calendarios setIsAuthenticated={setIsAuthenticated} />
                        </PrivateRoute>
                    }
                />
                <Route
<<<<<<< HEAD
                    path="/menu"
                    element={<PrivateRoute isAuthenticated={isAuthenticated}><Menu setIsAuthenticated={setIsAuthenticated} /></PrivateRoute>}
                />
                <Route path="/vercalendarios" element={<VerCalendarios />} />
                <Route path="/act" element={<Actualizar />} />
=======
                    path="/act"
                    element={
                        <PrivateRoute isAuthenticated={isAuthenticated}>
                            <Actualizar />
                        </PrivateRoute>
                    }
                />
                <Route
                    path="/vercalendarios"
                    element={
                        <PrivateRoute isAuthenticated={isAuthenticated}>
                            <VerCalendarios />
                        </PrivateRoute>
                    }
                />

                {/* Ruta para el menú */}
                <Route path="/menu" element={<Menu setIsAuthenticated={setIsAuthenticated} />} />
>>>>>>> dcd405d575fc5dc1fdcbf9d61572cdf87029e359
                <Route path="/eliminar" element={<Eliminar />} />
            </Routes>
        </Router>
    );
}

export default App;
