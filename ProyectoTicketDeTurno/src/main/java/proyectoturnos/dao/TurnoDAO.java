/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoturnos.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import proyectoturnos.model.Turno;
import proyectoturnos.model.Municipio;
import proyectoturnos.util.DatabaseConnection;

/**
 *
 * @author seb4s
 */
public class TurnoDAO {
    
    //Metodos Transaccionales (se ocupan varios para hacer una cosa)
    
    //Crear turno
    public void guardar(Turno turno, Connection conn) throws SQLException {
        String sql = "INSERT INTO turnos (curp_alumno, nombre_alumno, paterno_alumno, materno_alumno, nombre_solicitante, telefono, correo, nivel_educativo, asunto, numero_turno_municipio, id_municipio_fk) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, turno.getCurp_alumno());
            ps.setString(2, turno.getNombre_alumno());
            ps.setString(3, turno.getPaterno_alumno());
            ps.setString(4, turno.getMaterno_alumno());
            ps.setString(5, turno.getNombre_solicitante());
            ps.setString(6, turno.getTelefono());
            ps.setString(7, turno.getCorreo());
            ps.setString(8, turno.getNivel_educativo());
            ps.setString(9, turno.getAsunto());
            ps.setInt(10, turno.getNumero_turno_municipio());
            ps.setInt(11, turno.getMunicipio().getId_municipio());
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    turno.setId_turno(generatedKeys.getInt(1));
                }
            }
        }
    }
    
    //Obtiene el ultimo numero de turno por municipio
    public int obtenerUltimoTurnoPorMunicipio(int id_municipio, Connection conn) throws SQLException {
        String sql = "SELECT MAX(numero_turno_municipio) FROM turnos WHERE id_municipio_fk = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_municipio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
    
    //Metodos no transaccionales (estos hacen todo por si solos)
    
    //Obtener todos los turnos
    public List<Turno> obtenerTodos() {
        List<Turno> turnos = new ArrayList<>();
        String sql = "SELECT t.*, m.nombre AS nombre_municipio FROM turnos t LEFT JOIN municipios m ON t.id_munnicipio_fk = m.id_municipio ORDER BY fecha_creacion DESC";
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while(rs.next()){
                Turno turno = new Turno();
                turno.setId_turno(rs.getInt("id_turno"));
                turno.setCurp_alumno(rs.getString("curp_alumno"));
                turno.setNombre_alumno(rs.getString("nombre_alumno"));
                turno.setPaterno_alumno(rs.getString("paterno_alumno"));
                turno.setMaterno_alumno(rs.getString("materno_alumno"));
                turno.setNombre_solicitante(rs.getString("nombre_solicitante"));
                turno.setTelefono(rs.getString("telefono"));
                turno.setCorreo(rs.getString("correo"));
                turno.setNivel_educativo(rs.getString("nivel_educativo"));
                turno.setAsunto(rs.getString("asunto"));
                turno.setEstatus(rs.getString("estatus"));
                turno.setNumero_turno_municipio(rs.getInt("numero_turno_municipio"));
                Municipio municipio = new Municipio();
                municipio.setId_municipio(rs.getInt("id_municipio_fk"));
                municipio.setNombre(rs.getString("nombre_municipio"));
                turno.setMunicipio(municipio);
                
                Timestamp timestamp = rs.getTimestamp("fecha_creacion");
                if (timestamp != null) {
                    turno.setFecha_creacion(timestamp.toLocalDateTime());
                }
                
                turnos.add(turno);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener turnos: " + e.getMessage());
        }
        return turnos;
    }
    
    //Cambiar estado a pendiente o resuelto
    public void actualizarEstado(int id_turno, String nuevo_estado) {
        String sql = "UPDATE turnos SET estatus = ? WHERE id_turno = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevo_estado);
            ps.setInt(2, id_turno);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar estatus: " + e.getMessage());
        }
    }
    
    //Buscar un turno por CURP y id_turno
    public Turno buscarPorCurpYTurno(String curp, int numero_turno) {
        Turno turno = null;
        String sql = "SELECT t.*, m.nombre AS nombre_municipio FROM turnos t LEFT JOIN municipios m ON t.id_municipio_fk = m.id_municipio WHERE curp_alumno = ? AND numero_turno_municipio = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, curp);
            ps.setInt(2, numero_turno);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    turno = new Turno();
                    turno.setId_turno(rs.getInt("id_turno"));
                    turno.setCurp_alumno(rs.getString("curp_alumno"));
                    turno.setNombre_alumno(rs.getString("nombre_alumno"));
                    turno.setPaterno_alumno(rs.getString("paterno_alumno"));
                    turno.setMaterno_alumno(rs.getString("materno_alumno"));
                    turno.setNombre_solicitante(rs.getString("nombre_solicitante"));
                    turno.setTelefono(rs.getString("telefono"));
                    turno.setCorreo(rs.getString("correo"));
                    turno.setNivel_educativo(rs.getString("nivel_educativo"));
                    turno.setAsunto(rs.getString("asunto"));
                    turno.setEstatus(rs.getString("estatus"));
                    turno.setNumero_turno_municipio(rs.getInt("numero_turno_municipio"));
                    Municipio municipio = new Municipio();
                    municipio.setId_municipio(rs.getInt("id_municipio_fk"));
                    municipio.setNombre(rs.getString("nombre_municipio"));
                    turno.setMunicipio(municipio);
                    
                    Timestamp timestamp = rs.getTimestamp("fecha_creacion");
                    if (timestamp != null) {
                        turno.setFecha_creacion(timestamp.toLocalDateTime());
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar turno: " + e.getMessage());
        }
        return turno;
    }
    
    //Actualizar turno por parte del usuario (cambiar datos de comunicacion o asunto)
    public void actualizarTurno(Turno turno) {
        String sql = "UPDATE turnos SET nombre_alumno = ?, paterno_alumno = ?, materno_alumno = ?, nombre_solicitante = ?, telefono = ?, correo = ?, nivel_educativo = ?, asunto = ? WHERE id_turno = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            //Datos a actualizar
            ps.setString(1, turno.getNombre_alumno());
            ps.setString(2, turno.getPaterno_alumno());
            ps.setString(3, turno.getMaterno_alumno());
            ps.setString(4, turno.getNombre_solicitante());
            ps.setString(5, turno.getTelefono());
            ps.setString(6, turno.getCorreo());
            ps.setString(7, turno.getNivel_educativo());
            ps.setString(8, turno.getAsunto());
            //ID del turno
            ps.setInt(9, turno.getId_turno());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar el turno: " + e.getMessage());
        }
    }
    
    //Cancelar el turno
    public boolean cancelarTurno(int id_turno) {
        String sql = "UPDATE turnos SET estatus = 'Cancelado' WHERE id_turno = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setInt(1, id_turno);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al cancelar el turno: " + e.getMessage());
            return false;
        }
    }
}
