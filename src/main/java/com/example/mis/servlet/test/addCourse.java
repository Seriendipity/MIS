package com.example.mis.servlet.test;

import com.example.mis.dao.CourseDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 向Course表中插入一个课程
 */
@WebServlet(name = "addCourse" , value = "/add_course")
public class addCourse extends HttpServlet {
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
            courseDao.insertCourse(courseNo,courseName,totalCredit);
        } catch (Exception e) {
            out.println(e);
        }

    }
}
