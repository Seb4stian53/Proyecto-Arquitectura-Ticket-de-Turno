<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Turno</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <h1>Registro de Turno</h1>
</header>

<main>
    <c:if test="${not empty error}">
        <div class="error-message">
            <p>${error}</p>
        </div>
    </c:if>
    
    <form method="POST" action="registrar-turno">
        <h2>Datos del Alumno</h2>
        <label>CURP:</label>
        <input type="text" name="curp_alumno" maxlength="18" required>

        <label>Nombre:</label>
        <input type="text" name="nombre_alumno" required>

        <label>Apellido Paterno:</label>
        <input type="text" name="paterno_alumno" required>

        <label>Apellido Materno:</label>
        <input type="text" name="materno_alumno">

        <h2>Datos del Solicitante</h2>
        <label>Nombre del Solicitante:</label>
        <input type="text" name="nombre_solicitante" required>

        <label>Teléfono:</label>
        <input type="tel" name="telefono" pattern="[0-9]{10}" required>

        <label>Correo Electrónico:</label>
        <input type="email" name="correo" required>

        <h2>Detalles del Turno</h2>
        <label>Nivel Educativo:</label>
        <select name="id_nivel_fk" required>
            <option value="">Seleccione...</option>
            <c:forEach var="nivel" items="${listaNiveles}">
                <option value="${nivel.id_nivel}" ${nivel.id_nivel == turnoFallido.nivelAcademico.id_nivel ? 'selected' : ''}>
                    ${nivel.nombre}
                </option>
            </c:forEach>
        </select>

        <label>Asunto:</label>
        <select name="id_asunto_fk" required>
            <option value="">Seleccione...</option>
            <c:forEach var="asunto" items="${listaAsuntos}">
                <option value="${asunto.id_asunto}" ${asunto.id_asunto == turnoFallido.asunto.id_asunto ? 'selected' : ''}>
                    ${asunto.descripcion}
                </option>
            </c:forEach>
        </select>

        <label>Municipio:</label>
        <select name="id_municipio_fk" required>
        <option value="">Seleccione un municipio...</option>
        
        <%-- Usamos c:forEach para iterar sobre la lista que nos envió el Servlet --%>
        <%-- La llave "listaMunicipios" debe coincidir con la del request.setAttribute --%>
        <c:forEach var="municipio" items="${listaMunicipios}">
        
            <%-- Por cada objeto 'municipio' en la lista, creamos una opción --%>
            <%-- El 'value' será el ID, pero el texto que ve el usuario será el nombre --%>
            <option value="${municipio.id_municipio}">${municipio.nombre}</option>
            
        </c:forEach>
    </select>

        <button type="submit">Registrar Turno</button>
    </form>

    <div class="centered">
        <a href="index.jsp" class="link">← Volver al inicio</a>
    </div>
</main>
</body>
</html>
