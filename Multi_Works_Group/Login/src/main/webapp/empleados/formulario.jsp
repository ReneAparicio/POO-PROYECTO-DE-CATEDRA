<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario de Empleado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function actualizarCamposPersona() {
            const tipoPersona = document.querySelector('input[name="tipo_persona"]:checked').value;
            const documentoField = document.getElementById('documento');

            if (tipoPersona === 'Jurídica') {
                // Forzar prefijo J- y tipo de contratación
                if (!documentoField.value.startsWith('J-')) {
                    documentoField.value = 'J-' + documentoField.value;
                }
                document.getElementById('tipo_contratacion_subcontratado').checked = true;
                document.getElementById('tipo_contratacion_directo').disabled = true;
            } else {
                // Remover prefijo J-
                documentoField.value = documentoField.value.replace(/^J-/, '');
                document.getElementById('tipo_contratacion_directo').disabled = false;
            }
        }
    </script>
</head>
<body>
<div class="container mt-4">
    <h2>
        <c:choose>
            <c:when test="${modo == 'nuevo'}">Nuevo Empleado</c:when>
            <c:otherwise>Editar Empleado</c:otherwise>
        </c:choose>
    </h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="empleados" method="post">
        <input type="hidden" name="accion" value="${modo == 'nuevo' ? 'guardar' : 'actualizar'}">
        <c:if test="${modo == 'editar'}">
            <input type="hidden" name="id" value="${empleado.idEmpleado}">
        </c:if>

        <div class="mb-3">
            <label class="form-label">Tipo de Persona *</label>
            <div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="tipo_persona" id="fisica" value="Física"
                    ${empleado.tipoPersona == 'Física' ? 'checked' : ''} required onchange="actualizarCamposPersona()">
                    <label class="form-check-label" for="fisica">Física</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="tipo_persona" id="juridica" value="Jurídica"
                    ${empleado.tipoPersona == 'Jurídica' ? 'checked' : ''} onchange="actualizarCamposPersona()">
                    <label class="form-check-label" for="juridica">Jurídica</label>
                </div>
            </div>
        </div>

        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre Completo *</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required
                   value="${empleado.nombre}">
        </div>

        <div class="mb-3">
            <label for="documento" class="form-label">Documento *</label>
            <input type="text" class="form-control" id="documento" name="documento" required
                   value="${empleado.documento}" oninput="actualizarCamposPersona()">
        </div>

        <div class="mb-3">
            <label class="form-label">Tipo de Contratación *</label>
            <div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="tipo_contratacion" id="tipo_contratacion_directo" value="Directo"
                    ${empleado.tipoContratacion == 'Directo' ? 'checked' : ''} required>
                    <label class="form-check-label" for="tipo_contratacion_directo">Directo</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="tipo_contratacion" id="tipo_contratacion_subcontratado" value="Subcontratado"
                    ${empleado.tipoContratacion == 'Subcontratado' ? 'checked' : ''}>
                    <label class="form-check-label" for="tipo_contratacion_subcontratado">Subcontratado</label>
                </div>
            </div>
        </div>

        <div class="mb-3">
            <label for="telefono" class="form-label">Teléfono</label>
            <input type="tel" class="form-control" id="telefono" name="telefono"
                   value="${empleado.telefono}">
        </div>

        <div class="mb-3">
            <label for="correo" class="form-label">Correo Electrónico</label>
            <input type="email" class="form-control" id="correo" name="correo"
                   value="${empleado.correo}">
        </div>

        <div class="mb-3">
            <label for="direccion" class="form-label">Dirección</label>
            <textarea class="form-control" id="direccion" name="direccion" rows="2">${empleado.direccion}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${modo == 'nuevo'}">Crear Empleado</c:when>
                <c:otherwise>Actualizar Empleado</c:otherwise>
            </c:choose>
        </button>
        <a href="empleados" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>