package udb.multiwork;

import java.sql.SQLException;
import java.util.List;

public interface EmpleadoDAO {
    void insertar(Empleado empleado) throws SQLException;
    void actualizar(Empleado empleado) throws SQLException;
    void desactivar(int idEmpleado) throws SQLException;
    void activar(int idEmpleado) throws SQLException; // Añadido
    List<Empleado> listarTodos() throws SQLException;
    Empleado buscarPorId(int idEmpleado) throws SQLException;
    List<Empleado> buscarPorTipo(String tipoContratacion) throws SQLException;
    List<Empleado> buscarPorEstado(String estado) throws SQLException; // Añadido
    List<Empleado> buscarPorNombreODocumento(String criterio) throws SQLException; // Añadido
}