package proyectoturnos.controllers.Admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.AdminDAO;

@WebServlet("/panel/admins/delete")
public class EliminarAdminServlet extends HttpServlet {
    private AdminDAO adminDAO;

    @Override
    public void init() {
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // 1. Obtiene el ID de la URL
            int idAdmin = Integer.parseInt(request.getParameter("id"));

            // 2. Pide al DAO que lo elimine
            adminDAO.eliminarPorId(idAdmin);

            // 3. Redirige a la lista con un mensaje de éxito
            response.sendRedirect(request.getContextPath() + "/panel/admins?mensaje=eliminado");
        } catch (NumberFormatException e) {
            // Por si el ID no es un número válido
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/panel/admins?mensaje=error_id");
        }
    }
}