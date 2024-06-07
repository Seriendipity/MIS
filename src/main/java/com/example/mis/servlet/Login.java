package com.example.mis.servlet;

import com.example.mis.bean.Admin;
import com.example.mis.dao.ClassDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/loginServlet")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if(name.length() == 5){
            if(name.equals("admin")){
                if(password.equals("admin")){
                    Admin admin = new Admin();
                    admin.setName("admin");
                    HttpSession session = request.getSession();
                    session.setAttribute("admin",admin);
                    request.getRequestDispatcher("/admin.jsp").forward(request,response);
                }
            }
        }else if(name.length() == 4){

        }
    }
}
