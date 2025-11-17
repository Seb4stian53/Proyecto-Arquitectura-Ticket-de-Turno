<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestionar Asuntos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <h1>Gestión de Asuntos</h1>
    <nav>
        <a href="${pageContext.request.contextPath}/panel/dashboard">Dashboard</a> |
        <a href="${pageContext.request.contextPath}/panel/turnos">Gestionar Turnos</a> |
        <a href="${pageContext.request.contextPath}/panel/municipios">Municipios</a> |
        <a href="${pageContext.request.contextPath}/panel/asuntos">Gestionar Asuntos</a> |
        <a href="${pageContext.request.contextPath}/panel/admins">Gestionar Admins</a> |
        <a href="${pageContext.request.contextPath}/logout" class="btn secondary">Cerrar Sesión</a>
    </nav>
</header>

<main>
    <div class="admin-management-card">
        <h2>Agregar Nuevo Asunto</h2>

            <%-- Mensajes de feedback para el usuario --%>
    <c:if test="${param.mensaje == 'exito_crear'}"><p class="mensaje exito">Asunto agregado correctamente.</p></c:if>
    <c:if test="${param.mensaje == 'exito_editar'}"><p class="mensaje exito">Asunto actualizado correctamente.</p></c:if>
    <c:if test="${param.mensaje == 'exito_eliminar'}"><p class="mensaje info">Asunto eliminado correctamente.</p></c:if>
    <c:if test="${param.mensaje == 'error'}"><p class="mensaje error">Ocurrió un error al procesar la solicitud.</p></c:if>

        <form action="${pageContext.request.contextPath}/panel/asunto/agregar" method="post" class="inline-form">
            <div class="form-group">
                <label for="descripcion">Descripción del Asunto:</label>
                <input type="text" id="descripcion" name="descripcion" placeholder="Ej. Cambio de Escuela" required>
            </div>
            <button type="submit" class="btn primary">Agregar Asunto</button>
        </form>
    </div>

    <div class="admin-management-card">
        <h2>Asuntos Existentes</h2>
        <table class="data-table">
            <thead>
            <tr>
                <th>Asuntos</th>
                <th style="width: 40%;">Acciones</th>
            </tr>
         <tbody>
            <c:forEach var="asunto" items="${listaAsuntos}">
            <tr>
                <form action="${pageContext.request.contextPath}/panel/asunto/actualizar" method="post" class="item-form">
                        <input type="hidden" name="id_asunto" value="${asunto.id_asunto}">
                        <td>
                        <input type="text" name="descripcion" value="${asunto.descripcion}" required>
                        </td>
                        <td>
                            <button type="submit" class="btn">Actualizar</button>
                    
                        <a href="${pageContext.request.contextPath}/panel/asunto/eliminar?id=${asunto.id_asunto}" 
                            class="btn danger"
                            onclick="return confirm('¿Estás seguro de que quieres eliminar este asunto?');">
                            Eliminar
                        </a>
                    </td>
                </form>
            </tr>
            </c:forEach>
            <c:if test="${empty listaAsuntos}">
                <p>No hay asuntos registrados.</p>
            </c:if>
        </tbody>
    </table>
</main>

<footer>
    <p>© 2025 Sistema de Turnos Educativos - Administración</p>
</footer>
</body>
</html>