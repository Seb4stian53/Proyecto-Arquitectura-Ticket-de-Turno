/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoturnos.controllers;
import proyectoturnos.dao.TurnoDAO;
import proyectoturnos.model.Turno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *
 * @author seb4s
 */
@WebServlet("/consultar-turno")
public class ConsultarTurnoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // --- PASO 1: LEER DATOS DEL FORMULARIO ---
        String curp = request.getParameter("curp_alumno");
        String numeroTurnoStr = request.getParameter("numero_turno_municipio");
        
        // --- PASO 2: VALIDAR Y CONVERTIR ENTRADAS ---
        // Es una buena práctica verificar que los datos no estén vacíos.
        if (curp == null || curp.isEmpty() || numeroTurnoStr == null || numeroTurnoStr.isEmpty()) {
            request.setAttribute("error", "Por favor, ingrese tanto la CURP como el número de turno.");
            request.getRequestDispatcher("consultar_turno.jsp").forward(request, response);
            return;
        }

        int numeroTurno;
        try {
            numeroTurno = Integer.parseInt(numeroTurnoStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "El número de turno debe ser un valor numérico.");
            request.getRequestDispatcher("consultar_turno.jsp").forward(request, response);
            return;
        }

        TurnoDAO turnoDAO = new TurnoDAO();
        Turno turnoEncontrado = turnoDAO.buscarPorCurpYTurno(curp, numeroTurno);

        if (turnoEncontrado != null) {
            request.setAttribute("turno", turnoEncontrado);
            
            request.getRequestDispatcher("editar_turno.jsp").forward(request, response);
            
        } else {
            
            request.setAttribute("error", "No se encontró ningún turno con la CURP y el número proporcionados. Por favor, verifique sus datos.");
            
            request.getRequestDispatcher("consultar_turno.jsp").forward(request, response);
        }
    }
}
