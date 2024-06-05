package com.example.mis.servlet.test;

import com.example.mis.bean.Course;
import com.example.mis.bean.Exam;
import com.example.mis.dao.CourseDataAccessObjects;
import com.example.mis.dao.scDataAccessObjects;
import com.example.mis.dao.examDataAccessObjects;
import com.example.mis.dao.teachingDataAccessObjects;
import com.example.mis.bean.sc;
import com.example.mis.bean.teaching;

import com.mysql.cj.conf.ConnectionUrlParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 显示考试安排
 */
@WebServlet(name = "displayExamInfo",value = "/display_exam")
public class DisplayExamInfo extends HttpServlet {
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
        scDataAccessObjects scDao = new scDataAccessObjects();

        String studentNo = request.getParameter("student_no");
        try {
            ArrayList<sc> scs = scDao.selectFromSCBySno(studentNo);//获取指定学号的选课信息
            ArrayList<String> courseNo = new ArrayList<>();//获取该同学选课的课程名
            ArrayList<String> cid = new ArrayList<>();//获取该同学的选课的ID
            for (sc s : scs) {
                cid.add(s.getCid());
                courseNo.add((s.getCourseNo()));
            }
            ArrayList<Exam> exams = new ArrayList<>();//获取该同学的考试信息

            for(String c : cid){
                Exam e = examDao.selectExamByCid(c);
                exams.add(e);
            }

            ArrayList<String> courseName = new ArrayList<>();
            CourseDataAccessObjects courseDao = new CourseDataAccessObjects();
            for(String course : courseNo){
                courseName.add(courseDao.selectFromCourseByCno(course).getCourseName());
            }


            request.setAttribute("exams",exams);
            request.setAttribute("courseNames",courseName);
            request.getRequestDispatcher("/display_exam.jsp").forward(request,response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
