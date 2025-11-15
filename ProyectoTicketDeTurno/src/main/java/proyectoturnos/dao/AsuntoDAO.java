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

import proyectoturnos.model.Asunto;
import proyectoturnos.util.DatabaseConnection;
/**
 *
 * @author seb4s
 */
public class AsuntoDAO {
    
    //Metodo para obtener todos los municipios
    public List<Asunto> obtenerTodos(){
        List<Asunto> asuntos = new ArrayList<>();
        String sql = "SELECT * FROM asuntos ORDER BY id_asunto ASC";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                //Creamos un objeto por cada fila
                int id = rs.getInt("id_asunto");
                String nombre = rs.getString("descripcion");
                Asunto asunto = new Asunto(id, nombre);
                asuntos.add(asunto);
            }
            
        } catch(SQLException e) {
            System.err.println("Error al obtener los asuntos: " + e.getMessage());
        }
        
        return asuntos;
    }
    
    public void agregar(Asunto asunto) throws SQLException {
        String sql = "INSERT INTO asuntos (descripcion) VALUES (?)";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, asunto.getDescripcion());
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    asunto.setId_asunto(generatedKeys.getInt(1));
                }
            }
        }
    }

    public boolean actualizar(Asunto asunto) {
        String sql = "UPDATE asuntos SET descripcion = ? WHERE id_asunto = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, asunto.getDescripcion());
            ps.setInt(2, asunto.getId_asunto());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar el asunto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM asuntos WHERE id_asunto = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar el asunto: " + e.getMessage());
            return false;
        }
    }
}
