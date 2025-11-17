/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoturnos.model;

/**
 *
 * @author seb4s
 */
public class Asunto {
    private int id_asunto;
    private String descripcion;
    
    public Asunto() {}

    public Asunto(int id_asunto, String descripcion) {
        this.id_asunto = id_asunto;
        this.descripcion = descripcion;
    }

    public int getId_asunto() {
        return id_asunto;
    }

    public void setId_asunto(int id_asunto) {
        this.id_asunto = id_asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
