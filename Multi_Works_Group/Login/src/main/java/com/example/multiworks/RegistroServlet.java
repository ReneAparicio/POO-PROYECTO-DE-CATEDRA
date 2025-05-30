package com.example.multiworks;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreUsuario = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasenaPlana = request.getParameter("contrasena");
        String rol = request.getParameter("rol");

        // Generar hash seguro
        String contrasenaHash = BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt());

        try (Connection conn = DBConnection.getConnection()) {
            // Validar si el nombre de usuario ya existe (CORREGIDO)
            PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT * FROM usuarios WHERE nombre_usuario = ?"
            );
            checkStmt.setString(1, nombreUsuario);

            if (checkStmt.executeQuery().next()) {
                request.setAttribute("errorMessage", "El nombre de usuario ya está en uso.");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
                return;
            }

            // Validar si el correo ya existe
            PreparedStatement checkCorreo = conn.prepareStatement("SELECT * FROM usuarios WHERE correo = ?");
            checkCorreo.setString(1, correo);
            ResultSet rsCorreo = checkCorreo.executeQuery();
            if (rsCorreo.next()) {
                request.setAttribute("errorMessage", "El correo ya está registrado.");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
                return;
            }

            // Insertar nuevo usuario (CORREGIDO)
            PreparedStatement insertStmt = conn.prepareStatement(
                    "INSERT INTO usuarios (nombre_usuario, correo, contrasena_hash, rol) VALUES (?, ?, ?, ?)"
            );
            insertStmt.setString(1, nombreUsuario);
            insertStmt.setString(2, correo);
            insertStmt.setString(3, contrasenaHash);
            insertStmt.setString(4, rol);
            insertStmt.executeUpdate();

            response.sendRedirect("login.jsp?registro=exito");

        } catch (SQLException e) {
            throw new ServletException("Error en base de datos: " + e.getMessage(), e);
        }
    }
}
