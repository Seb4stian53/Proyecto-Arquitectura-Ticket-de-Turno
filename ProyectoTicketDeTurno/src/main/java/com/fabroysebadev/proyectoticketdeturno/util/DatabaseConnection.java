/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fabroysebadev.proyectoticketdeturno.util;
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
    
    private Connection connection;
        
    private DatabaseConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("conexion exitosa :D");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar a la base :c -  " + e.getMessage());
            throw new RuntimeException("No se pudo conectar a la base de datos :O, " + e);
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
    public Connection getConnection(){
        return connection;
    }
    
    //Metodo para cerrar la conexion
    public void closeConnection(){
        try {
            if(connection != null && !connection.isClosed()){
                connection.close();
                System.out.println("Conexion a la base cerrada :D");
            }
        } catch(SQLException e) {
            System.err.println("Error al cerrar la conexion :c - " + e.getMessage());
        }
    }
}
