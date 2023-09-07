package com.ipc2.proyectoipc2.web;

import com.ipc2.proyectoipc2.data.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Conexion conexion = new Conexion();
        conexion.obtenerConexion();

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3500);
        session.setAttribute("conexion", conexion.obtenerConexion());

        response.sendRedirect("login.jsp");
    }

}
