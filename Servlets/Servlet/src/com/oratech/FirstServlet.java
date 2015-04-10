package com.oratech;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class FirstServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        out.println("Hello Servlets ");
        out.println(request.getParameter("myURLParam"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String c = request.getParameter("color");

//        PrintWriter out = response.getWriter();
//        out.println("Beer Selection Advice<br>");
//        out.println("<br>Got beer color " + c);
        
        HttpSession session = request.getSession();
        if(request.getParameter("clientName") != null && !request.getParameter("clientName").equals(""))
            session.setAttribute("customerName", request.getParameter("clientName"));
        
        List result = getBrands(c);

        request.setAttribute("styles", result);
        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        view.forward(request, response);        
    }

    public List getBrands(String color) {
        List brands = new ArrayList();
        if (color.equals("amber")) {
            brands.add("Jack Amber");
            brands.add("Red Moose");
        } else {
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }
        return (brands);
    }
}
