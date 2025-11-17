<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Administradores</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <h1>Panel de Administración</h1>
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
    <h2>Agregar Nuevo Administrador</h2>

    <form action="${pageContext.request.contextPath}/panel/admins/create" method="post" class="admin-form">
        <div class="form-group">
            <label for="username">Nombre de Usuario:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="btn primary">Agregar Administrador</button>
    </form>

    <%-- Sistema de Mensajes de Feedback --%>
    <c:if test="${param.mensaje == 'exito'}">
        <p class="mensaje exito">Administrador agregado correctamente.</p>
    </c:if>
    <c:if test="${param.mensaje == 'eliminado'}">
        <p class="mensaje info">Administrador eliminado correctamente.</p>
    </c:if>
     <c:if test="${param.mensaje == 'error'}">
        <p class="mensaje error">Ocurrió un error. El usuario podría ya existir.</p>
    </c:if>

    <hr>

    <h2>Administradores Existentes</h2>


    <table class="data-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre de Usuario</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>

            <c:forEach var="admin" items="${listaAdmins}">
                <tr>
                    <td>${admin.ID_Admin}</td>
                    <td>${admin.usuario}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/panel/admins/delete?id=${admin.ID_Admin}" 
                           class="btn danger" 
                           onclick="return confirm('¿Estás seguro de que quieres eliminar a este administrador?');">
                           Eliminar
                        </a>
                    </td>
                </tr>
             </c:forEach>
            <c:if test="${empty listaAdmins}">
                <tr>
                    <td colspan="3">No se encontraron administradores.</td>
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