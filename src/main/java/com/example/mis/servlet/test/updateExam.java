package com.example.mis.servlet.test;

import com.example.mis.dao.examDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
/**
 * 修改指定考试时间信息
 */
@WebServlet(name = "updateExam",value = "/update_exam")
public class updateExam extends HttpServlet {
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

        examDataAccessObjects examDao = new examDataAccessObjects();

        String cid = request.getParameter("cid");
        String examDate = request.getParameter("exam_date");

        try {
            examDao.updateExam(cid,examDate);
        } catch (Exception e) {
            out.println(e);
        }
    }
}
