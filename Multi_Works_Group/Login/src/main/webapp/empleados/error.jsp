<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error en la operación</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2dede;
            color: #a94442;
            padding: 20px;
        }
        .container {
            background-color: #fff;
            border: 1px solid #ebccd1;
            padding: 20px;
            border-radius: 5px;
            max-width: 600px;
            margin: auto;
        }
        h1 {
            color: #a94442;
        }
        .detalle {
            font-size: 0.9em;
            color: #a94442;
            margin-top: 10px;
            padding: 10px;
            background-color: #f9f2f4;
            border-left: 4px solid #a94442;
        }
        a {
            color: #a94442;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Ocurrió un error</h1>

    <p><strong>${error}</strong></p>

    <c:if test="${not empty detalleError}">
        <div class="detalle">
            <p>Detalles técnicos (útil para depuración):</p>
            <pre>${detalleError}</pre>
        </div>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/empleados">Volver al listado de empleados</a></p>
</div>
</body>
</html>
