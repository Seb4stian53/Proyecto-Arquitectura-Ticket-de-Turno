<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestionar Municipios</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<header>
    <h1>Gestión de Municipios</h1>
    <nav>
        <a href="dashboard.jsp">Dashboard</a> |
        <a href="gestionar_turnos.jsp">Turnos</a> |
        <a href="gestionar_municipios.jsp">Municipios</a> |
        <a href="<%= request.getContextPath() %>/logout" class="btn secondary">Cerrar Sesión</a>
    </nav>
</header>

<main>
    <h2>Listado de Municipios</h2>

    <form action="<%= request.getContextPath() %>/MunicipioServlet" method="post" class="formulario">
        <input type="text" name="nombre" placeholder="Nombre del Municipio" required>
        <button type="submit" name="accion" value="agregar">Agregar</button>
    </form>

    <table border="1" class="tabla">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre del Municipio</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="m" items="${listaMunicipios}">
                <tr>
                    <td>${m.id_municipio}</td>
                    <td>${m.nombre}</td>
                    <td>
                        <form action="<%= request.getContextPath() %>/MunicipioServlet" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${m.id_municipio}">
                            <input type="text" name="nuevoNombre" placeholder="Nuevo nombre">
                            <button type="submit" name="accion" value="editar">Editar</button>
                            <button type="submit" name="accion" value="eliminar"
                                    onclick="return confirm('¿Eliminar municipio ${m.nombre}?');">
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
