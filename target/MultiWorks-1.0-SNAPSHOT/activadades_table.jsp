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
    <th>ID Asignación</th>
    <th>Título Subtarea</th>
    <th>Descripción Subtarea</th>
  </tr>
  </thead>
  <tbody>
  <%
    try (
            Connection conn = com.example.multiworks.DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM actividades")
    ) {
      while(rs.next()) {
  %>
  <tr>
    <td><%= rs.getInt("id_asignacion") %></td>
    <td><%= rs.getString("titulo_subtarea") %></td>
    <td><%= rs.getString("descripcion_subtarea") %></td>
  </tr>
  <%
    }
  } catch(Exception e) {
  %>
  <tr>
    <td colspan="3" class="text-danger">Error: <%= e.getMessage() %></td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>


