/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoturnos.controllers.Turno;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.MunicipioDAO;
import proyectoturnos.dao.AsuntoDAO;
import proyectoturnos.dao.NivelAcademicoDAO;
import proyectoturnos.dao.TurnoDAO;
import proyectoturnos.model.Municipio;
import proyectoturnos.model.Asunto;
import proyectoturnos.model.NivelAcademico;
import proyectoturnos.model.Turno;
import proyectoturnos.service.TurnoService;

/**
 *
 * @author seb4s
 */
@WebServlet("/registrar-turno")
public class RegistrarTurnoServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String curp = request.getParameter("curp_alumno");
        String nombreAlumno = request.getParameter("nombre_alumno");
        String paternoAlumno = request.getParameter("paterno_alumno");
        String maternoAlumno = request.getParameter("materno_alumno");
        String nombreSolicitante = request.getParameter("nombre_solicitante");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        int idNivelEducativo = Integer.parseInt(request.getParameter("id_nivel_fk"));
        int idAsunto = Integer.parseInt(request.getParameter("id_asunto_fk"));
        // El ID del municipio viene como String, necesitamos convertirlo a entero.
        int idMunicipio = Integer.parseInt(request.getParameter("id_municipio_fk"));
        
        Turno nuevoTurno = new Turno();
        nuevoTurno.setCurp_alumno(curp);
        nuevoTurno.setNombre_alumno(nombreAlumno);
        nuevoTurno.setPaterno_alumno(paternoAlumno);
        nuevoTurno.setMaterno_alumno(maternoAlumno);
        nuevoTurno.setNombre_solicitante(nombreSolicitante);
        nuevoTurno.setTelefono(telefono);
        nuevoTurno.setCorreo(correo);
        
        NivelAcademico nivelTemp = new NivelAcademico();
        nivelTemp.setId_nivel(idNivelEducativo);
        nuevoTurno.setNivelAcademico(nivelTemp);
        
        Asunto asuntoTemp = new Asunto();
        asuntoTemp.setId_asunto(idAsunto);
        nuevoTurno.setAsunto(asuntoTemp);
        
        Municipio municipioTemp = new Municipio();
        municipioTemp.setId_municipio(idMunicipio);
        nuevoTurno.setMunicipio(municipioTemp);
        
        TurnoService turnoService = new TurnoService();
        
        try {
            Turno turnoRegistrado = turnoService.registrarNuevoTurno(nuevoTurno);
            
            TurnoDAO turnoDAO = new TurnoDAO(); // Puedes instanciarlo aquí
            Turno turnoCompleto = turnoDAO.buscarPorCurpYTurno(
                    turnoRegistrado.getCurp_alumno(),
                    turnoRegistrado.getNumero_turno_municipio()
            );
            
            request.setAttribute("turno", turnoCompleto);
    
            request.getRequestDispatcher("comprobante.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Error al registrar turno de parte del servlet");
            e.printStackTrace(); 
            
            request.setAttribute("error", e.getMessage());
            recargarCatalogos(request);
            request.setAttribute("turnoFallido", nuevoTurno);
            request.getRequestDispatcher("registrar_turno.jsp").forward(request, response);
        }
           
    }
    
    private void recargarCatalogos(HttpServletRequest request) {
        MunicipioDAO municipioDAO = new MunicipioDAO();
        AsuntoDAO asuntoDAO = new AsuntoDAO();
        NivelAcademicoDAO nivelDAO = new NivelAcademicoDAO();
        
        request.setAttribute("listaMunicipios", municipioDAO.obtenerTodos());
        request.setAttribute("listaAsuntos", asuntoDAO.obtenerTodos());
        request.setAttribute("listaNiveles", nivelDAO.obtenerTodos());
    }
}
