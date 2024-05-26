package com.example.mis.servlet;

import com.example.mis.dao.teachingDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addTeaching",value = "/add_teaching")
public class addTeaching extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

       teachingDataAccessObjects teachingDao = new teachingDataAccessObjects();

       String teacherNo = request.getParameter("teacher_no");
       String courseNo = request.getParameter("course_no");
       String language = request.getParameter("language");

        try {
            teachingDao.insertTeaching(courseNo,teacherNo,language);
        } catch (Exception e) {
            out.println(e);
        }
    }
}
