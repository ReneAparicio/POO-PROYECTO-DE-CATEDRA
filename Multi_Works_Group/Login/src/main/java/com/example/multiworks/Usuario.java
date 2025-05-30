package udb.multiwork;

import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenaHash;
    private String rol;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimoAcceso;

    // Constructores
    public Usuario() {
        // Constructor vacío necesario para JSPs y frameworks
    }

    public Usuario(String nombreUsuario, String contrasenaHash, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenaHash = contrasenaHash;
        this.rol = rol;
    }

    // Getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(LocalDateTime ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    // Métodos de negocio
    public boolean esAdmin() {
        return "admin".equalsIgnoreCase(rol);
    }

    public boolean esEmpleado() {
        return "empleado".equalsIgnoreCase(rol);
    }

    public boolean tieneAcceso(String rolRequerido) {
        if (rolRequerido == null) return true;
        return rolRequerido.equalsIgnoreCase(rol);
    }

    // Método para registro
    public void generarHashContrasena(String contrasenaPlana) {
        // Importar BCrypt: import org.mindrot.jbcrypt.BCrypt;
        this.contrasenaHash = BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt());
    }

    // Método para verificar contraseña
    public boolean verificarContrasena(String contrasenaPlana) {
        return BCrypt.checkpw(contrasenaPlana, this.contrasenaHash);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}