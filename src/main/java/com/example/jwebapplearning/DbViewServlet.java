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
import java.sql.ResultSet;

@WebServlet(name = "dbViewServlet", value = "/db-view")
public class DbViewServlet extends HttpServlet {

    String url = "jdbc:mysql://localhost:3306/test_db";
    String username = Credentials.dbUsername;
    String password = Credentials.dbPassword;

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");

        out.println("<table>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from  people";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            ResultSet result = preparedStmt.executeQuery();


            while(result.next()){
                out.println("<tr>");

                out.println("<th>" + result.getInt("idpeople") + "</th>");
                out.println("<th>" + result.getString("name") + "</th>");
                out.println("<th>" + result.getString("surname") + "</th>");

                out.println("</tr>");
            }

            connection.close();
        } catch (Exception e) {
            out.println("<br/>" + e.getMessage());
        }

        out.println("</table>");

        out.println("</body></html>");
    }
}
