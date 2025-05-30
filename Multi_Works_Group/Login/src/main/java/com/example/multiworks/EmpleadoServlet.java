package udb.multiwork;

import udb.multiwork.EmpleadoDAO;
import udb.multiwork.EmpleadoDAOImpl;
import udb.multiwork.Empleado;
import udb.multiwork.DBConnection;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/empleados"})
public class EmpleadoServlet extends HttpServlet {

    private EmpleadoDAO empleadoDAO;

    @Override
    public void init() throws ServletException {
        try {
            empleadoDAO = new EmpleadoDAOImpl();
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar EmpleadoDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        accion = (accion == null) ? "listar" : accion;

        try {
            switch (accion) {
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "desactivar":
                    desactivarEmpleado(request, response);
                    break;
                case "activar":
                    activarEmpleado(request, response);
                    break;
                case "buscar":
                    buscarEmpleados(request, response);
                    break;
                case "listar":
                default:
                    listarEmpleados(request, response);
                    break;
            }
        } catch (SQLException e) {
            manejarErrorSQL(request, response, e);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID de empleado inválido");
            request.getRequestDispatcher("empleados/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        accion = (accion == null) ? "guardar" : accion;

        try {
            switch (accion) {
                case "guardar":
                    guardarEmpleado(request, response);
                    break;
                case "actualizar":
                    actualizarEmpleado(request, response);
                    break;
                default:
                    listarEmpleados(request, response);
            }
        } catch (SQLException e) {
            manejarErrorSQL(request, response, e);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Formato de número inválido");
            request.getRequestDispatcher("empleados/error.jsp").forward(request, response);
        }
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        // Obtener parámetros de filtro
        String estado = request.getParameter("estado");
        String tipo = request.getParameter("tipo");
        String tipoPersona = request.getParameter("tipo_persona");

        List<Empleado> empleados;

        if (estado != null && !estado.isEmpty()) {
            empleados = empleadoDAO.buscarPorEstado(estado);
        } else if (tipo != null && !tipo.isEmpty()) {
            empleados = empleadoDAO.buscarPorTipo(tipo);
        } else if (tipoPersona != null && !tipoPersona.isEmpty()) {
            empleados = empleadoDAO.listarTodos().stream()
                    .filter(e -> e.getTipoPersona().equals(tipoPersona))
                    .collect(Collectors.toList());
        } else {
            empleados = empleadoDAO.listarTodos();
        }

        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("empleado.jsp").forward(request, response);
    }

    private void buscarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String criterio = request.getParameter("criterio");
        List<Empleado> empleados;

        if (criterio != null && !criterio.isEmpty()) {
            empleados = empleadoDAO.buscarPorNombreODocumento(criterio);
        } else {
            empleados = empleadoDAO.listarTodos();
        }

        request.setAttribute("empleados", empleados);
        request.setAttribute("criterioBusqueda", criterio);
        request.getRequestDispatcher("empleado.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Empleado empleado = new Empleado();
        empleado.setTipoPersona("Física");
        empleado.setTipoContratacion("Directo");
        empleado.setEstado("Activo");

        request.setAttribute("empleado", empleado);
        request.setAttribute("modo", "nuevo");
        request.getRequestDispatcher("empleados/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Empleado empleado = empleadoDAO.buscarPorId(id);

        if (empleado == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Empleado no encontrado");
            return;
        }

        request.setAttribute("empleado", empleado);
        request.setAttribute("modo", "editar");
        request.getRequestDispatcher("empleados/formulario.jsp").forward(request, response);
    }

    private void guardarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        Empleado empleado = crearEmpleadoDesdeRequest(request);
        validarEmpleado(empleado);

        // Obtener ID del usuario autenticado
        HttpSession session = request.getSession();
        Integer creadoPor = (Integer) session.getAttribute("idUsuario");
        if (creadoPor == null) {
            throw new IllegalArgumentException("Debe iniciar sesión para registrar empleados.");
        }
        empleado.setCreadoPor(creadoPor);


        empleadoDAO.insertar(empleado);
        response.sendRedirect("empleados?exito=Empleado creado correctamente");
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Empleado empleadoExistente = empleadoDAO.buscarPorId(id);

        if (empleadoExistente == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Empleado no encontrado");
            return;
        }

        Empleado empleadoActualizado = crearEmpleadoDesdeRequest(request);
        validarEmpleado(empleadoActualizado);
        empleadoActualizado.setIdEmpleado(id);
        empleadoActualizado.setEstado(empleadoExistente.getEstado()); // Mantener estado actual

        empleadoDAO.actualizar(empleadoActualizado);
        response.sendRedirect("empleados?exito=Empleado actualizado correctamente");
    }

    private void desactivarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        empleadoDAO.desactivar(id);
        response.sendRedirect("empleados?exito=Empleado desactivado");
    }

    private void activarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        empleadoDAO.activar(id);
        response.sendRedirect("empleados?exito=Empleado activado");
    }

    private Empleado crearEmpleadoDesdeRequest(HttpServletRequest request) {
        Empleado empleado = new Empleado();

        empleado.setNombre(request.getParameter("nombre"));
        empleado.setDocumento(request.getParameter("documento"));
        empleado.setTipoPersona(request.getParameter("tipo_persona"));
        empleado.setTipoContratacion(request.getParameter("tipo_contratacion"));
        empleado.setTelefono(request.getParameter("telefono"));
        empleado.setCorreo(request.getParameter("correo"));
        empleado.setDireccion(request.getParameter("direccion"));

        // Para empleados subcontratados (persona jurídica)
        if ("Jurídica".equals(empleado.getTipoPersona())) {
            if (empleado.getDocumento() == null || !empleado.getDocumento().startsWith("J-")) {
                empleado.setDocumento("J-" + (empleado.getDocumento() != null ? empleado.getDocumento() : ""));
            }
            empleado.setTipoContratacion("Subcontratado");
        }

        return empleado;
    }

    private void validarEmpleado(Empleado empleado) {
        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es requerido");
        }

        if (empleado.getDocumento() == null || empleado.getDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("El documento es requerido");
        }

        if ("Jurídica".equals(empleado.getTipoPersona()) &&
                !empleado.getDocumento().startsWith("J-")) {
            throw new IllegalArgumentException("Documento de PJ debe comenzar con 'J-'");
        }

        if (empleado.getCorreo() != null && !empleado.getCorreo().isEmpty() &&
                !empleado.getCorreo().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Formato de email inválido");
        }
    }

    private void manejarErrorSQL(HttpServletRequest request, HttpServletResponse response, SQLException e)
            throws ServletException, IOException {
        String mensajeError = "Error en base de datos";

        // Manejo específico de errores conocidos
        if (e.getMessage().contains("documento")) {
            mensajeError = "Error: El documento ya existe en el sistema";
        } else if (e.getErrorCode() == 1062) { // Código de error para duplicados en MySQL
            mensajeError = "Error: El documento ya está registrado";
        }

        request.setAttribute("error", mensajeError);
        request.setAttribute("detalleError", e.getMessage());
        request.getRequestDispatcher("empleados/error.jsp").forward(request, response);
    }
}