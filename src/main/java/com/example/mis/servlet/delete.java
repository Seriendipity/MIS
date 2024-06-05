package com.example.mis.servlet;

import com.example.mis.dao.CourseDataAccessObjects;
import com.example.mis.dao.StudentDataAccessObjects;
import com.example.mis.dao.TeacherDataAccessObjects;
import com.example.mis.dao.UserDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/delete")
public class delete extends HttpServlet {
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

        String action = request.getParameter("action");

        if(action.equals("delete_user")){
            String userName = request.getParameter("username");
            if(userName.length() == 8){
                UserDataAccessObjects userDao = new UserDataAccessObjects();
                StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
                try {
                    userDao.deleteUser(userName);
                    studentDao.deleteStudent(userName);
                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                    html.append("<div>");
                    html.append("<label>信息已保存</label>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else if(userName.length() == 4){
                UserDataAccessObjects userDao = new UserDataAccessObjects();
                TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();
                try {
                    userDao.deleteUser(userName);
                    teacherDao.deleteTeacher(userName);
                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                    html.append("<div>");
                    html.append("<label>信息已保存</label>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }else if(action.equals("delete_student")){
            String studentNo = request.getParameter("sno");
            StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            try {
                studentDao.deleteStudent(studentNo);
                html.append("<label>信息已保存</label>");
                html.append("</div>");
                html.append("</div>");
                response.getWriter().write(html.toString());
            } catch (Exception e) {
                html.append("<label>删除失败</label>");
                html.append("</div>");
                html.append("</div>");
                response.getWriter().write(html.toString());
            }
        } else if (action.equals("delete_course")) {
            String courseNo = request.getParameter("cno");

            CourseDataAccessObjects courseDao = new CourseDataAccessObjects();
            try {
                courseDao.deleteCourse(courseNo);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            html.append("<label>信息已保存</label>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        }

    }
}
