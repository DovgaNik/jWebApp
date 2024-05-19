package com.example.jwebapplearning;

import java.io.*;
import java.sql.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "dbConnect", value = "/db-connect")
public class DbConnect extends HttpServlet {

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String url = "jdbc:mysql://localhost:3306/test_db";
        String username = Credentials.dbUsername;
        String password = Credentials.dbPassword;


        System.out.println("Connecting database ...");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            out.println("Database connected!");
        } catch (SQLException e) {
            out.println("Database connection failed!: " + e.getMessage());
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}