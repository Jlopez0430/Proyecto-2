
import React, { useState, useEffect } from 'react';
import api from '../services/api';
import { useNavigate } from 'react-router-dom';

function Menu({setIsAuthenticated}) {
    const navigate = useNavigate();
    const [selectedOption, setSelectedOption] = useState('');

    const menuItems = [
        { name: "Crear Calendarios", path: "/calendarios" },
        { name: "Eliminar Calendarios", path: "/eliminar" },
        { name: "Actualizar Calendarios", path: "/act" },
        { name: "Ver Calendarios", path: "/vercalendarios" }
    ];

    const handleClick = (item) => {
        setSelectedOption(item.name);
        navigate(item.path); // Navega a la ruta correspondiente
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('userId');
        setIsAuthenticated(false);
        navigate("/login");
    };

    return (
        <div className="App">
            <h2>Menú de Opciones</h2>
            <div>
                {menuItems.map((item, index) => (
                    <button
                        key={index}
                        className="todo-btn"
                        onClick={() => handleClick(item)}
                    >
                        {item.name}
                    </button>
                ))}
                <button onClick={handleLogout}>Cerrar Sesión</button>
            </div>
            {selectedOption && <p>Has seleccionado: {selectedOption}</p>}
        </div>
    );
}

export default Menu;
