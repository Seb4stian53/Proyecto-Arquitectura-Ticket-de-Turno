package proyectoturnos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import proyectoturnos.util.DatabaseConnection;

public class DashboardDAO {
    public Map<String, Integer> contarTurnosPorMunicipio(String estatusFiltro, Integer idAsuntoFiltro) {
        Map<String, Integer> resultados = new LinkedHashMap<>();
        StringBuilder sql = new StringBuilder("SELECT m.nombre, COUNT(t.id_turno) AS total ")
                .append("FROM turnos t ")
                .append("JOIN municipios m ON t.id_municipio_fk = m.id_municipio ")
                .append("JOIN asuntos a ON t.id_asunto_fk = a.id_asunto ") 
                .append("WHERE 1=1 "); 

        if (estatusFiltro != null && !estatusFiltro.isEmpty()) {
            sql.append("AND t.estatus = ? ");
        }
        if (idAsuntoFiltro != null && idAsuntoFiltro > 0) {
            sql.append("AND t.id_asunto_fk = ? ");
        }

        sql.append("GROUP BY m.nombre ")
           .append("ORDER BY total DESC");

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (estatusFiltro != null && !estatusFiltro.isEmpty()) {
                ps.setString(paramIndex++, estatusFiltro);
            }
            if (idAsuntoFiltro != null && idAsuntoFiltro > 0) {
                ps.setInt(paramIndex++, idAsuntoFiltro);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultados.put(rs.getString("nombre"), rs.getInt("total"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al contar turnos por municipio con filtros: " + e.getMessage());
        }
        return resultados;
    }

    public Map<String, Integer> contarTurnosPorAsunto(String estatusFiltro, Integer idMunicipioFiltro) {
        Map<String, Integer> resultados = new LinkedHashMap<>();
        StringBuilder sql = new StringBuilder("SELECT a.descripcion, COUNT(t.id_turno) AS total ")
                .append("FROM turnos t ")
                .append("JOIN asuntos a ON t.id_asunto_fk = a.id_asunto ")
                .append("JOIN municipios m ON t.id_municipio_fk = m.id_municipio ") 
                .append("WHERE 1=1 ");

        if (estatusFiltro != null && !estatusFiltro.isEmpty()) {
            sql.append("AND t.estatus = ? ");
        }
        if (idMunicipioFiltro != null && idMunicipioFiltro > 0) {
            sql.append("AND t.id_municipio_fk = ? ");
        }

        sql.append("GROUP BY a.descripcion ")
           .append("ORDER BY total DESC");

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (estatusFiltro != null && !estatusFiltro.isEmpty()) {
                ps.setString(paramIndex++, estatusFiltro);
            }
            if (idMunicipioFiltro != null && idMunicipioFiltro > 0) {
                ps.setInt(paramIndex++, idMunicipioFiltro);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultados.put(rs.getString("descripcion"), rs.getInt("total"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al contar turnos por asunto con filtros: " + e.getMessage());
        }
        return resultados;
    }

    public Map<String, Integer> contarTurnosPorEstado(Integer idMunicipioFiltro, Integer idAsuntoFiltro) {
        Map<String, Integer> resultados = new LinkedHashMap<>();

        resultados.put("Pendiente", 0);
        resultados.put("Resuelto", 0);
        resultados.put("Cancelado", 0);

        StringBuilder sql = new StringBuilder("SELECT t.estatus, COUNT(t.id_turno) AS total ")
                .append("FROM turnos t ")
                .append("JOIN municipios m ON t.id_municipio_fk = m.id_municipio ")
                .append("JOIN asuntos a ON t.id_asunto_fk = a.id_asunto ")
                .append("WHERE 1=1 ");

        if (idMunicipioFiltro != null && idMunicipioFiltro > 0) {
            sql.append("AND t.id_municipio_fk = ? ");
        }
        if (idAsuntoFiltro != null && idAsuntoFiltro > 0) {
            sql.append("AND t.id_asunto_fk = ? ");
        }

        sql.append("GROUP BY t.estatus ")
           .append("ORDER BY t.estatus");

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (idMunicipioFiltro != null && idMunicipioFiltro > 0) {
                ps.setInt(paramIndex++, idMunicipioFiltro);
            }
            if (idAsuntoFiltro != null && idAsuntoFiltro > 0) {
                ps.setInt(paramIndex++, idAsuntoFiltro);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultados.put(rs.getString("estatus"), rs.getInt("total"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al contar turnos por estado con filtros: " + e.getMessage());
        }
        return resultados;
    }
}