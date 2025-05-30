<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de Empleados</title>
    <style>
        .exito { color: green; padding: 10px; background-color: #e8f5e9; border: 1px solid #c8e6c9; }
        .error { color: red; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f2f2f2; font-weight: bold; }
        .filters { background-color: #f9f9f9; padding: 15px; border-radius: 5px; margin-bottom: 20px; }
        .filter-group { margin-bottom: 10px; }
    </style>
</head>
<body>
<div style="max-width: 1200px; margin: 0 auto; padding: 20px;">
    <h1>Listado de Empleados</h1>

    <%-- Mensajes de éxito/error --%>
    <c:if test="${not empty param.exito}">
        <div class="exito">${param.exito}</div>
    </c:if>

    <div class="filters">
        <%-- Formulario de búsqueda --%>
        <form method="get" action="${pageContext.request.contextPath}/empleados">
            <div class="filter-group">
                <input type="hidden" name="accion" value="buscar">
                <label><strong>Buscar:</strong></label>
                <input type="text" name="criterio" value="${criterioBusqueda}"
                       placeholder="Nombre o documento" style="padding: 8px; width: 300px;">
                <button type="submit" style="padding: 8px 15px; background-color: #4CAF50; color: white; border: none; cursor: pointer;">Buscar</button>
                <a href="${pageContext.request.contextPath}/empleados" style="margin-left: 10px;">Ver todos</a>
            </div>
        </form>

        <%-- Filtros --%>
        <form method="get" action="${pageContext.request.contextPath}/empleados">
            <div class="filter-group">
                <label><strong>Filtrar por estado:</strong></label>
                <select name="estado" style="padding: 8px; width: 150px;">
                    <option value="">Todos</option>
                    <option value="Activo" ${param.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                    <option value="Inactivo" ${param.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                </select>
            </div>

            <div class="filter-group">
                <label><strong>Filtrar por tipo contratación:</strong></label>
                <select name="tipo" style="padding: 8px; width: 150px;">
                    <option value="">Todos</option>
                    <option value="Directo" ${param.tipo == 'Directo' ? 'selected' : ''}>Directo</option>
                    <option value="Subcontratado" ${param.tipo == 'Subcontratado' ? 'selected' : ''}>Subcontratado</option>
                </select>
            </div>

            <button type="submit" style="padding: 8px 15px; background-color: #2196F3; color: white; border: none; cursor: pointer;">Aplicar Filtros</button>
        </form>
    </div>

    <p>
        <a href="${pageContext.request.contextPath}/empleados?accion=nuevo"
           style="padding: 10px 15px; background-color: #4CAF50; color: white; text-decoration: none; display: inline-block; margin-bottom: 20px;">
            + Nuevo Empleado
        </a>
    </p>

    <c:choose>
        <c:when test="${not empty empleados && empleados.size() > 0}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Documento</th>
                    <th>Contratación</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
                <c:forEach items="${empleados}" var="emp">
                    <tr>
                        <td>${emp.idEmpleado}</td>
                        <td>${emp.nombre}</td>
                        <td>${emp.tipoPersona}</td>
                        <td>${emp.documento}</td>
                        <td>${emp.tipoContratacion}</td>
                        <td>
                            <span style="color: ${emp.estado == 'Activo' ? 'green' : 'gray'}; font-weight: bold;">
                                    ${emp.estado}
                            </span>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/empleados?accion=editar&id=${emp.idEmpleado}"
                               style="color: #FF9800; margin-right: 10px;">Editar</a>
                            <c:choose>
                                <c:when test="${emp.estado == 'Activo'}">
                                    <a href="${pageContext.request.contextPath}/empleados?accion=desactivar&id=${emp.idEmpleado}"
                                       style="color: #F44336;"
                                       onclick="return confirm('¿Desactivar este empleado?')">Desactivar</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/empleados?accion=activar&id=${emp.idEmpleado}"
                                       style="color: #4CAF50;"
                                       onclick="return confirm('¿Activar este empleado?')">Activar</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <div style="padding: 20px; background-color: #fff8e1; border: 1px solid #ffecb3; margin-top: 20px;">
                No se encontraron empleados
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
