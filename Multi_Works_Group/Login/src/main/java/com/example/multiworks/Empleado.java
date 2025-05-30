package udb.multiwork;

import java.time.LocalDateTime;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String documento;
    private String tipoPersona;       // "Física" o "Jurídica"
    private String tipoContratacion;  // "Directo" o "Subcontratado"
    private String telefono;
    private String correo;
    private String direccion;
    private String estado;            // "Activo" o "Inactivo"
    private int creadoPor;            // ID del usuario que creó el registro
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private LocalDateTime fechaInactivacion;
    private int idUsuario;

    // Constructor vacío (necesario para JSPs)
    public Empleado() {
    }

    // Constructor con campos básicos
    public Empleado(String nombre, String documento, String tipoPersona, String tipoContratacion) {
        this.nombre = nombre;
        this.documento = documento;
        this.tipoPersona = tipoPersona;
        this.tipoContratacion = tipoContratacion;
        this.estado = "Activo"; // Valor por defecto
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public LocalDateTime getFechaInactivacion() {
        return fechaInactivacion;
    }

    public void setFechaInactivacion(LocalDateTime fechaInactivacion) {
        this.fechaInactivacion = fechaInactivacion;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", tipoPersona='" + tipoPersona + '\'' +
                ", tipoContratacion='" + tipoContratacion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    // Métodos de negocio
    public boolean esSubcontratado() {
        return "Subcontratado".equals(tipoContratacion);
    }

    public boolean esPersonaJuridica() {
        return "Jurídica".equals(tipoPersona);
    }

    public boolean estaActivo() {
        return "Activo".equals(estado);
    }
}