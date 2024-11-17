import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Menu() {
    const navigate = useNavigate();
    const [selectedOption, setSelectedOption] = useState('');

    const menuItems = [
        { name: "Crear", path: "/calendarios" },
        { name: "Eliminar", path: "/servicios" },
        { name: "Actualizar", path: "/acerca" },
        { name: "Ver", path: "/contacto" }
    ];

    const handleClick = (item) => {
        setSelectedOption(item.name);
        navigate(item.path); // Navega a la ruta correspondiente
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
            </div>
            {selectedOption && <p>Has seleccionado: {selectedOption}</p>}
        </div>
    );
}

export default Menu;
