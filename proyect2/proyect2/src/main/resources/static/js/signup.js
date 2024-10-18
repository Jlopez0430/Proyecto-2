document.getElementById("signupForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita que el formulario se envíe automáticamente

    // Validaciones básicas
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const errorMessage = document.getElementById("signup-error-message");

    if (!name || !email || !password) {
        errorMessage.textContent = "Todos los campos son obligatorios.";
        return;
    }

    if (!validateEmail(email)) {
        errorMessage.textContent = "Por favor, ingresa un correo electrónico válido.";
        return;
    }

    // Aquí puedes enviar los datos al servidor o realizar otras acciones
    alert("Cuenta creada exitosamente!");
    errorMessage.textContent = "";
});

// Función de validación de correo
function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
}
