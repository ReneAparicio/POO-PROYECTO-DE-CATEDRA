<%--
  Created by IntelliJ IDEA.
  User: Rene Aparicio
  Date: 29/05/2025
  Time: 05:53 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.*" %>
<table class="table table-striped">
  <thead>
  <tr>
    <th>Nombre</th>
    <th>Documento</th>
    <th>Tipo Persona</th>
    <th>Tipo Contratación</th>
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM empleados")
    ) {
      while(rs.next()) {
  %>
  <tr>
    <td><%= rs.getString("nombre") %></td>
    <td><%= rs.getString("documento") %></td>
    <td><%= rs.getString("tipo_persona") %></td>
    <td><%= rs.getString("tipo_contratacion") %></td>
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
    <td colspan="12" class="text-danger">Error: <%= e.getMessage() %></td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>





