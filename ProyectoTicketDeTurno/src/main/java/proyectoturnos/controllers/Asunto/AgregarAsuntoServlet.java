package proyectoturnos.controllers.Asunto;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.AsuntoDAO;
import proyectoturnos.model.Asunto;

@WebServlet("/panel/asunto/agregar")
public class AgregarAsuntoServlet extends HttpServlet {
    private AsuntoDAO asuntoDAO;

    @Override
    public void init() {
        asuntoDAO = new AsuntoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String descripcion = request.getParameter("descripcion");
        Asunto nuevoAsunto = new Asunto();
        nuevoAsunto.setDescripcion(descripcion);

        try {
            asuntoDAO.agregar(nuevoAsunto);
        } catch (SQLException e) {

           e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/panel/asuntos?mensaje=error");
        }

        response.sendRedirect(request.getContextPath() + "/panel/asuntos?mensaje=exito_crear");
    }
}