package proyectoturnos.controllers.Asunto;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.AsuntoDAO;
import proyectoturnos.model.Asunto;

@WebServlet("/panel/asunto/actualizar")
public class ActualizarAsuntoServlet extends HttpServlet {
    private AsuntoDAO asuntoDAO;

    @Override
    public void init() {
        asuntoDAO = new AsuntoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id_asunto"));
        String descripcion = request.getParameter("descripcion");
        
        Asunto asuntoActualizado = new Asunto(id, descripcion);
        asuntoDAO.actualizar(asuntoActualizado);

        response.sendRedirect(request.getContextPath() + "/panel/asuntos?mensaje=exito_editar");
    }
}