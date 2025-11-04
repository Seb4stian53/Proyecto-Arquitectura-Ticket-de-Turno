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

import org.mindrot.jbcrypt.BCrypt;

import proyectoturnos.model.Admin;
import proyectoturnos.util.DatabaseConnection;

/**
 *
 * @author seb4s
 */
public class AdminDAO {
    
    
    public Admin validarCredenciales(String usuario, String contrasenaPlana){
        Admin admin = null;
        String sql = "SELECT * FROM administradores WHERE usuario = ?";
        Connection conn = null;
            try {
                conn = DatabaseConnection.getInstance().getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    String hashGuardado = rs.getString("contrasena");
                    if(BCrypt.checkpw(contrasenaPlana, hashGuardado)) {
                    //if (contrasenaPlana.equals(hashGuardado)) {
                        int id = rs.getInt("id_admin");
                        String usuarioDB = rs.getString("usuario");
                        admin = new Admin(id, usuarioDB, hashGuardado);
                        System.out.println("Login exitoso usuario: " + admin.getUsuario());
                    }
                }
            }
        }
        
        } catch(SQLException e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
        }
        return admin;
    }
    
    public void registrarAdmin(Admin admin) throws SQLException {
        // Encriptamos la contraseña con BCrypt
        String hashContrasena = BCrypt.hashpw(admin.getContrasena(), BCrypt.gensalt());
        
        String sql = "INSERT INTO administradores (usuario, contrasena) VALUES (?, ?)";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, admin.getUsuario());
            ps.setString(2, hashContrasena); // Guardamos el hash, no la contraseña original
            ps.executeUpdate();
            
            try(ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    admin.setID_Admin(generatedKeys.getInt(1));
                }
            }
        } catch(SQLException e) {
            System.err.println("Error al registrar administrador: " + e.getMessage());
            throw e;
        }
    }

    public List<Admin> listarTodos() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT id_admin, usuario FROM administradores ORDER BY usuario ASC";
        
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setID_Admin(rs.getInt("id_admin"));
                admin.setUsuario(rs.getString("usuario"));
                // No cargamos la contraseña por seguridad
                admins.add(admin);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar administradores: " + e.getMessage());
        }
        return admins;
    }

    // --- NUEVO MÉTODO PARA ELIMINAR UN ADMIN POR SU ID ---  @Arkesnouyoutube__xX1230
    public boolean eliminarPorId(int id) {
        String sql = "DELETE FROM administradores WHERE id_admin = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Devuelve true si se borró algo
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar administrador: " + e.getMessage());
            return false;
        }
    }
}