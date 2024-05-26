package com.example.mis.servlet;

import com.example.mis.dao.StudentDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "updateStudent" ,value = "/update_student")
public class updateStudent extends HttpServlet {
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

        StudentDataAccessObjects studentDao = new StudentDataAccessObjects();

        String studentNo = request.getParameter("student_no");
        String studentName = request.getParameter("student_Name");
        String classNo = request.getParameter("class_no");
        String studentBirthday = request.getParameter("student_birthday");
        String studentSex = request.getParameter("student_sex");
        String totalCredit = request.getParameter("total_credit");
        String phoneNumber = request.getParameter("phone_number");
        String studentEmail = request.getParameter("student_email");

        try {
            studentDao.updateStudentInfo(studentNo,studentName,classNo,studentBirthday,
                                        studentSex,totalCredit,phoneNumber,studentEmail);
        } catch (Exception e) {
            out.println(e);
        }
    }
}
