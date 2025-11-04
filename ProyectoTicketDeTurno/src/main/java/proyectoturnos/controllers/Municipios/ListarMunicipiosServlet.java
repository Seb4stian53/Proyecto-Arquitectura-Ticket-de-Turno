package proyectoturnos.controllers.Municipios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.MunicipioDAO;
import proyectoturnos.model.Municipio;
import java.io.IOException;
import java.util.List;

@WebServlet("/panel/municipios")
public class ListarMunicipiosServlet extends HttpServlet {
    private MunicipioDAO municipioDAO;

    @Override
    public void init() {
        municipioDAO = new MunicipioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Municipio> listaMunicipios = municipioDAO.obtenerTodos();
        request.setAttribute("listaMunicipios", listaMunicipios);
        request.getRequestDispatcher("/admin/gestionar_municipios.jsp").forward(request, response);
    }
}