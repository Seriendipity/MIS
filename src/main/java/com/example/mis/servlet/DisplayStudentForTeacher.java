package com.example.mis.servlet;

import com.example.mis.bean.sc;
import com.example.mis.dao.scDataAccessObjects;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 为教师显示选修某门课的学生列表
 */
@WebServlet(name = "displayStudentForTeacher" , value = "/display_student_for_teacher")
public class DisplayStudentForTeacher extends HttpServlet {
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

        String cid = request.getParameter("cid");
        ArrayList<com.example.mis.bean.sc> scs = new ArrayList<>();
        scDataAccessObjects scDao = new scDataAccessObjects();
        try {
            scs = scDao.selectFromSCByCid(cid);
            for(sc s : scs){
                System.out.print(s.getStudentNo()+" ");
                System.out.print(s.getCourseNo()+" ");
                System.out.print(s.getCid()+" ");
                System.out.print(s.getGrade()+" ");
                System.out.println();
            }
            request.setAttribute("sc",scs);
            request.getRequestDispatcher("/display_student_for_teacher.jsp").forward(request,response);
        } catch (Exception e) {
            out.println(e);
        }

    }
}
