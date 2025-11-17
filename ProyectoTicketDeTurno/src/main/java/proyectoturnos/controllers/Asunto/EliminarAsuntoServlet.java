package proyectoturnos.controllers.Asunto;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.AsuntoDAO;

@WebServlet("/panel/asunto/eliminar")
public class EliminarAsuntoServlet extends HttpServlet {
    private AsuntoDAO asuntoDAO;

    @Override
    public void init() {
        asuntoDAO = new AsuntoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        asuntoDAO.eliminar(id);

        response.sendRedirect(request.getContextPath() + "/panel/asuntos?mensaje=exito_eliminar");
    }
    
}