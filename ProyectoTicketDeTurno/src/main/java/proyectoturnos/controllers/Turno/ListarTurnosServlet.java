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

        String busqueda = request.getParameter("busqueda"); // 1. Lee el parámetro de búsqueda

        List<Turno> listaTurnos;

        if (busqueda != null && !busqueda.trim().isEmpty()) { // 2. Filtra si hay un término de búsqueda
            listaTurnos = turnoDAO.buscarPorTermino(busqueda); // Asumiendo que existe este método en TurnoDAO
        } else {
            listaTurnos = turnoDAO.obtenerTodos(); // Muestra todos los turnos si no hay búsqueda
        }

        request.setAttribute("listaTurnos", listaTurnos); // 3. Guarda los resultados en el request
        request.setAttribute("busqueda", busqueda); // 4. Guarda el término de búsqueda para mantenerlo en el campo

        request.getRequestDispatcher("/admin/gestionar_turnos.jsp").forward(request, response);
    }
}