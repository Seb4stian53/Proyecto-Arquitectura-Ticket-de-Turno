/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fabroysebadev.proyectoticketdeturno.service;
import com.fabroysebadev.proyectoticketdeturno.dao.TurnoDAO;
import com.fabroysebadev.proyectoticketdeturno.model.Turno;
import com.fabroysebadev.proyectoticketdeturno.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author seb4s
 */
public class TurnoService {
    //Ocupamos un TurnoDAO para acceder a la base de datos
    public TurnoDAO turnoDAO;
    
    public TurnoService(){
        this.turnoDAO = new TurnoDAO();
    }
    
    public Turno registrarNuevoTurno(Turno turno) throws Exception {
        Connection conn = null;
        try {
            //Obtenemos la conexuin que vamos a usar
            conn = DatabaseConnection.getInstance().getConnection();
            
            //Empezamos transaccion
            conn.setAutoCommit(false);
            
            //Verificamos CURP
            if (!validarFormatoCurp(turno.getCurp_alumno())) {
                throw new Exception("El formato de la CURP no es el correcto, favor de verificar");
            }
            
            //Usamos los DAOs
            //Obtenemos el numerio del ultimo turno del municipio
            int ultimoTurno = turnoDAO.obtenerUltimoTurnoPorMunicipio(turno.getId_municipio_fk(), conn);
            int numeroNuevoTurno = ultimoTurno + 1;
            turno.setNumero_turno_municipio(numeroNuevoTurno);
            
            //Guardamos el nuevo turno
            turnoDAO.guardar(turno, conn);
            conn.commit();
            
            return turno;
        } catch (Exception e) {
            if (conn != null) {
                try {
                    //Revertimos la transaccion
                    conn.rollback();
                    System.out.println("Transaccion revertida debido a un error");
                } catch (SQLException ex) {
                    System.err.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
            throw new Exception("No se pudo registrar el turno: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexion: " + e.getMessage());
                }
            }
        }
    }
    
    public boolean validarFormatoCurp(String curp) {
        if (curp == null) {
            return false;
        }
        return curp.matches("^[A-Z]{4}[0-9]{6}[HM][A-Z]{5}[A-Z0-9]{2}$");
    }
}
