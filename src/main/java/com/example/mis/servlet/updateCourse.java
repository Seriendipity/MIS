package com.example.mis.servlet;

import com.example.mis.dao.ClassDataAccessObjects;
import com.example.mis.dao.CourseDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "updateCourse" , value = "/update_course")
public class updateCourse extends HttpServlet {
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

        CourseDataAccessObjects courseDao = new CourseDataAccessObjects();

        String courseNo = request.getParameter("course_no");
        String courseName = request.getParameter("course_name");
        String totalCredit = request.getParameter("course_credit");

        try {
            courseDao.updateCourse(courseNo,courseName,totalCredit);
        } catch (Exception e) {
            out.println(e);
        }
    }
}
