package udb.multiwork;

import udb.multiwork.EmpleadoDAO;
import udb.multiwork.Empleado;
import udb.multiwork.DBConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImpl implements EmpleadoDAO {
    private final Connection conexion;

    public EmpleadoDAOImpl() throws SQLException {
        this.conexion = DBConnection.getConnection();
    }

    @Override
    public void insertar(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO empleados (nombre, documento, tipo_persona, tipo_contratacion, "
                + "telefono, correo, direccion, estado, creado_por) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getDocumento());
            ps.setString(3, empleado.getTipoPersona());
            ps.setString(4, empleado.getTipoContratacion());
            ps.setString(5, empleado.getTelefono());
            ps.setString(6, empleado.getCorreo());
            ps.setString(7, empleado.getDireccion());
            ps.setString(8, empleado.getEstado());
            ps.setInt(9, empleado.getCreadoPor());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error al crear empleado, ninguna fila afectada.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    empleado.setIdEmpleado(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Error al obtener ID del empleado creado.");
                }
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new SQLException("Error: El documento ya existe en el sistema", e);
            }
            throw e;
        }
    }

    @Override
    public void actualizar(Empleado empleado) throws SQLException {
        String sql = "UPDATE empleados SET nombre = ?, documento = ?, tipo_persona = ?, "
                + "tipo_contratacion = ?, telefono = ?, correo = ?, direccion = ?, "
                + "estado = ?, fecha_actualizacion = CURRENT_TIMESTAMP "
                + "WHERE id_empleado = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getDocumento());
            ps.setString(3, empleado.getTipoPersona());
            ps.setString(4, empleado.getTipoContratacion());
            ps.setString(5, empleado.getTelefono());
            ps.setString(6, empleado.getCorreo());
            ps.setString(7, empleado.getDireccion());
            ps.setString(8, empleado.getEstado());
            ps.setInt(9, empleado.getIdEmpleado());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error al actualizar: Empleado no encontrado con ID: " + empleado.getIdEmpleado());
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new SQLException("Error: El documento ya está registrado en otro empleado", e);
            }
            throw e;
        }
    }

    @Override
    public void desactivar(int idEmpleado) throws SQLException {
        String sql = "UPDATE empleados SET estado = 'Inactivo', fecha_inactivacion = CURRENT_TIMESTAMP "
                + "WHERE id_empleado = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error al desactivar: Empleado no encontrado con ID: " + idEmpleado);
            }
        }
    }

    @Override
    public void activar(int idEmpleado) throws SQLException {
        String sql = "UPDATE empleados SET estado = 'Activo', fecha_inactivacion = NULL "
                + "WHERE id_empleado = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Empleado> listarTodos() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                empleados.add(mapearEmpleado(rs));
            }
        }
        return empleados;
    }

    @Override
    public Empleado buscarPorId(int idEmpleado) throws SQLException {
        String sql = "SELECT * FROM empleados WHERE id_empleado = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearEmpleado(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Empleado> buscarPorTipo(String tipoContratacion) throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados WHERE tipo_contratacion = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tipoContratacion);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    empleados.add(mapearEmpleado(rs));
                }
            }
        }
        return empleados;
    }

    @Override
    public List<Empleado> buscarPorEstado(String estado) throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados WHERE estado = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, estado);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    empleados.add(mapearEmpleado(rs));
                }
            }
        }
        return empleados;
    }

    // Método auxiliar para mapear ResultSet a objeto Empleado
    private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(rs.getInt("id_empleado"));
        empleado.setNombre(rs.getString("nombre"));
        empleado.setDocumento(rs.getString("documento"));
        empleado.setTipoPersona(rs.getString("tipo_persona"));
        empleado.setTipoContratacion(rs.getString("tipo_contratacion"));
        empleado.setTelefono(rs.getString("telefono"));
        empleado.setCorreo(rs.getString("correo"));
        empleado.setDireccion(rs.getString("direccion"));
        empleado.setEstado(rs.getString("estado"));
        empleado.setCreadoPor(rs.getInt("creado_por"));

        // Manejo de fechas con posible valor nulo
        Timestamp tsCreacion = rs.getTimestamp("fecha_creacion");
        if (tsCreacion != null) {
            empleado.setFechaCreacion(tsCreacion.toLocalDateTime());
        }

        Timestamp tsActualizacion = rs.getTimestamp("fecha_actualizacion");
        if (tsActualizacion != null) {
            empleado.setFechaActualizacion(tsActualizacion.toLocalDateTime());
        }

        Timestamp tsInactivacion = rs.getTimestamp("fecha_inactivacion");
        if (tsInactivacion != null) {
            empleado.setFechaInactivacion(tsInactivacion.toLocalDateTime());
        }

        return empleado;
    }

    // Método adicional para búsqueda combinada
    @Override
    public List<Empleado> buscarPorNombreODocumento(String criterio) throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados WHERE nombre LIKE ? OR documento LIKE ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            String busqueda = "%" + criterio + "%";
            ps.setString(1, busqueda);
            ps.setString(2, busqueda);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    empleados.add(mapearEmpleado(rs));
                }
            }
        }
        return empleados;
    }
}