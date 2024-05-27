package com.example.mis.servlet;

import com.example.mis.bean.Course;
import com.example.mis.dao.CourseDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "DisplayCourses",value = "/display_courses")
public class DisplayCourses extends HttpServlet {
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

        CourseDataAccessObjects courseDao = new CourseDataAccessObjects();

        try {
            ArrayList<Course> courses = courseDao.selectFromCourse();
            System.out.println(1);
            for(Course c : courses){
                System.out.print(c.getCourseNo());
                System.out.print(c.getCourseName());
                System.out.println(c.getCourseCredit());
                System.out.println();
            }
            request.setAttribute("courses",courses);
            request.getRequestDispatcher("/display_courses.jsp").forward(request,response);
        } catch (Exception e) {
            out.println(e);
        }
    }
}
