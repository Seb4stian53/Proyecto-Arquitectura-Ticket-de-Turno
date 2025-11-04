<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestionar Turnos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <h1>Gestión de Turnos</h1>
   <nav>
        <a href="${pageContext.request.contextPath}/panel/dashboard">Dashboard</a> |
        <a href="${pageContext.request.contextPath}/panel/turnos">Gestionar Turnos</a> |
        <a href="${pageContext.request.contextPath}/panel/municipios">Municipios</a> |
        <a href="${pageContext.request.contextPath}/panel/admins">Gestionar Admins</a> |
        <a href="${pageContext.request.contextPath}/logout" class="btn secondary">Cerrar Sesión</a>
   </nav>
</header>

<main>
    <h2>Listado de Turnos</h2>

    <form action="${pageContext.request.contextPath}/panel/turnos" method="get">
        <input type="text" name="busqueda" placeholder="Buscar por nombre, CURP o municipio..." value="${param.busqueda}">
        <button type="submit">Buscar</button>
    </form>

    <table class="data-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>CURP</th>
                <th>Nombre del Estudiante</th>
                <th>Municipio</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Estatus</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="turno" items="${listaTurnos}">
                <tr>
                    <td>${turno.numero_turno_municipio}</td>
                    <td>${turno.curp_alumno}</td>
                    <td>${turno.nombre_alumno} ${turno.paterno_alumno}</td>
                    <td>${turno.municipio.nombre}</td>
                    <td>${turno.fechaFormateada}</td>
                    <td>${turno.horaFormateada}</td>
                    <td><span class="estatus estatus-${turno.estatus}">${turno.estatus}</span></td>
                    <td>
                        <%-- CAMBIO CLAVE: Un solo enlace para ver el detalle --%>
                        <a href="${pageContext.request.contextPath}/panel/turno/ver?id=${turno.id_turno}" class="btn">
                            Ver / Gestionar
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty listaTurnos}">
                <tr>
                    <td colspan="8">No se encontraron turnos.</td>
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