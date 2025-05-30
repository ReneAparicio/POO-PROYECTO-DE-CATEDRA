<%@ page import="java.sql.*" %>
<table class="table table-striped">
  <thead>
  <tr>
    <th>Título Actividad</th>
    <th>Trabajador</th>
    <th>Área</th>
    <th>Costo/hora</th>
    <th>Inicio</th>
    <th>Fin</th>
    <th>Horas approx</th>
    <th>Incremento (%)</th>
    <th>Total</th>
  </tr>
  </thead>
  <tbody>
  <%
    try (
            Connection conn = com.example.multiworks.DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cotizaciones")
    ) {
      while(rs.next()) {
  %>
  <tr>
    <td><%= rs.getString("titulo_actividad") %></td>
    <td><%= rs.getString("trabajador") %></td>
    <td><%= rs.getString("area") %></td>
    <td><%= rs.getDouble("costo_hora") %></td>
    <td><%= rs.getString("inicio") %></td>
    <td><%= rs.getString("fin") %></td>
    <td><%= rs.getDouble("horas_aprox") %></td>
    <td><%= rs.getDouble("incremento") %></td>
    <td><%= rs.getDouble("total") %></td>
  </tr>
  <%
    }
  } catch(Exception e) {
  %>
  <tr>
    <td colspan="9" class="text-danger">Error: <%= e.getMessage() %></td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>





