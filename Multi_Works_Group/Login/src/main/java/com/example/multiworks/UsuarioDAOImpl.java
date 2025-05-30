package udb.multiwork;


import java.sql.*;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void insertar(Usuario usuario, Connection conn) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena_hash, rol) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasenaHash());
            ps.setString(3, usuario.getRol());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error al crear usuario, ninguna fila afectada.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setIdUsuario(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Error al obtener ID del usuario creado.");
                }
            }
        }
    }

    @Override
    public Usuario buscarPorNombreUsuario(String nombreUsuario, Connection conn) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }
        return null;
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNombreUsuario(rs.getString("nombre_usuario"));
        usuario.setContrasenaHash(rs.getString("contrasena_hash"));
        usuario.setRol(rs.getString("rol"));

        // Manejo de fechas
        Timestamp tsCreacion = rs.getTimestamp("fecha_creacion");
        if (tsCreacion != null) {
            usuario.setFechaCreacion(tsCreacion.toLocalDateTime());
        }

        Timestamp tsAcceso = rs.getTimestamp("ultimo_acceso");
        if (tsAcceso != null) {
            usuario.setUltimoAcceso(tsAcceso.toLocalDateTime());
        }

        return usuario;
    }
}