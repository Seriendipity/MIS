package com.example.mis.servlet.test;

import com.example.mis.bean.S_C_Info;
import com.example.mis.bean.S_C_T_Info;
import com.example.mis.util.util;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 显示某学号同学的选课列表
 */
@WebServlet(name = "displaySelectedCourse" , value = "/display_selectedCourse")
public class DisplaySelectedCourse extends HttpServlet {
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

        String student_no = request.getParameter("student_no");

        try {
            ArrayList<S_C_T_Info> sCTInfos = new ArrayList<>();
            com.example.mis.util.util u = new util();
            sCTInfos = u.getS_C_T_Infos(student_no);
            request.setAttribute("sCTInfos", sCTInfos);
            request.getRequestDispatcher("/display_selectedCourse.jsp").forward(request, response);
        } catch (Exception e) {
            out.println(e);
        }
    }
}
