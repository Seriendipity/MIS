package com.example.mis.servlet;

import com.example.mis.dao.teachingDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
/**
 * 修改指定教师授课信息
 */
@WebServlet(name = "updateTeaching",value = "/update_teaching")
public class updateTeaching extends HttpServlet {
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

        teachingDataAccessObjects teachingDao = new teachingDataAccessObjects();

        String teacherNo = request.getParameter("teacher_no");
        String courseNo = request.getParameter("course_no");
        String language = request.getParameter("language");
        String cid = request.getParameter("cid");
        System.out.println(teacherNo);
        System.out.println(courseNo);
        System.out.println(language);
        System.out.println(cid);
        try {
            teachingDao.updateTeaching(courseNo,teacherNo,language,cid);
        } catch (Exception e) {
            out.println(e);
        }
    }
}
