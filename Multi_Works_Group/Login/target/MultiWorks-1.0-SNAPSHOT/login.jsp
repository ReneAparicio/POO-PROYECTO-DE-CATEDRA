<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4 class="card-title text-center mb-4">Iniciar Sesión</h4>

                    <form method="post" action="login">
                        <div class="mb-3">
                            <label for="nombre" class="form-label">Usuario</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" required>
                        </div>

                        <div class="mb-3">
                            <label for="contrasena" class="form-label">Contraseña</label>
                            <input type="password" class="form-control" id="contrasena" name="contrasena" required>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Iniciar sesión</button>
                        </div>

                        <% String error = (String) request.getAttribute("errorMessage"); %>
                        <% if (error != null) { %>
                        <div class="mt-3 alert alert-danger text-center p-2"><%= error %></div>
                        <% } %>
                    </form>
                    <div class="text-center">
                        <a href="registro.jsp" class="btn btn-link">¿No tienes cuenta? Registrate</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>