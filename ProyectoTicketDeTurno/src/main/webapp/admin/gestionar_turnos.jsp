<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestionar Turnos</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<header>
    <h1>Gestión de Turnos</h1>
    <nav>
        <a href="dashboard.jsp">Dashboard</a> |
        <a href="gestionar_turnos.jsp">Turnos</a> |
        <a href="gestionar_municipios.jsp">Municipios</a> |
        <a href="<%= request.getContextPath() %>/logout" class="btn secondary">Cerrar Sesión</a>
    </nav>
</header>

<main>
    <h2>Listado de Turnos</h2>

    <form action="<%= request.getContextPath() %>/TurnoServlet" method="get">
        <input type="text" name="busqueda" placeholder="Buscar por nombre o municipio...">
        <button type="submit">Buscar</button>
    </form>

    <table border="1" class="tabla">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre del Estudiante</th>
                <th>Municipio</th>
                <th>Estatus</th>
                <th>Fecha</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="turno" items="${listaTurnos}">
                <tr>
                    <td>${turno.id_turno}</td>
                    <td>${turno.nombre_estudiante}</td>
                    <td>${turno.municipio}</td>
                    <td>${turno.estatus}</td>
                    <td>${turno.fecha}</td>
                    <td>
                        <form action="<%= request.getContextPath() %>/TurnoServlet" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${turno.id_turno}">
                            <button type="submit" name="accion" value="editar">Editar</button>
                            <button type="submit" name="accion" value="eliminar" 
                                    onclick="return confirm('¿Seguro que deseas eliminar este turno?');">
                                Eliminar
                            </button>
                        </form>
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
