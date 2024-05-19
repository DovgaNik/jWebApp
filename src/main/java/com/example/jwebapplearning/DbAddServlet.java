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

@WebServlet (name = "dbAddServlet", value = "/db-add")
public class DbAddServlet extends HttpServlet {
    String url = "jdbc:mysql://localhost:3306/test_db";
    String username = Credentials.dbUsername;
    String password = Credentials.dbPassword;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        out.println(
                "<form action = \"db-add\" method = \"POST\">" +
                        "First Name: <input type = \"text\" name = \"first_name\">" +
                        "<br />" +
                        "Last Name: <input type = \"text\" name = \"last_name\" />" +
                        "<input type = \"submit\" value = \"Submit\" />" +
                        "</form>"
        );
        out.println("</body></html>");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "insert into people (name, surname) values (?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString (1, request.getParameter("first_name"));
            preparedStmt.setString (2, request.getParameter("last_name"));
            preparedStmt.execute();
            connection.close();
            out.println("Added entry successfully<br/>");
        } catch (Exception e) {
            out.println("<br/>" + e.getMessage());
        }
        out.println("</body></html>");
    }
}
