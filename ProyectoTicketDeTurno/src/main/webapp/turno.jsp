<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle y Modificación de Turno</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Gestión de Turnos</h1>
</header>

<main>
    <h2>Detalle y Modificación del Turno</h2>

    <c:if test="${not empty mensajeError}">
        <p class="error">${mensajeError}</p>
    </c:if>

    <c:choose>
        <c:when test="${not empty turno}">
            
            <form action="<%= request.getContextPath() %>/TurnoServlet" method="post">
                <input type="hidden" name="accion" value="actualizarPublico">
                <input type="hidden" name="id_turno" value="${turno.id_turno}">

                <%--
                    =======================================
                    BLOQUE DE INFORMACIÓN NO MODIFICABLE (Visualización)
                    =======================================
                --%>
                <div class="info-group">
                    <h3>Datos del Turno</h3>
                    <div class="form-row">
                        <div>
                            <label>ID de Turno:</label>
                            <p><strong>#${turno.id_turno} - ${turno.numero_turno_municipio}</strong></p>
                        </div>
                        <div>
                            <label>CURP del Alumno:</label>
                            <p><strong>${turno.curp_alumno}</strong></p>
                        </div>
                        <div>
                            <label>Municipio:</label>
                            <%-- Asumimos que ${turno.municipio} tiene el nombre, no el ID --%>
                            <p><strong>${turno.municipio}</strong></p>
                        </div>
                    </div>
                    <div class="form-row">
                        <div>
                            <label>Fecha de Creación:</label>
                            <p><strong>${turno.fecha_creacion}</strong></p>
                        </div>
                        <div>
                            <label>Estatus Actual:</label>
                            <%-- Lógica para asignar la clase CSS según el estatus --%>
                            <c:set var="estatusClass">
                                <c:choose>
                                    <c:when test="${turno.estatus eq 'Cancelado'}">estatus-cancelado</c:when>
                                    <c:when test="${turno.estatus eq 'Resuelto'}">estatus-resuelto</c:when>
                                    <c:otherwise>estatus-pendiente</c:otherwise>
                                </c:choose>
                            </c:set>
                            <p>
                                <strong class="${estatusClass}">
                                    ${turno.estatus}
                                </strong>
                            </p>
                        </div>
                    </div>
                </div>

                <%--
                    =======================================
                    BLOQUE DE INFORMACIÓN MODIFICABLE
                    =======================================
                --%>
                <div class="info-group">
                    <h3>Datos a Modificar</h3>

                    <%-- Nombre del Solicitante --%>
                    <label for="nombre_solicitante">Nombre Completo del Solicitante:</label>
                    <input type="text" id="nombre_solicitante" name="nombre_solicitante" 
                           value="${turno.nombre_solicitante}" required>

                    <%-- Teléfono y Correo --%>
                    <div class="form-row">
                        <div>
                            <label for="telefono">Teléfono de Contacto:</label>
                            <input type="tel" id="telefono" name="telefono" 
                                   value="${turno.telefono}" required maxlength="15">
                        </div>
                        <div>
                            <label for="correo">Correo Electrónico:</label>
                            <input type="email" id="correo" name="correo" 
                                   value="${turno.correo}" required>
                        </div>
                    </div>
                    
                    <%-- Nombre y Apellidos del Alumno --%>
                    <div class="form-row">
                        <div>
                            <label for="nombre_alumno">Nombre del Alumno:</label>
                            <input type="text" id="nombre_alumno" name="nombre_alumno" 
                                   value="${turno.nombre_alumno}" required>
                        </div>
                        <div>
                            <label for="paterno_alumno">Apellido Paterno:</label>
                            <input type="text" id="paterno_alumno" name="paterno_alumno" 
                                   value="${turno.paterno_alumno}" required>
                        </div>
                        <div>
                            <label for="materno_alumno">Apellido Materno:</label>
                            <input type="text" id="materno_alumno" name="materno_alumno" 
                                   value="${turno.materno_alumno}">
                        </div>
                    </div>
                    
                    <%-- Nivel Educativo --%>
                    <label for="nivel_educativo">Nivel Educativo:</label>
                    <input type="text" id="nivel_educativo" name="nivel_educativo" 
                           value="${turno.nivel_educativo}" required maxlength="50">

                    <%-- Asunto --%>
                    <label for="asunto">Detalle del Asunto:</label>
                    <textarea id="asunto" name="asunto" rows="4" required>${turno.asunto}</textarea>

                    <button type="submit">Guardar Modificaciones</button>
                </div>
            </form>
            
            <%--
                =======================================
                BOTÓN DE CANCELACIÓN (Solo si no está ya cancelado/resuelto)
                =======================================
            --%>
            <c:if test="${turno.estatus eq 'Pendiente'}">
                <div class="centered">
                    <form action="<%= request.getContextPath() %>/TurnoServlet" method="post" style="margin-top: 1.5em;">
                        <input type="hidden" name="accion" value="cancelarPublico">
                        <input type="hidden" name="id_turno" value="${turno.id_turno}">
                        <button type="submit" class="cancel-btn"
                                onclick="return confirm('¡ATENCIÓN! ¿Está seguro que desea CANCELAR este turno? Esta acción no se puede revertir.');">
                            ❌ Cancelar Turno
                        </button>
                    </form>
                </div>
            </c:if>

        </c:when>
        <c:otherwise>
            <p class="error">El turno no fue encontrado o no tiene permiso para verlo.</p>
        </c:otherwise>
    </c:choose>

    <div class="centered">
        <a href="index.jsp" class="link">← Volver al inicio</a>
    </div>

</main>

<footer>
    <p>© 2025 Sistema de Turnos Educativos - Usuario</p>
</footer>
</body>
</html>