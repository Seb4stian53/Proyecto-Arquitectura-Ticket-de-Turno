package controlador;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class AdminLoginServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_turnos_db";
    private static final String USER = "root"; // Cambia según tu configuración
    private static final String PASSWORD = ""; // Cambia según tu configuración

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        if (usuario == null || contrasena == null || usuario.isEmpty() || contrasena.isEmpty()) {
            request.setAttribute("error", "Por favor ingrese usuario y contraseña.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM administradores WHERE usuario = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, usuario);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String hashGuardado = rs.getString("contrasena");

                    if (BCrypt.checkpw(contrasena, hashGuardado)) {
                        // Login correcto
                        HttpSession session = request.getSession();
                        session.setAttribute("adminUsuario", usuario);
                        response.sendRedirect("admin/dashboard.jsp");
                        return;
                    }
                }

                // Si no coincide
                request.setAttribute("error", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error de conexión con la base de datos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
