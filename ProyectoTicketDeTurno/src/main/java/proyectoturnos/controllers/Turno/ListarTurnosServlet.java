package proyectoturnos.controllers.Turno;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.TurnoDAO;
import proyectoturnos.model.Turno;

@WebServlet("/panel/turnos")
public class ListarTurnosServlet extends HttpServlet {
    private TurnoDAO turnoDAO;

    @Override
    public void init() {
        turnoDAO = new TurnoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Turno> listaTurnos = turnoDAO.obtenerTodos();
        
        request.setAttribute("listaTurnos", listaTurnos);
        
        // Lo mandamos al JSP correspondiente para que muestre la tabla
        request.getRequestDispatcher("/admin/gestionar_turnos.jsp").forward(request, response);
    }
}