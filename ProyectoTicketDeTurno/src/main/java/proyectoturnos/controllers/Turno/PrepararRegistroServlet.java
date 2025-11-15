/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoturnos.controllers.Turno;
import proyectoturnos.dao.MunicipioDAO;
import proyectoturnos.dao.AsuntoDAO;
import proyectoturnos.dao.NivelAcademicoDAO;
import proyectoturnos.model.Municipio;
import proyectoturnos.model.Asunto;
import proyectoturnos.model.NivelAcademico;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author seb4s
 */
@WebServlet("/preparar-registro")
public class PrepararRegistroServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Preparando formulario de registro...");
        
        MunicipioDAO municipioDAO = new MunicipioDAO();
        List<Municipio> listaMunicipios = municipioDAO.obtenerTodos();
        AsuntoDAO asuntoDAO = new AsuntoDAO();
        List<Asunto> listaAsuntos = asuntoDAO.obtenerTodos();
        NivelAcademicoDAO nivelAcademicoDAO = new NivelAcademicoDAO();
        List<NivelAcademico> listaNiveles = nivelAcademicoDAO.obtenerTodos();
        
        request.setAttribute("listaMunicipios", listaMunicipios);
        request.setAttribute("listaAsuntos", listaAsuntos);
        request.setAttribute("listaNiveles", listaNiveles);
       
        request.getRequestDispatcher("registrar_turno.jsp").forward(request, response);
    }
}
