package com.example.mis.servlet;

import com.example.mis.bean.Class;
import com.example.mis.dao.ClassDataAccessObjects;
import com.example.mis.dao.CourseDataAccessObjects;
import com.example.mis.dao.StudentDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 从课程表中删除指定课程号的课程
 */
@WebServlet(name = "deleteCourse" , value = "/delete_course")
public class deleteCourse extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out =  response.getWriter();

        CourseDataAccessObjects courseDao = new CourseDataAccessObjects();

        String courseNo = request.getParameter("course_no");

        try {
            courseDao.deleteCourse(courseNo);
        } catch (Exception e) {
            //TODO
            out.println(e);
        }

    }
}
