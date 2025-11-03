/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.model;

/**
 *
 * @author seb4s
 */
public class Admin {
    private int id_admin;
    private String usuario;
    private String contrasena;
    
    public Admin(){}
    
    public Admin(int id_admin, String usuario, String contrasena){
        this.id_admin = id_admin;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    
    public int getID_Admin(){
        return id_admin;
    }
    
    public void setID_Admin(int id_admin){
        this.id_admin = id_admin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
}
