<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Comprobante de Registro</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"> 
</head>
<body>
<header>
    <h1>Turno Registrado Exitosamente</h1>
</header>

<main>
    <h2>¡Guarda esta información!</h2>
    <p>Tu turno ha sido generado. Necesitarás tu CURP y el número de turno para futuras consultas o modificaciones.</p>
    
    <div class="comprobante-box"> <%-- Puedes añadir estilos a esta clase en tu CSS --%>
        
        <h3>Detalles de tu Cita</h3>
        <p><strong>Número de Turno:</strong> <span class="turno-numero">${turno.numero_turno_municipio}</span></p>
        <p><strong>Municipio:</strong> ${turno.municipio.nombre}</p>
        
        <%-- ¡AQUÍ ESTÁN LOS NUEVOS CAMPOS! --%>
        <p><strong>Nivel Educativo:</strong> ${turno.nivelAcademico.nombre}</p>
        <p><strong>Asunto del Trámite:</strong> ${turno.asunto.descripcion}</p>
        
        <hr>

        <h3>Información del Alumno</h3>
        <p><strong>Alumno:</strong> ${turno.nombre_alumno} ${turno.paterno_alumno} ${turno.materno_alumno}</p>
        <p><strong>CURP:</strong> ${turno.curp_alumno}</p>
        
        <hr>

        <h3>Estado del Turno</h3>
        <p><strong>Estatus:</strong> ${turno.estatus}</p>
        <p><strong>Fecha de Registro:</strong> ${turno.fecha_creacion}</p>
    </div>

    <div class="centered">
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn">Volver al Inicio</a>
    </div>
</main>
</body>
</html>