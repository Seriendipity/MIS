package com.example.mis.servlet.test;

import com.example.mis.dao.scDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
/**
 * 修改指定学生选课信息（学生成绩）
 */
@WebServlet(name = "updateSc" , value = "/update_sc")
public class updateSc extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        scDataAccessObjects scDao = new scDataAccessObjects();

        String studentNo = request.getParameter("student_no");
        String courseNo = request.getParameter("course_no");
        String grade = request.getParameter("grade");
        String cid = request.getParameter("cid");

        try {
            scDao.updateSc(studentNo,courseNo,grade,cid);
        } catch (Exception e) {
            out.println(e);
        }

    }
}
