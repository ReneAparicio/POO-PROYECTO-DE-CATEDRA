package com.example.multiworks;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String contrasena = request.getParameter("contrasena");

        try (Connection conn = DBConnection.getConnection()) {
            // CAMBIAR LOS NOMBRES DE LAS COLUMNAS
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT rol FROM usuarios WHERE nombre_usuario = ? AND contrasena_hash = ?"
            );
            stmt.setString(1, nombre);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String rol = rs.getString("rol");
                HttpSession session = request.getSession();
                session.setAttribute("rol", rol);

                if ("empleado".equals(rol)) {
                    response.sendRedirect("empleado.jsp");
                } else {
                    response.sendRedirect("dashboard.jsp");
                }
            } else {
                request.setAttribute("errorMessage", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

