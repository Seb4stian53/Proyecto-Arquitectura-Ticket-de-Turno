<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración</title>
    <link rel="stylesheet" href="../css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
    <h2>Resumen General</h2>
    <p>Bienvenido, <strong>${sessionScope.adminUsuario}</strong></p>

    <section class="dashboard">
        <div class="card">
            <h3>Total de Turnos</h3>
            <p>${totalTurnos}</p>
        </div>
        <div class="card">
            <h3>Turnos Pendientes</h3>
            <p>${pendientes}</p>
        </div>
        <div class="card">
            <h3>Turnos Resueltos</h3>
            <p>${resueltos}</p>
        </div>
        <div class="card">
            <h3>Turnos Cancelados</h3>
            <p>${cancelados}</p>
        </div>
    </section>

    <section>
        <h2>Estadísticas Visuales</h2>
        <canvas id="graficaTurnos" width="400" height="200"></canvas>
    </section>
</main>

<footer>
    <p>© 2025 Sistema de Turnos Educativos - Administración</p>
</footer>

<script src="../js/dashboard.js"></script>
</body>
</html>
