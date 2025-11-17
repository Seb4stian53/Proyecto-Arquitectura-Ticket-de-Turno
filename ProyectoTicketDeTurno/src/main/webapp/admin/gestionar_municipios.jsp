<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestionar Municipios</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <h1>Gestión de Municipios</h1>
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
    <h2>Agregar Nuevo Municipio</h2>
    
    <%-- Mensajes de feedback para el usuario --%>
    <c:if test="${param.mensaje == 'exito_crear'}"><p class="mensaje exito">Municipio agregado correctamente.</p></c:if>
    <c:if test="${param.mensaje == 'exito_editar'}"><p class="mensaje exito">Municipio actualizado correctamente.</p></c:if>
    <c:if test="${param.mensaje == 'exito_eliminar'}"><p class="mensaje info">Municipio eliminado correctamente.</p></c:if>
    <c:if test="${param.mensaje == 'error'}"><p class="mensaje error">Ocurrió un error al procesar la solicitud.</p></c:if>

    <form action="${pageContext.request.contextPath}/panel/municipios/create" method="post" class="admin-form">
        <div class="form-group">
            <label for="nombre">Nombre del Municipio:</label>
            <input type="text" id="nombre" name="nombre" placeholder="Ej. Saltillo" required>
        </div>
        <button type="submit" class="btn primary">Agregar Municipio</button>
    </form>
    
    <hr>

    <h2>Municipios Existentes</h2>
    <table class="data-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre del Municipio</th>
                <th style="width: 40%;">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="m" items="${listaMunicipios}">
                <tr>
                    <td>${m.id_municipio}</td>
                    <form action="${pageContext.request.contextPath}/panel/municipios/update" method="post">
                        <input type="hidden" name="id_municipio" value="${m.id_municipio}">
                        <td>
                            <input type="text" name="nombre" value="${m.nombre}" required class="input-in-table">
                        </td>
                        <td>
                            <button type="submit" class="btn">Actualizar</button>
                            <a href="${pageContext.request.contextPath}/panel/municipios/delete?id=${m.id_municipio}" 
                               class="btn danger"
                               onclick="return confirm('¿Estás seguro de que quieres eliminar el municipio ${m.nombre}?');">
                               Eliminar
                            </a>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            <c:if test="${empty listaMunicipios}">
                <tr>
                    <td colspan="3">No se encontraron municipios.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</main>

<footer>
    <p>© 2025 Sistema de Turnos Educativos - Administración</p>
</footer>
</body>
</html>
