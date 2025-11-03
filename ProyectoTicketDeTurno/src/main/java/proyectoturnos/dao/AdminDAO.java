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
                    //if(BCrypt.checkpw(contrasenaPlana, hashGuardado)) {
                    if (contrasenaPlana.equals(hashGuardado)) {
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
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("por mi parte esta bien el login");
        return admin;
    }
    
    public void registrarAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO administradores (usuario, contrasena) VALUES (?, ?)";
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, admin.getUsuario());
            ps.setString(2, admin.getContrasena());
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
}
