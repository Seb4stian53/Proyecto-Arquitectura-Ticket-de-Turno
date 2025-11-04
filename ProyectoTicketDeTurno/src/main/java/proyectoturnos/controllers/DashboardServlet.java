package proyectoturnos.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 
import proyectoturnos.dao.TurnoDAO;

@WebServlet("/panel/dashboard")
public class DashboardServlet extends HttpServlet {
    private TurnoDAO turnoDAO;

    @Override
    public void init() {
        turnoDAO = new TurnoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // --- 1. PIDE LOS DATOS AL DAO ---
        int totalTurnos = turnoDAO.contarTotal();
        int pendientes = turnoDAO.contarPorEstado("Pendiente");
        int resueltos = turnoDAO.contarPorEstado("Resuelto");
        int cancelados = turnoDAO.contarPorEstado("Cancelado");

        // --- 2. GUARDA LOS DATOS EN EL REQUEST ---
        request.setAttribute("totalTurnos", totalTurnos);
        request.setAttribute("pendientes", pendientes);
        request.setAttribute("resueltos", resueltos);
        request.setAttribute("cancelados", cancelados);

        // --- 3. MANDA AL JSP PARA QUE LOS MUESTRE ---
        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }
}