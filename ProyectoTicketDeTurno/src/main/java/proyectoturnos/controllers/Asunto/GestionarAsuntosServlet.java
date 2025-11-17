package proyectoturnos.controllers.Asunto;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.AsuntoDAO;
import proyectoturnos.model.Asunto;

@WebServlet("/panel/asuntos")
public class GestionarAsuntosServlet extends HttpServlet {
    private AsuntoDAO asuntoDAO;

    @Override
    public void init() {
        asuntoDAO = new AsuntoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        List<Asunto> listaAsuntos = asuntoDAO.obtenerTodos();

        request.setAttribute("listaAsuntos", listaAsuntos);

        request.getRequestDispatcher("/admin/gestionar_asuntos.jsp").forward(request, response);
    }
}