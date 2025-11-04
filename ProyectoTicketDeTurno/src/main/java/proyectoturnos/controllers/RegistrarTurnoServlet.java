/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoturnos.controllers;
import proyectoturnos.model.Turno;
import proyectoturnos.model.Municipio;
import proyectoturnos.dao.TurnoDAO;
import proyectoturnos.dao.MunicipioDAO;
import proyectoturnos.service.TurnoService;
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
        String nivelEducativo = request.getParameter("nivel_educativo");
        String asunto = request.getParameter("asunto");
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
        nuevoTurno.setNivel_educativo(nivelEducativo);
        nuevoTurno.setAsunto(asunto);
        
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
            System.out.println("Errorsin en al registrar turno de paerte del servlet");
            e.printStackTrace(); 
            
            request.setAttribute("error", e.getMessage());
            MunicipioDAO municipioDAO = new MunicipioDAO();
            List<Municipio> listaMunicipios = municipioDAO.obtenerTodos();
            request.setAttribute("listaMunicipios", listaMunicipios);
            request.getRequestDispatcher("registrar_turno.jsp").forward(request, response);
        }
    }
}
