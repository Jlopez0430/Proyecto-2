<<<<<<< HEAD

import React, { useState, useEffect } from 'react';
import api from '../services/api';
import { useNavigate } from 'react-router-dom';

function Menu({setIsAuthenticated}) {
=======
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Menu({ setIsAuthenticated }) {
>>>>>>> dcd405d575fc5dc1fdcbf9d61572cdf87029e359
    const navigate = useNavigate();
    const [selectedOption, setSelectedOption] = useState('');

    // Menú con todas las opciones de calendario
    const menuItems = [
        { name: "Crear Calendarios", path: "/calendarios" },
        { name: "Eliminar Calendarios", path: "/eliminar" },
        { name: "Actualizar Calendarios", path: "/act" },
        { name: "Ver Calendarios", path: "/vercalendarios" }
<<<<<<< HEAD
=======

>>>>>>> dcd405d575fc5dc1fdcbf9d61572cdf87029e359
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
                {/* Renderizamos las opciones del menú */}
                {menuItems.map((item, index) => (
                    <button
                        key={index}
                        className="todo-btn"
                        onClick={() => handleClick(item)}
                    >
                        {item.name}
                    </button>
                ))}
<<<<<<< HEAD
=======
                {/* Opción para cerrar sesión */}
>>>>>>> dcd405d575fc5dc1fdcbf9d61572cdf87029e359
                <button onClick={handleLogout}>Cerrar Sesión</button>
            </div>
            {/* Mostrar la opción seleccionada */}
            {selectedOption && <p>Has seleccionado: {selectedOption}</p>}
        </div>
    );
}

export default Menu;

