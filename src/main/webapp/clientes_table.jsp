<%--
  Created by IntelliJ IDEA.
  User: Rene Aparicio
  Date: 29/05/2025
  Time: 05:34 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Formulario para agregar cliente -->
<form action="AgregarClienteServlet" method="post" class="row g-2 mb-3">
    <div class="col">
        <input type="text" name="nombre" class="form-control" placeholder="Nombre" required>
    </div>
    <div class="col">
        <input type="text" name="documento" class="form-control" placeholder="Documento" required>
    </div>
    <div class="col">
        <input type="text" name="tipo_persona" class="form-control" placeholder="Tipo Persona" required>
    </div>
    <div class="col">
        <input type="text" name="telefono" class="form-control" placeholder="Teléfono" required>
    </div>
    <div class="col">
        <input type="email" name="correo" class="form-control" placeholder="Correo" required>
    </div>
    <div class="col">
        <input type="text" name="direccion" class="form-control" placeholder="Dirección" required>
    </div>
    <div class="col">
        <input type="text" name="estado" class="form-control" placeholder="Estado" required>
    </div>
    <div class="col">
        <input type="text" name="creado_por" class="form-control" placeholder="Creado Por" required>
    </div>
    <div class="col">
        <input type="date" name="fecha_creacion" class="form-control" required>
    </div>
    <div class="col">
        <input type="date" name="fecha_actualizacion" class="form-control">
    </div>
    <div class="col">
        <input type="date" name="fecha_inactivacion" class="form-control">
    </div>
    <div class="col">
        <button type="submit" class="btn btn-primary">Agregar</button>
    </div>
</form>

<!-- Tabla de clientes -->
<table class="table table-striped">
    <thead>
    <tr>
        <th>Nombre</th>
        <th>Documento</th>
        <th>Tipo Persona</th>
        <th>Teléfono</th>
        <th>Correo</th>
        <th>Dirección</th>
        <th>Estado</th>
        <th>Creado Por</th>
        <th>Fecha Creación</th>
        <th>Fecha Actualización</th>
        <th>Fecha Inactivación</th>
    </tr>
    </thead>
    <tbody>
    <%
        try (
                Connection conn = com.example.multiworks.DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")
        ) {
            while(rs.next()) {
    %>
    <tr>
        <td><%= rs.getString("nombre") %></td>
        <td><%= rs.getString("documento") %></td>
        <td><%= rs.getString("tipo_persona") %></td>
        <td><%= rs.getString("telefono") %></td>
        <td><%= rs.getString("correo") %></td>
        <td><%= rs.getString("direccion") %></td>
        <td><%= rs.getString("estado") %></td>
        <td><%= rs.getString("creado_por") %></td>
        <td><%= rs.getString("fecha_creacion") %></td>
        <td><%= rs.getString("fecha_actualizacion") %></td>
        <td><%= rs.getString("fecha_inactivacion") %></td>
    </tr>
    <%
        }
    } catch(Exception e) {
    %>
    <tr>
        <td colspan="11" class="text-danger">Error: <%= e.getMessage() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>






