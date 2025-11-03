<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Turno</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Registro de Turno</h1>
</header>

<main>
    <form method="POST" action="registrar-turno">
        <h2>Datos del Alumno</h2>
        <label>CURP:</label>
        <input type="text" name="curp_alumno" maxlength="18" required>

        <label>Nombre:</label>
        <input type="text" name="nombre_alumno" required>

        <label>Apellido Paterno:</label>
        <input type="text" name="paterno_alumno" required>

        <label>Apellido Materno:</label>
        <input type="text" name="materno_alumno">

        <h2>Datos del Solicitante</h2>
        <label>Nombre del Solicitante:</label>
        <input type="text" name="nombre_solicitante" required>

        <label>Teléfono:</label>
        <input type="tel" name="telefono" pattern="[0-9]{10}" required>

        <label>Correo Electrónico:</label>
        <input type="email" name="correo" required>

        <h2>Detalles del Turno</h2>
        <label>Nivel Educativo:</label>
        <select name="nivel_educativo" required>
            <option value="">Seleccione...</option>
            <option value="Primaria">Primaria</option>
            <option value="Secundaria">Secundaria</option>
            <option value="Preparatoria">Preparatoria</option>
        </select>

        <label>Asunto:</label>
        <textarea name="asunto" rows="3" required></textarea>

        <label>Municipio:</label>
        <select name="id_municipio_fk" required>
            <option value="">Seleccione...</option>
            <option value="1">Saltillo</option>
            <option value="2">Torreón</option>
            <option value="3">Monclova</option>
            <option value="4">Piedras Negras</option>
            <option value="5">Ciudad Acuña</option>
        </select>

        <button type="submit">Registrar Turno</button>
    </form>

    <div class="centered">
        <a href="index.jsp" class="link">← Volver al inicio</a>
    </div>
</main>
</body>
</html>
