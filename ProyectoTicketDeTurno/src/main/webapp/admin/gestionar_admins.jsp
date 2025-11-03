<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Administradores</title>
    <link rel="stylesheet" href="../css/style.css"> <%-- Asegúrate que la ruta al CSS es correcta --%>
</head>
<body>
<header>
    <h1>Panel de Administración</h1>
    <nav>
        <a href="dashboard.jsp">Dashboard</a> |
        <a href="gestionar_turnos.jsp">Gestionar Turnos</a> |
        <a href="gestionar_municipios.jsp">Municipios</a> |
        <a href="gestionar_admins.jsp">Gestionar Admins</a> |
        <a href="<%= request.getContextPath() %>/logout" class="btn secondary">Cerrar Sesión</a>
    </nav>
</header>

<main>
    <h2>Agregar Nuevo Administrador</h2>

    <%-- Formulario para crear un nuevo admin --%>
    <form action="adminController?action=crear" method="post" class="admin-form">
        <div class="form-group">
            <label for="username">Nombre de Usuario:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="email">Correo Electrónico:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="btn primary">Agregar Administrador</button>
    </form>

    <%-- Muestra un mensaje de éxito o error si existe --%>
    <c:if test="${not empty mensaje}">
        <p class="mensaje ${tipoMensaje}">${mensaje}</p>
    </c:if>

    <hr>

    <h2>Administradores Existentes</h2>
    
    <%-- Tabla para listar los admins (esto es opcional pero muy útil) --%>
    <table class="data-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre de Usuario</th>
                <th>Correo Electrónico</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%-- Aquí listaremos los usuarios que traigamos de la base de datos --%>
            <%-- Este es un ejemplo, necesitarás un Servlet que cargue esta lista --%>
            <c:forEach var="admin" items="${listaAdmins}">
                <tr>
                    <td>${admin.id}</td>
                    <td>${admin.username}</td>
                    <td>${admin.email}</td>
                    <td>
                        <a href="adminController?action=eliminar&id=${admin.id}" class="btn danger">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</main>

<footer>
    <p>© 2025 Sistema de Turnos Educativos - Administración</p>
</footer>

</body>
</html>