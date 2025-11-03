<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login Administrador</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Acceso Administrador</h1>
</header>

<main>
    <form method="POST" action="login">
        <p class="error">${error}</p>
        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required>

        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required>

        <button type="submit">Ingresar</button>
    </form>

    <div class="centered">
        <a href="index.jsp" class="link">← Volver al inicio</a>
    </div>
</main>
</body>
</html>
