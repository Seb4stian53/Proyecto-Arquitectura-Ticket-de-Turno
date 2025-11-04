package proyectoturnos.controllers.Turno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.TurnoDAO;
import proyectoturnos.model.Turno;
import java.io.IOException;

@WebServlet("/panel/turno/ver")
public class VerTurnoServlet extends HttpServlet {
    private TurnoDAO turnoDAO;

    @Override
    public void init() {
        turnoDAO = new TurnoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Turno turno = turnoDAO.buscarPorId(id); // Necesitaremos este método en el DAO
        
        request.setAttribute("turno", turno);
        request.getRequestDispatcher("/admin/detalle_turno_admin.jsp").forward(request, response);
    }
}