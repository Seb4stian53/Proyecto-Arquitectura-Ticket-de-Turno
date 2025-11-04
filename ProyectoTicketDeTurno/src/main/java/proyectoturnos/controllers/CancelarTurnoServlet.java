/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoturnos.controllers;
import proyectoturnos.dao.TurnoDAO;
import proyectoturnos.model.Turno;
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
@WebServlet("/cancelar-turno")
public class CancelarTurnoServlet extends HttpServlet {
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idTurno = Integer.parseInt(request.getParameter("id_turno"));
        TurnoDAO turnoDAO = new TurnoDAO();
        boolean exito = turnoDAO.cancelarTurno(idTurno);
        if (exito) {
            response.sendRedirect(request.getContextPath() + "/cancelacion_exitosa.jsp");
            
        } else {

            request.setAttribute("error", "No se pudo cancelar el turno. Por favor, intente de nuevo más tarde.");
            Turno turnoOriginal = turnoDAO.buscarPorId(idTurno);
            request.setAttribute("turno", turnoOriginal);

            request.getRequestDispatcher("editar_turno.jsp").forward(request, response);
        }
    }
}
