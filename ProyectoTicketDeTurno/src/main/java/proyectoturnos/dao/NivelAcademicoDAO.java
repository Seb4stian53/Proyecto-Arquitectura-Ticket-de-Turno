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
import java.util.ArrayList;
import java.util.List;

import proyectoturnos.model.NivelAcademico;
import proyectoturnos.util.DatabaseConnection;
/**
 *
 * @author seb4s
 */
public class NivelAcademicoDAO {
    
    //Metodo para obtener todos los municipios
    public List<NivelAcademico> obtenerTodos(){
        List<NivelAcademico> niveles = new ArrayList<>();
        String sql = "SELECT * FROM niveles_academicos ORDER BY id_nivel ASC";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                //Creamos un objeto por cada fila
                int id = rs.getInt("id_nivel");
                String nombre = rs.getString("nombre");
                NivelAcademico nivelAcademico = new NivelAcademico(id, nombre);
                niveles.add(nivelAcademico);
            }
            
        } catch(SQLException e) {
            System.err.println("Error al obtener los niveles academicos: " + e.getMessage());
        }
        
        return niveles;
    }
    
    public void agregar(NivelAcademico nivelAcademico) throws SQLException {
        String sql = "INSERT INTO niveles_academicos (nombre) VALUES (?)";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nivelAcademico.getNombre());
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    nivelAcademico.setId_nivel(generatedKeys.getInt(1));
                }
            }
        }
    }

    public boolean actualizar(NivelAcademico nivelAcademico) {
        String sql = "UPDATE niveles_academicos SET nombre = ? WHERE id_nivel = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nivelAcademico.getNombre());
            ps.setInt(2, nivelAcademico.getId_nivel());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar el nivel educativo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM niveles_academicos WHERE id_nivel = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar el nivel academico: " + e.getMessage());
            return false;
        }
    }
}
