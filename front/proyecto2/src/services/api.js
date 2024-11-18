import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api', // Cambia esto si el backend está en otra URL
    headers: {
        'Content-Type': 'application/json',
    },
});

// Interceptor para agregar el token a cada solicitud, si es necesario
api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token'); // Supone que guardas el token en localStorage
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

export default api;
