package com.example.mis.servlet;

import com.example.mis.dao.scDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "deleteSc" , value = "/delete_sc")
public class deleteSc extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        scDataAccessObjects scDao = new scDataAccessObjects();

        String studentNo = request.getParameter("student_no");
        String courseNo = request.getParameter("course_no");

        try {
            scDao.deleteSC(studentNo,courseNo);
        } catch (Exception e) {
            //TODO
            out.println(e);
        }

    }
}
