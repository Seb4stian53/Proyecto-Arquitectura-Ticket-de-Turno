<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Turno</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Editar Turno</h1>
</header>

<main>
    <form method="POST" action="actualizar-turno">
        <input type="hidden" name="id_turno" value="${turno.id_turno}">

        <label>CURP:</label>
        <input type="text" name="curp_alumno" value="${turno.curp_alumno}" readonly>

        <label>Nombre Alumno:</label>
        <input type="text" name="nombre_alumno" value="${turno.nombre_alumno}">

        <label>Apellido Paterno:</label>
        <input type="text" name="paterno_alumno" value="${turno.paterno_alumno}">

        <label>Apellido Materno:</label>
        <input type="text" name="materno_alumno" value="${turno.materno_alumno}">

        <label>Nombre Solicitante:</label>
        <input type="text" name="nombre_solicitante" value="${turno.nombre_solicitante}">

        <label>Teléfono:</label>
        <input type="text" name="telefono" value="${turno.telefono}">

        <label>Correo:</label>
        <input type="email" name="correo" value="${turno.correo}">

        <label>Nivel Educativo:</label>
        <input type="text" name="nivel_educativo" value="${turno.nivel_educativo}">

        <label>Asunto:</label>
        <textarea name="asunto">${turno.asunto}</textarea>

        <button type="submit">Guardar Cambios</button>
    </form>

    <form method="POST" action="cancelar-turno">
        <input type="hidden" name="id_turno" value="${turno.id_turno}">
        <button type="submit" class="btn secondary">Cancelar Turno</button>
    </form>

    <div class="centered">
        <a href="index.jsp" class="link">← Volver al inicio</a>
    </div>
</main>
</body>
</html>
