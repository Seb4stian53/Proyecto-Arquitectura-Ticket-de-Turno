/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoturnos.model;

/**
 *
 * @author seb4s
 */
public class Municipio {
    private int id_municipio;
    private String nombre;
    
    public Municipio(){}
    
    public Municipio(int id_municipio, String nombre){
        this.id_municipio = id_municipio;
        this.nombre = nombre;
    }

    public int getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
