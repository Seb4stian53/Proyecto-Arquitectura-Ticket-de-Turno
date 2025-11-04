package proyectoturnos.controllers.Municipios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.MunicipioDAO;
import proyectoturnos.model.Municipio;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/panel/municipios/create")
public class CrearMunicipioServlet extends HttpServlet {
    private MunicipioDAO municipioDAO;

    @Override
    public void init() {
        municipioDAO = new MunicipioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        Municipio nuevoMunicipio = new Municipio();
        nuevoMunicipio.setNombre(nombre);
        
        try {
            municipioDAO.agregar(nuevoMunicipio);
            response.sendRedirect(request.getContextPath() + "/panel/municipios?mensaje=exito_crear");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/panel/municipios?mensaje=error");
        }
    }
}