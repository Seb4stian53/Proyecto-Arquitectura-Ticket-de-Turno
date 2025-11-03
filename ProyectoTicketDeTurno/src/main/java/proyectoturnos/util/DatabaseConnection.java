/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoturnos.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author seb4s
 */
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sistema_turnos_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    //Implementación del Singleton
    
    private static DatabaseConnection instance;
        
    private DatabaseConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver de MYSQL :O, " + e);
        }
    }
    
    //Metodo estatico para obtener la unica instancia
    public static synchronized DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    //Metodo para obtener la conexion
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
}
