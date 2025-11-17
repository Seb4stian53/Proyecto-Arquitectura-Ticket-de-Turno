<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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

    <section class="filter-section">
    <h2>Filtros de Estadísticas</h2>
    <form action="${pageContext.request.contextPath}/panel/dashboard" method="get" class="filter-form">
        
        <div class="form-group">
            <label for="idMunicipioFiltro">Municipio:</label>
            <select name="idMunicipioFiltro" id="idMunicipioFiltro" onchange="this.form.submit()">
                <option value="">Todos</option>
                <c:forEach var="municipio" items="${listaMunicipios}">
                    <option value="${municipio.id_municipio}" ${municipio.id_municipio == selectedIdMunicipioFiltro ? 'selected' : ''}>
                        ${municipio.nombre}
                    </option>
                </c:forEach>
            </select>
        </div>
        
        <div class="form-group">
            <label for="idAsuntoFiltro">Asunto:</label>
            <select name="idAsuntoFiltro" id="idAsuntoFiltro" onchange="this.form.submit()">
                <option value="">Todos</option>
                <c:forEach var="asunto" items="${listaAsuntos}">
                    <option value="${asunto.id_asunto}" ${asunto.id_asunto == selectedIdAsuntoFiltro ? 'selected' : ''}>
                        ${asunto.descripcion}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="estatusFiltro">Estatus:</label>
            <select name="estatusFiltro" id="estatusFiltro" onchange="this.form.submit()">
                <option value="">Todos</option>
                <option value="Pendiente" ${'Pendiente' eq selectedEstatusFiltro ? 'selected' : ''}>Pendiente</option>
                <option value="Resuelto" ${'Resuelto' eq selectedEstatusFiltro ? 'selected' : ''}>Resuelto</option>
                <option value="Cancelado" ${'Cancelado' eq selectedEstatusFiltro ? 'selected' : ''}>Cancelado</option>
            </select>
        </div>
        
        <div class="btn-container">
            <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/panel/dashboard'" class="btn secondary">Limpiar Filtros</button>
        </div>

    </form>
</section>

    <section>
    <div class="section-title-container">
        <h2>Estadísticas Visuales</h2>
    </div>

    <div class="visual-stats">
        <div class="chart-container">
            <h3>Turnos por Municipio</h3>
            <canvas id="graficaTurnosPorMunicipio"></canvas>
        </div>
        <div class="chart-container">
            <h3>Turnos por Asunto</h3>
            <canvas id="graficaTurnosPorAsunto"></canvas>
        </div>
        <div class="chart-container">
            <h3>Turnos por Estatus</h3>
            <canvas id="graficaTurnosPorEstado"></canvas>
        </div>
    </div>
    </section>
</main>

<footer>
    <p>© 2025 Sistema de Turnos Educativos - Administración</p>
</footer>

<script>
    const turnosPorMunicipioLabels = <c:out value="${turnosPorMunicipioLabels}" escapeXml="false" default="[]"/>;
    const turnosPorMunicipioData = <c:out value="${turnosPorMunicipioData}" escapeXml="false" default="[]"/>;
    const turnosPorAsuntoLabels = <c:out value="${turnosPorAsuntoLabels}" escapeXml="false" default="[]"/>;
    const turnosPorAsuntoData = <c:out value="${turnosPorAsuntoData}" escapeXml="false" default="[]"/>;
    const turnosPorEstadoLabels = <c:out value="${turnosPorEstadoLabels}" escapeXml="false" default="[]"/>; 
    const turnosPorEstadoData = <c:out value="${turnosPorEstadoData}" escapeXml="false" default="[]"/>; 
</script>

<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
</body>
</html>