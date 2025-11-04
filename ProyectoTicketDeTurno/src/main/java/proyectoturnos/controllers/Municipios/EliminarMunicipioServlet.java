package proyectoturnos.controllers.Municipios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.MunicipioDAO;
import java.io.IOException;

@WebServlet("/panel/municipios/delete")
public class EliminarMunicipioServlet extends HttpServlet {
    private MunicipioDAO municipioDAO;

    @Override
    public void init() {
        municipioDAO = new MunicipioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        municipioDAO.eliminar(id);
        
        response.sendRedirect(request.getContextPath() + "/panel/municipios?mensaje=exito_eliminar");
    }
}