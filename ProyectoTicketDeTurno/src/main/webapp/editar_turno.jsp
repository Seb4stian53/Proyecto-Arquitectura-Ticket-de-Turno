<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Turno</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <h1>Editar Turno N° ${turno.numero_turno_municipio}</h1>
</header>

<main>
    <c:if test="${not empty success}">
        <div class="success-message">
            <p>${success}</p>
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="error-message">
            <p>${error}</p>
        </div>
    </c:if>
    
    <form method="POST" action="actualizar-turno">
        <%-- El ID es crucial para que el backend sepa qué turno actualizar --%>
        <input type="hidden" name="id_turno" value="${turno.id_turno}">

        <label>CURP (No se puede modificar):</label>
        <input type="text" name="curp_alumno_display" value="${turno.curp_alumno}" disabled>

        <label>Nombre Alumno:</label>
        <input type="text" name="nombre_alumno" value="${turno.nombre_alumno}" required>

        <label>Apellido Paterno:</label>
        <input type="text" name="paterno_alumno" value="${turno.paterno_alumno}" required>

        <label>Apellido Materno:</label>
        <input type="text" name="materno_alumno" value="${turno.materno_alumno}">

        <label>Nombre Solicitante:</label>
        <input type="text" name="nombre_solicitante" value="${turno.nombre_solicitante}" required>

        <label>Teléfono:</label>
        <input type="text" name="telefono" value="${turno.telefono}" required>

        <label>Correo:</label>
        <input type="email" name="correo" value="${turno.correo}" required>

        <label>Nivel Educativo:</label>
        <select name="nivel_educativo" required>
            <option value="">Seleccione...</option>
            <option value="Primaria" ${turno.nivel_educativo == 'Primaria' ? 'selected' : ''}>
                Primaria
            </option>
            <option value="Secundaria" ${turno.nivel_educativo == 'Secundaria' ? 'selected' : ''}>
                Secundaria
            </option>
            <option value="Preparatoria" ${turno.nivel_educativo == 'Preparatoria' ? 'selected' : ''}>
                Preparatoria
            </option>
        </select>

        <label>Asunto:</label>
        <textarea name="asunto" rows="4" required>${turno.asunto}</textarea>

        <button type="submit">Guardar Cambios</button>
    </form>

    <hr style="margin-top: 2em; margin-bottom: 2em;">

    <form method="POST" action="cancelar-turno" onsubmit="return confirm('¿Estás seguro de que deseas cancelar este turno? Esta acción no se puede deshacer.');">
        <input type="hidden" name="id_turno" value="${turno.id_turno}">
        <button type="submit" class="btn secondary">Cancelar Turno</button>
    </form>

    <div class="centered">
        <a href="${pageContext.request.contextPath}/index.jsp" class="link">← Volver al inicio</a>
    </div>
</main>
</body>
</html>