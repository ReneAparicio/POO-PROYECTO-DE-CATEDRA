<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Usuario</title>
    <style>
        body { background: #f8f9fa; font-family: Arial, sans-serif; }
        .container { width: 400px; margin: 40px auto; padding: 24px 32px; background: #fff; border-radius: 12px; box-shadow: 0 0 14px #bbb; }
        h2 { text-align: center; margin-bottom: 24px; }
        .form-group { margin-bottom: 18px; }
        label { display: block; margin-bottom: 8px; }
        input, select { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #bbb; border-radius: 5px; }
        .btn { width: 100%; background: #0866ff; color: #fff; border: none; padding: 12px 0; border-radius: 5px; font-size: 16px; }
        .btn:hover { background: #0846aa; }
        .link { display: block; text-align: center; margin-top: 16px; }
        .error { color: #d11a1a; background: #ffeaea; padding: 8px; margin-bottom: 16px; border-radius: 5px; text-align: center; }
    </style>
</head>
<body>
<div class="container">
    <h2>Registro de Usuario</h2>

    <% String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { %>
    <div class="error"><%= errorMessage %></div>
    <% } %>

    <form method="post" action="registro">
        <div class="form-group">
            <label for="nombre">Nombre de usuario</label>
            <input type="text" id="nombre" name="nombre" required>
        </div>
        <div class="form-group">
            <label for="correo">Correo</label>
            <input type="email" id="correo" name="correo" required>
        </div>
        <div class="form-group">
            <label for="contrasena">Contraseña</label>
            <input type="password" id="contrasena" name="contrasena" required>
        </div>
        <div class="form-group">
            <label for="rol">Rol</label>
            <select id="rol" name="rol">
                <option value="Usuario">Usuario</option>
                <option value="empleado">Empleado</option>
            </select>
        </div>
        <button class="btn" type="submit">Registrarse</button>
    </form>
    <div class="link">
        <a href="login.jsp">¿Ya tienes cuenta? Inicia sesión</a>
    </div>
</div>
</body>
</html>

