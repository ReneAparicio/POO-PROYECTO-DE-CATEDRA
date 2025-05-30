package com.example.multiworks;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreUsuario = request.getParameter("nombre");
        String contrasenaPlana = request.getParameter("contrasena");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT id_usuario, contrasena_hash, rol FROM usuarios WHERE nombre_usuario = ?"
            );
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String contrasenaHash = rs.getString("contrasena_hash");

                // Verificar contraseña con hash
                if (BCrypt.checkpw(contrasenaPlana, contrasenaHash)) {
                    int idUsuario = rs.getInt("id_usuario");
                    String rol = rs.getString("rol");

                    HttpSession session = request.getSession();
                    session.setAttribute("idUsuario", idUsuario); // <- CORREGIDO
                    session.setAttribute("usuario", nombreUsuario);
                    session.setAttribute("rol", rol);

                    // Redirigir según rol
                    if ("empleado".equals(rol)) {
                        response.sendRedirect("empleado.jsp");
                    } else if ("admin".equals(rol)) {
                        response.sendRedirect("admin.jsp");
                    } else {
                        response.sendRedirect("usuario.jsp");
                    }
                } else {
                    manejarError(request, response, "Contraseña incorrecta");
                }
            } else {
                manejarError(request, response, "Usuario no encontrado");
            }

        } catch (SQLException e) {
            throw new ServletException("Error en base de datos: " + e.getMessage(), e);
        }
    }

    private void manejarError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", mensaje);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
