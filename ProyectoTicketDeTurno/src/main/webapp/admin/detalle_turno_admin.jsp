<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle de Turno #${turno.numero_turno_municipio}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <h1>Detalle del Turno #${turno.numero_turno_municipio} <span class="estatus estatus-${turno.estatus} small">${turno.estatus}</span></h1>
    <nav>
        <a href="${pageContext.request.contextPath}/panel/dashboard">Dashboard</a> |
        <a href="${pageContext.request.contextPath}/panel/turnos">Gestionar Turnos</a> |
        <a href="${pageContext.request.contextPath}/panel/municipios">Municipios</a> |
        <a href="${pageContext.request.contextPath}/panel/admins">Gestionar Admins</a> |
        <a href="${pageContext.request.contextPath}/logout" class="btn secondary">Cerrar Sesión</a>
   </nav>
</header>
<main>
    <c:if test="${param.mensaje == 'exito_estado'}">
        <p class="success-message">El estatus del turno ha sido actualizado correctamente.</p>
    </c:if>

    <div class="turno-detalle-grid">
        <div class="info-card">
            <h3>Gestionar Estatus</h3>
            <form action="${pageContext.request.contextPath}/panel/turno/actualizarEstado" method="post" class="admin-form">
                <input type="hidden" name="id_turno" value="${turno.id_turno}">
                <div class="form-group">
                    <label for="nuevo_estatus">Cambiar estatus a:</label>
                    <select name="nuevo_estatus" id="nuevo_estatus">
                        <option value="Pendiente" ${turno.estatus == 'Pendiente' ? 'selected' : ''}>Pendiente</option>
                        <option value="Resuelto" ${turno.estatus == 'Resuelto' ? 'selected' : ''}>Resuelto</option>
                        <option value="Cancelado" ${turno.estatus == 'Cancelado' ? 'selected' : ''}>Cancelado</option>
                    </select>
                </div>
                <button type="submit" class="btn primary">Actualizar Estatus</button>
            </form>
        </div>

        <div class="info-card">
            <h3>Información del Alumno</h3>
            <p><strong>Nombre:</strong> ${turno.nombre_alumno} ${turno.paterno_alumno} ${turno.materno_alumno}</p>
            <p><strong>CURP:</strong> ${turno.curp_alumno}</p>
            <p><strong>Nivel Educativo:</strong> ${turno.nivel_educativo}</p>
        </div>
        
        <div class="info-card">
            <h3>Información de Contacto</h3>
            <p><strong>Solicitante:</strong> ${turno.nombre_solicitante}</p>
            <p><strong>Teléfono:</strong> ${turno.telefono}</p>
            <p><strong>Correo:</strong> ${turno.correo}</p>
        </div>

        <div class="info-card full-width">
            <h3>Detalles del Turno</h3>
            <p><strong>Municipio:</strong> ${turno.municipio.nombre}</p>
            <p><strong>Fecha de Registro:</strong> ${turno.fechaFormateada} a las ${turno.horaFormateada}</p>
            <p><strong>Asunto:</strong></p>
            <p class="asunto-box">${turno.asunto}</p>
        </div>
    </div>

    <div class="centered" style="margin-top: 2rem;">
        <a href="${pageContext.request.contextPath}/panel/turnos" class="link">← Volver al listado de turnos</a>
    </div>
</main>
</body>
</html>