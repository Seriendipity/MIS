package com.example.mis.servlet;

import com.example.mis.bean.Student;
import com.example.mis.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 增加信息模块
 */
@WebServlet(value = "/insert")
public class insert extends HttpServlet {
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

        System.out.println(action);
        if(action.equals("insert_user")){
            /**
             * 管理员插入用户信息模块
             */
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String level = request.getParameter("level");
            if(level.equals("用户")){
                if(userName.length() == 8){
                    UserDataAccessObjects userDao = new UserDataAccessObjects();
                    StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
                    try {
                        userDao.insertUser(userName,password);
                        System.out.println(studentDao.insertStudent(userName,"null","null","null","null","null","null","null",password));
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
                        userDao.insertUser(userName,password);
                        teacherDao.insertTeacher(userName,"null","null","null","null","null",password);
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
            }
        }else if(action.equals("insert_student")){
            /**
             * 管理员插入学生信息模块
             */
            String studentNo = request.getParameter("sno");
            String classNo = request.getParameter("clno");
            String studentName = request.getParameter("sname");
            String studentAge = request.getParameter("sage");
            String studentSex = request.getParameter("ssex");
            StudentDataAccessObjects studentDao = new StudentDataAccessObjects();

            Calendar calendar = Calendar.getInstance();
            int age = Integer.parseInt(studentAge);
            calendar.add(Calendar.YEAR,-age);
            Date birthDate = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String studentBirthday = dateFormat.format(birthDate);

            System.out.println(studentBirthday);
            String password;
            try {
                password = "Bjtu@123456";
                if(studentDao.selectFromStudentBySno(studentNo) != null){
                    studentDao.deleteStudent(studentNo);
                }
                studentDao.insertStudent(studentNo,classNo,studentName,studentBirthday,studentSex,"null","null","null",password);
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
        }else if(action.equals("insert_course")){
            /**
             * 管理员插入课程信息模块
             */
            String courseNo = request.getParameter("cno");
            String courseName = request.getParameter("cname");
            String courseCredit = request.getParameter("ccredit");

            CourseDataAccessObjects courseDao = new CourseDataAccessObjects();

            try {
                courseDao.insertCourse(courseNo,courseName,courseCredit);
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
        } else if (action.equals("insert_scInfo")) {
            /**
             * 学生选课模块
             */
            System.out.println(3);
            String courseNo = request.getParameter("courseNo");
            String cid = request.getParameter("cid");
            String studentNo = request.getParameter("sno");

            System.out.println(courseNo);
            System.out.println(cid);
            System.out.println(studentNo);
            com.example.mis.dao.scDataAccessObjects scDao = new scDataAccessObjects();
            try {
                scDao.insertSC(studentNo,courseNo,"null",cid);
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
