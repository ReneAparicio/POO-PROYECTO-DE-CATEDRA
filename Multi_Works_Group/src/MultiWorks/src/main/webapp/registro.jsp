<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4 class="card-title text-center mb-4">Registro de Usuario</h4>

                    <form action="registro" method="post">
                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre de usuario</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" required>
                        </div>

                        <div class="mb-3">
                            <label for="correo" class="form-label">Correo</label>
                            <input type="email" class="form-control" id="correo" name="correo" required>
                        </div>

                        <div class="mb-3">
                            <label for="contrasena" class="form-label">Contraseña</label>
                            <input type="password" class="form-control" id="contrasena" name="contrasena" required>
                        </div>

                        <div class="mb-3">
                            <label for="rol" class="form-label">Rol</label>
                            <select class="form-select" id="rol" name="rol" required>
                                <option value="usuario">Usuario</option>
                                <option value="empleado">Empleado</option>
                            </select>
                        </div>

                        <div class="d-grid mb-3">
                            <button type="submit" class="btn btn-primary">Registrarse</button>
                        </div>
                    </form>

                    <div class="text-center">
                        <a href="login.jsp" class="btn btn-link">¿Ya tienes cuenta? Inicia sesión</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
