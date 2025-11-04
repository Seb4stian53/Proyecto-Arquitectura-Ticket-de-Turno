package proyectoturnos.controllers.Admin;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.AdminDAO;
import proyectoturnos.model.Admin;

@WebServlet("/panel/admins/create")
public class CrearAdminServlet extends HttpServlet {
    private AdminDAO adminDAO;

    @Override
    public void init() {
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Obtiene los datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2. Crea el objeto Admin
        Admin nuevoAdmin = new Admin();
        nuevoAdmin.setUsuario(username);
        nuevoAdmin.setContrasena(password); // El DAO se encargará de encriptarla

        // 3. Pide al DAO que lo guarde en la base de datos
        try {
            adminDAO.registrarAdmin(nuevoAdmin);
            // 4. Redirige a la lista con un mensaje de éxito
            response.sendRedirect(request.getContextPath() + "/panel/admins?mensaje=exito");
        } catch (SQLException e) {
            e.printStackTrace();
            // Si hay un error (ej. usuario duplicado), redirige con un mensaje de error
            response.sendRedirect(request.getContextPath() + "/panel/admins?mensaje=error");
        }
    }
}