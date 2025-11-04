/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoturnos.controllers;
import proyectoturnos.model.Turno;
import proyectoturnos.dao.TurnoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *
 * @author seb4s
 */
@WebServlet("/actualizar-turno")
public class ActualizarTurnoServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id_turno"));
        String nombre_alumno = request.getParameter("nombre_alumno");
        String paterno_alumno = request.getParameter("paterno_alumno");
        String materno_alumno = request.getParameter("materno_alumno");
        String nombre_solicitante = request.getParameter("nombre_solicitante");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String nivel_educativo = request.getParameter("nivel_educativo");
        String asunto = request.getParameter("asunto");
        
        Turno turnoParaActualizar = new Turno();
        turnoParaActualizar.setId_turno(id);
        turnoParaActualizar.setNombre_alumno(nombre_alumno);
        turnoParaActualizar.setPaterno_alumno(paterno_alumno);
        turnoParaActualizar.setMaterno_alumno(materno_alumno);
        turnoParaActualizar.setNombre_solicitante(nombre_solicitante);
        turnoParaActualizar.setTelefono(telefono);
        turnoParaActualizar.setCorreo(correo);
        turnoParaActualizar.setNivel_educativo(nivel_educativo);
        turnoParaActualizar.setAsunto(asunto);
        
        TurnoDAO turnoDAO = new TurnoDAO();
        
        try {
            turnoDAO.actualizarTurno(turnoParaActualizar);
            request.setAttribute("success", "¡Turno actualizado exitosamente!");
            Turno turnoActualizado = turnoDAO.buscarPorId(id);
            request.setAttribute("turno", turnoActualizado);
            request.getRequestDispatcher("editar_turno.jsp").forward(request, response);

        } catch (Exception e) {
            
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al actualizar el turno: " + e.getMessage());
            Turno turnoOriginal = turnoDAO.buscarPorId(id);
            request.setAttribute("turno", turnoOriginal);
            request.getRequestDispatcher("editar_turno.jsp").forward(request, response);
        }
    }
}