<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Consultar Turno</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Consultar o Modificar Turno</h1>
</header>

<main>
    <form method="POST" action="consultar-turno">
        <label for="curp_alumno">CURP del Alumno:</label>
        <input type="text" id="curp_alumno" name="curp_alumno" maxlength="18" required>

        <label for="numero_turno_municipio">Número de Turno:</label>
        <input type="number" id="numero_turno_municipio" name="numero_turno_municipio" required>

        <button type="submit">Buscar Turno</button>
    </form>

    <div class="centered">
        <a href="index.jsp" class="link">← Volver al inicio</a>
    </div>
</main>
</body>
</html>
