package proyectoturnos.controllers.Turno;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.TurnoDAO;

@WebServlet("/panel/turno/actualizarEstado")
public class ActualizarEstadoTurnoServlet extends HttpServlet {
    private TurnoDAO turnoDAO;

    @Override
    public void init() {
        turnoDAO = new TurnoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id_turno"));
        String nuevoEstatus = request.getParameter("nuevo_estatus");
        
        turnoDAO.actualizarEstado(id, nuevoEstatus);
        
        response.sendRedirect(request.getContextPath() + "/panel/turno/ver?id=" + id + "&mensaje=exito_estado");
    }
}