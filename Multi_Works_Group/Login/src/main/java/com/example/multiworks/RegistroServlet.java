package com.example.multiworks;

import com.example.multiworks.DBConnection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class RegistroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String rol = request.getParameter("rol");

        try (Connection conn = DBConnection.getConnection()) {
            // Validar si el nombre ya existe
            PreparedStatement checkNombre = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre = ?");
            checkNombre.setString(1, nombre);
            ResultSet rsNombre = checkNombre.executeQuery();
            if (rsNombre.next()) {
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

            // Insertar nuevo usuario
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO usuarios (nombre, correo, contrasena, rol) VALUES (?, ?, ?, ?)");
            insertStmt.setString(1, nombre);
            insertStmt.setString(2, correo);
            insertStmt.setString(3, contrasena);
            insertStmt.setString(4, rol);
            insertStmt.executeUpdate();

            response.sendRedirect("login.jsp");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
