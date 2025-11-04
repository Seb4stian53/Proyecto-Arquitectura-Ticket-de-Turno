package proyectoturnos.controllers.Municipios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.MunicipioDAO;
import proyectoturnos.model.Municipio;
import java.io.IOException;

@WebServlet("/panel/municipios/update")
public class ActualizarMunicipioServlet extends HttpServlet {
    private MunicipioDAO municipioDAO;

    @Override
    public void init() {
        municipioDAO = new MunicipioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id_municipio"));
        String nuevoNombre = request.getParameter("nombre");
        
        Municipio municipio = new Municipio(id, nuevoNombre);
        municipioDAO.actualizar(municipio);
        
        response.sendRedirect(request.getContextPath() + "/panel/municipios?mensaje=exito_editar");
    }
}