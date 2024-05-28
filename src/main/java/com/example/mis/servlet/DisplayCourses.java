package com.example.mis.servlet;

import com.example.mis.bean.Course;
import com.example.mis.bean.T_C_Info;
import com.example.mis.dao.CourseDataAccessObjects;
import com.example.mis.util.util;
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

        try {
            com.example.mis.util.util u = new util();
            ArrayList<T_C_Info> tCInfos = u.getT_C_Infos();
            request.setAttribute("tCInfos",tCInfos);
            request.getRequestDispatcher("/display_courses.jsp").forward(request,response);
        } catch (Exception e) {
            out.println(e);
        }
    }
}
