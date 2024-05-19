package com.example.jwebapplearning;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "infoServlet", value = "/info-servlet")
public class InfoServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        out.println(
                "<form action = \"info-servlet\" method = \"POST\">" +
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
        out.println(request.getParameter("first_name"));
        out.println(request.getParameter("last_name"));
        out.println("</body></html>");


    }

    public void destroy() {
    }
}
