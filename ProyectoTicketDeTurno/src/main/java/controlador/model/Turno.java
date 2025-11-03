/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.model;
import java.time.LocalDateTime;
/**
 *
 * @author seb4s
 */
public class Turno {
    private int id_turno;
    private String curp_alumno;
    private String nombre_alumno;
    private String paterno_alumno;
    private String materno_alumno;
    private String nombre_solicitante;
    private String telefono;
    private String correo;
    private String nivel_educativo;
    private String asunto;
    private String estatus;
    private int numero_turno_municipio;
    private LocalDateTime fecha_creacion;
    private int id_municipio_fk;
    
    public Turno(){}

    public Turno(int id_turno, String curp_alumno, String nombre_alumno, String paterno_alumno, String materno_alumno, String nombre_solicitante, String telefono, String correo, String nivel_educativo, String asunto, String estatus, int numero_turno_municipio, LocalDateTime fecha_creacion, int id_municipio_fk) {
        this.id_turno = id_turno;
        this.curp_alumno = curp_alumno;
        this.nombre_alumno = nombre_alumno;
        this.paterno_alumno = paterno_alumno;
        this.materno_alumno = materno_alumno;
        this.nombre_solicitante = nombre_solicitante;
        this.telefono = telefono;
        this.correo = correo;
        this.nivel_educativo = nivel_educativo;
        this.asunto = asunto;
        this.estatus = estatus;
        this.numero_turno_municipio = numero_turno_municipio;
        this.fecha_creacion = fecha_creacion;
        this.id_municipio_fk = id_municipio_fk;
    }

    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public String getCurp_alumno() {
        return curp_alumno;
    }

    public void setCurp_alumno(String curp_alumno) {
        this.curp_alumno = curp_alumno;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getPaterno_alumno() {
        return paterno_alumno;
    }

    public void setPaterno_alumno(String paterno_alumno) {
        this.paterno_alumno = paterno_alumno;
    }

    public String getMaterno_alumno() {
        return materno_alumno;
    }

    public void setMaterno_alumno(String materno_alumno) {
        this.materno_alumno = materno_alumno;
    }

    public String getNombre_solicitante() {
        return nombre_solicitante;
    }

    public void setNombre_solicitante(String nombre_solicitante) {
        this.nombre_solicitante = nombre_solicitante;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNivel_educativo() {
        return nivel_educativo;
    }

    public void setNivel_educativo(String nivel_educativo) {
        this.nivel_educativo = nivel_educativo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getNumero_turno_municipio() {
        return numero_turno_municipio;
    }

    public void setNumero_turno_municipio(int numero_turno_municipio) {
        this.numero_turno_municipio = numero_turno_municipio;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getId_municipio_fk() {
        return id_municipio_fk;
    }

    public void setId_municipio_fk(int id_municipio_fk) {
        this.id_municipio_fk = id_municipio_fk;
    }
    
    
}
