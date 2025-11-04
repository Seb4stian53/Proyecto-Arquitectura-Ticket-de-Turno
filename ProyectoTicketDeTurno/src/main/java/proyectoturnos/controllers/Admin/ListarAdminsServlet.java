package proyectoturnos.controllers.Admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoturnos.dao.AdminDAO;
import proyectoturnos.model.Admin;

@WebServlet("/panel/admins")
public class ListarAdminsServlet extends HttpServlet {
    private AdminDAO adminDAO;

    @Override
    public void init() {
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Llama al DAO para obtener la lista de admins
        List<Admin> listaAdmins = adminDAO.listarTodos();

        // 2. Pone la lista en el request para que el JSP la pueda usar
        request.setAttribute("listaAdmins", listaAdmins);

        // 3. Manda la petición al JSP para que renderice la página
        request.getRequestDispatcher("/admin/gestionar_admins.jsp").forward(request, response);
    }
}