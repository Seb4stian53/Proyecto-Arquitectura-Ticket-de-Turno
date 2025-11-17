package proyectoturnos.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 
import proyectoturnos.dao.AsuntoDAO;
import proyectoturnos.dao.DashboardDAO;
import proyectoturnos.dao.MunicipioDAO;
import proyectoturnos.dao.TurnoDAO;
import proyectoturnos.model.Asunto;
import proyectoturnos.model.Municipio;

@WebServlet("/panel/dashboard")
public class DashboardServlet extends HttpServlet {
    private TurnoDAO turnoDAO;
    private DashboardDAO dashboardDAO;
    private MunicipioDAO municipioDAO;
    private AsuntoDAO asuntoDAO;
    private Gson gson;

    @Override
    public void init() {
        turnoDAO = new TurnoDAO();
        dashboardDAO = new DashboardDAO();
        municipioDAO = new MunicipioDAO();
        asuntoDAO = new AsuntoDAO();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String estatusFiltroParam = request.getParameter("estatusFiltro");
        String idMunicipioFiltroParam = request.getParameter("idMunicipioFiltro");
        String idAsuntoFiltroParam = request.getParameter("idAsuntoFiltro");

        Integer idMunicipioFiltro = (idMunicipioFiltroParam != null && !idMunicipioFiltroParam.isEmpty()) ? Integer.parseInt(idMunicipioFiltroParam) : null;
        Integer idAsuntoFiltro = (idAsuntoFiltroParam != null && !idAsuntoFiltroParam.isEmpty()) ? Integer.parseInt(idAsuntoFiltroParam) : null;
        String estatusFiltro = (estatusFiltroParam != null && !estatusFiltroParam.isEmpty()) ? estatusFiltroParam : null;

        int totalTurnos = turnoDAO.contarTotal();
        int pendientes = turnoDAO.contarPorEstado("Pendiente");
        int resueltos = turnoDAO.contarPorEstado("Resuelto");
        int cancelados = turnoDAO.contarPorEstado("Cancelado");

        request.setAttribute("totalTurnos", totalTurnos);
        request.setAttribute("pendientes", pendientes);
        request.setAttribute("resueltos", resueltos);
        request.setAttribute("cancelados", cancelados);

        Map<String, Integer> turnosPorMunicipio = dashboardDAO.contarTurnosPorMunicipio(estatusFiltro, idAsuntoFiltro);
        Map<String, Integer> turnosPorAsunto = dashboardDAO.contarTurnosPorAsunto(estatusFiltro, idMunicipioFiltro);
        Map<String, Integer> turnosPorEstado = dashboardDAO.contarTurnosPorEstado(idMunicipioFiltro, idAsuntoFiltro); // Nueva gráfica

        String turnosPorMunicipioLabels = gson.toJson(turnosPorMunicipio.keySet());
        String turnosPorMunicipioData = gson.toJson(turnosPorMunicipio.values());
        String turnosPorAsuntoLabels = gson.toJson(turnosPorAsunto.keySet());
        String turnosPorAsuntoData = gson.toJson(turnosPorAsunto.values());
        String turnosPorEstadoLabels = gson.toJson(turnosPorEstado.keySet()); // Nueva
        String turnosPorEstadoData = gson.toJson(turnosPorEstado.values());   // Nueva

        request.setAttribute("turnosPorMunicipioLabels", turnosPorMunicipioLabels);
        request.setAttribute("turnosPorMunicipioData", turnosPorMunicipioData);
        request.setAttribute("turnosPorAsuntoLabels", turnosPorAsuntoLabels);
        request.setAttribute("turnosPorAsuntoData", turnosPorAsuntoData);
        request.setAttribute("turnosPorEstadoLabels", turnosPorEstadoLabels); // Nueva
        request.setAttribute("turnosPorEstadoData", turnosPorEstadoData);     // Nueva

        List<Municipio> listaMunicipios = municipioDAO.obtenerTodos();
        List<Asunto> listaAsuntos = asuntoDAO.obtenerTodos();
        
        request.setAttribute("listaMunicipios", listaMunicipios);
        request.setAttribute("listaAsuntos", listaAsuntos);

        request.setAttribute("selectedEstatusFiltro", estatusFiltroParam);
        request.setAttribute("selectedIdMunicipioFiltro", idMunicipioFiltroParam);
        request.setAttribute("selectedIdAsuntoFiltro", idAsuntoFiltroParam);

        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }
}