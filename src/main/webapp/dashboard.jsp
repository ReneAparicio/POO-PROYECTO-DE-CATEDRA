<%--
  Created by IntelliJ IDEA.
  User: Rene Aparicio
  Date: 29/05/2025
  Time: 05:32 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>MultiWorks Group - Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body style="background:#f8fafc">
<div class="container mt-4">
    <h2 class="mb-4">MultiWorks Group</h2>
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item"><a class="nav-link active" data-bs-toggle="tab" href="#clientes">Clientes</a></li>
        <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#empleados">Empleados</a></li>
        <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#actividades">Actividades</a></li>
        <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#cotizaciones">Cotizaciones</a></li>
    </ul>
    <div class="tab-content mt-3">
        <div class="tab-pane fade show active" id="clientes"><%@ include file="clientes_table.jsp" %></div>
        <div class="tab-pane fade" id="empleados"><%@ include file="empleados_table.jsp" %></div>
        <div class="tab-pane fade" id="actividades"><%@ include file="activadades_table.jsp" %></div>
        <div class="tab-pane fade" id="cotizaciones"><%@ include file="cotizaciones_table.jsp" %></div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>





