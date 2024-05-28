package com.example.mis.servlet;

import com.example.mis.dao.EvaluationDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 从评教表中删除指定学生号、教师号、课程号的评教信息
 */
@WebServlet(name = "deleteEvaluation",value = "/delete_evaluation")
public class deleteEvaluation extends HttpServlet {
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

        EvaluationDataAccessObjects evaluationDao = new EvaluationDataAccessObjects();

        String studentNo = request.getParameter("student_no");
        String courseNo = request.getParameter("course_no");
        String teacherNo = request.getParameter("teacher_no");

        try {
            evaluationDao.deleteEvaluation(studentNo,courseNo,teacherNo);
        } catch (Exception e) {
            //TODO
            out.println(e);
        }
    }
}

