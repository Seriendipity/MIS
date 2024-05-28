package com.example.mis.servlet;

import com.example.mis.dao.TeacherDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 删除指定教师号的教师
 */
@WebServlet(name = "deleteTeacher" , value = "/delete_teacher")
public class deleteTeacher extends HttpServlet {
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

       TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();

       String teacherNo = request.getParameter("teacher_no");

        try {
            teacherDao.deleteTeacher(teacherNo);

        } catch (Exception e) {
            //TODO
            out.println(e);
        }
    }
}
