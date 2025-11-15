/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoturnos.controllers.Turno;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import proyectoturnos.dao.AsuntoDAO;
import proyectoturnos.dao.MunicipioDAO;
import proyectoturnos.dao.NivelAcademicoDAO;
import proyectoturnos.dao.TurnoDAO;
import proyectoturnos.model.Asunto;
import proyectoturnos.model.Municipio;
import proyectoturnos.model.NivelAcademico;
import proyectoturnos.model.Turno;
/**
 *
 * @author seb4s
 */
@WebServlet("/actualizar-turno")
public class ActualizarTurnoServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idTurno = Integer.parseInt(request.getParameter("id_turno"));
        String nombreAlumno = request.getParameter("nombre_alumno");
        String paternoAlumno = request.getParameter("paterno_alumno");
        String maternoAlumno = request.getParameter("materno_alumno");
        String nombreSolicitante = request.getParameter("nombre_solicitante");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        int idNivel = Integer.parseInt(request.getParameter("id_nivel_fk"));
        int idAsunto = Integer.parseInt(request.getParameter("id_asunto_fk"));
        
        Turno turnoParaActualizar = new Turno();
        turnoParaActualizar.setId_turno(idTurno);
        turnoParaActualizar.setNombre_alumno(nombreAlumno);
        turnoParaActualizar.setPaterno_alumno(paternoAlumno);
        turnoParaActualizar.setMaterno_alumno(maternoAlumno);
        turnoParaActualizar.setNombre_solicitante(nombreSolicitante);
        turnoParaActualizar.setTelefono(telefono);
        turnoParaActualizar.setCorreo(correo);
        
        NivelAcademico nivelTemp = new NivelAcademico();
        nivelTemp.setId_nivel(idNivel);
        turnoParaActualizar.setNivelAcademico(nivelTemp);

        Asunto asuntoTemp = new Asunto();
        asuntoTemp.setId_asunto(idAsunto);
        turnoParaActualizar.setAsunto(asuntoTemp);
        
        TurnoDAO turnoDAO = new TurnoDAO();
        
        try {
            turnoDAO.actualizarTurno(turnoParaActualizar);
            request.setAttribute("success", "¡Turno actualizado exitosamente!");
            Turno turnoActualizado = turnoDAO.buscarPorId(idTurno);
            request.setAttribute("turno", turnoActualizado);
            recargarCatalogos(request);
            request.getRequestDispatcher("editar_turno.jsp").forward(request, response);

        } catch (Exception e) {
            
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al actualizar el turno: " + e.getMessage());
            Turno turnoOriginal = turnoDAO.buscarPorId(idTurno);
            request.setAttribute("turno", turnoOriginal);
            recargarCatalogos(request);
            request.getRequestDispatcher("editar_turno.jsp").forward(request, response);
        }
    }
    
    private void recargarCatalogos(HttpServletRequest request) {
        AsuntoDAO asuntoDAO = new AsuntoDAO();
        NivelAcademicoDAO nivelDAO = new NivelAcademicoDAO();
        
        request.setAttribute("listaAsuntos", asuntoDAO.obtenerTodos());
        request.setAttribute("listaNiveles", nivelDAO.obtenerTodos());
    }
}