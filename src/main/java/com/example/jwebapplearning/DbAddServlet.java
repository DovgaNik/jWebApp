package com.example.jwebapplearning;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet (name = "dbAddServlet", value = "/db-add")
public class DbAddServlet extends HttpServlet {
    String url = "jdbc:mysql://localhost:3306/test_db";
    String username = Credentials.dbUsername;
    String password = Credentials.dbPassword;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "insert into people (name, surname) values (?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString (1, "s.first_name");
            preparedStmt.setString (2, "s.last_name");
            preparedStmt.execute();
        } catch (Exception e) {
            out.println("<br/>" + e.getMessage());
        }


    }
}
