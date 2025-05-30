<%--
  Created by IntelliJ IDEA.
  User: Rene Aparicio
  Date: 29/05/2025
  Time: 05:32 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MultiWorks Group - Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body style="background:#f8fafc">
<div class="container mt-4">
    <h2 class="mb-4">MultiWorks Group</h2>
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation"><button class="nav-link active" id="clientes-tab" data-bs-toggle="tab" data-bs-target="#clientes" type="button" role="tab">Clientes</button></li>
        <li class="nav-item" role="presentation"><button class="nav-link" id="empleados-tab" data-bs-toggle="tab" data-bs-target="#empleados" type="button" role="tab">Empleados</button></li>
        <li class="nav-item" role="presentation"><button class="nav-link" id="actividades-tab" data-bs-toggle="tab" data-bs-target="#actividades" type="button" role="tab">Actividades</button></li>
        <li class="nav-item" role="presentation"><button class="nav-link" id="cotizaciones-tab" data-bs-toggle="tab" data-bs-target="#cotizaciones" type="button" role="tab">Cotizaciones</button></li>
    </ul>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="clientes" role="tabpanel">
            <%@ include file="clientes_table.jsp" %>
        </div>
        <div class="tab-pane fade" id="empleados" role="tabpanel">
            <%@ include file="empleados_table.jsp" %>
        </div>
        <div class="tab-pane fade" id="actividades" role="tabpanel">
            <%@ include file="activadades_table.jsp" %>
        </div>
        <div class="tab-pane fade" id="cotizaciones" role="tabpanel">
            <%@ include file="cotizaciones_table.jsp" %>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>




