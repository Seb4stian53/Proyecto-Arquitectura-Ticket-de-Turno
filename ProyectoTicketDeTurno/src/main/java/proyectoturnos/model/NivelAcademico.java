package proyectoturnos.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author seb4s
 */
public class NivelAcademico {
    private int id_nivel;
    private String nombre;
    
    public NivelAcademico(){}

    public NivelAcademico(int id_nivel, String nombre) {
        this.id_nivel = id_nivel;
        this.nombre = nombre;
    }

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
