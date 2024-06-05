package com.example.mis.servlet.test;

import com.example.mis.dao.TeacherDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
/**
 * 修改指定教师信息
 */
@WebServlet(name = "updateTeacher" , value = "/update_teacher")
public class updateTeacher extends HttpServlet {
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
        String teacherName = request.getParameter("teacher_name");
        String teacherSex = request.getParameter("teacher_sex");
        String teacherBirthday = request.getParameter("teacher_birthday");
        String teacherTitle = request.getParameter("teacher_title");
        String teacherEmail = request.getParameter("teacher_email");
        String password = request.getParameter("password");

        try {
            teacherDao.updateTeacher(teacherNo,teacherName,teacherSex,
                                     teacherBirthday, teacherTitle,
                                    teacherEmail,password);
        } catch (Exception e) {
            out.println(e);
        }
    }
}
