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

/**
 * 从班级表中删除指定班级号的班级
 */
@WebServlet(name = "deleteClass" ,value = "/delete_class")
public class deleteClass extends HttpServlet {
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

        ClassDataAccessObjects classDao = new ClassDataAccessObjects();

        String classNo = request.getParameter("class_no");

        try {
            classDao.deleteClass(classNo);
        } catch (Exception e) {
            //TODO
            out.println(e);
        }
    }
}
