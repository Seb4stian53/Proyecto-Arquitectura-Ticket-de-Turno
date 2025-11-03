<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Comprobante de Registro</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Turno Registrado Exitosamente</h1>
</header>

<main>
    <h2>Datos del Turno</h2>
    <p><strong>Número de turno:</strong> ${turno.numero_turno_municipio}</p>
    <p><strong>Alumno:</strong> ${turno.nombre_alumno} ${turno.paterno_alumno} ${turno.materno_alumno}</p>
    <p><strong>Municipio:</strong> ${turno.municipio.nombre}</p>
    <p><strong>Estatus:</strong> ${turno.estatus}</p>
    <p><strong>Fecha de creación:</strong> ${turno.fecha_creacion}</p>

    <div class="centered">
        <a href="index.jsp" class="btn">Volver al Inicio</a>
    </div>
</main>
</body>
</html>
