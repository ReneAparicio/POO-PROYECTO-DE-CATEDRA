package com.example.multiworks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AgregarClienteServlet")
public class AgregarClienteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String documento = req.getParameter("documento");
        String tipo_persona = req.getParameter("tipo_persona");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");
        String direccion = req.getParameter("direccion");
        String estado = req.getParameter("estado");
        String creado_por = req.getParameter("creado_por");
        String fecha_creacion = req.getParameter("fecha_creacion");
        String fecha_actualizacion = req.getParameter("fecha_actualizacion");
        String fecha_inactivacion = req.getParameter("fecha_inactivacion");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO clientes (nombre, documento, tipo_persona, telefono, correo, direccion, estado, creado_por, fecha_creacion, fecha_actualizacion, fecha_inactivacion) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, documento);
            ps.setString(3, tipo_persona);
            ps.setString(4, telefono);
            ps.setString(5, correo);
            ps.setString(6, direccion);
            ps.setString(7, estado);
            ps.setString(8, creado_por);
            ps.setString(9, fecha_creacion);
            ps.setString(10, fecha_actualizacion);
            ps.setString(11, fecha_inactivacion);

            ps.executeUpdate();
        } catch (Exception e) {
            throw new ServletException(e);
        }
        resp.sendRedirect("dashboard.jsp");
    }
}

