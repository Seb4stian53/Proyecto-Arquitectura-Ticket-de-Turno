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

import proyectoturnos.model.Municipio;
import proyectoturnos.util.DatabaseConnection;

/**
 *
 * @author seb4s
 */
public class MunicipioDAO {
    
    //Metodo para obtener todos los municipios
    public List<Municipio> obtenerTodos(){
        List<Municipio> municipios = new ArrayList<>();
        String sql = "SELECT * FROM municipios ORDER BY nombre ASC";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                //Creamos un objeto por cada fila
                int id = rs.getInt("id_municipio");
                String nombre = rs.getString("nombre");
                Municipio municipio = new Municipio(id, nombre);
                municipios.add(municipio);
            }
            
        } catch(SQLException e) {
            System.err.println("Error al obtener los municipios: " + e.getMessage());
        }
        
        return municipios;
    }
    
    public void agregar(Municipio municipio) throws SQLException {
        String sql = "INSERT INTO municipios (nombre) VALUES (?)";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, municipio.getNombre());
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    municipio.setId_municipio(generatedKeys.getInt(1));
                }
            }
        }
    }

    public boolean actualizar(Municipio municipio) {
        String sql = "UPDATE municipios SET nombre = ? WHERE id_municipio = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, municipio.getNombre());
            ps.setInt(2, municipio.getId_municipio());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar el municipio: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM municipios WHERE id_municipio = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar el municipio: " + e.getMessage());
            return false;
        }
    }
}

