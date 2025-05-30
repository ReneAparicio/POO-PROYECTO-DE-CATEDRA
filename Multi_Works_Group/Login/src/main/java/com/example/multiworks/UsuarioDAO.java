package udb.multiwork;

import java.sql.Connection;
import java.sql.SQLException;

public interface UsuarioDAO {
    void insertar(Usuario usuario, Connection conn) throws SQLException;
    Usuario buscarPorNombreUsuario(String nombreUsuario, Connection conn) throws SQLException;
}