package com.example.mis.servlet;

import com.example.mis.bean.Student;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet(value = "/alter")
public class alter extends HttpServlet {
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

        if(action.equals("alter_user")){
            String userName = request.getParameter("username");
            String afterUserName = request.getParameter("after_username");
            String afterPassword = request.getParameter("after_password");
            String afterLevel = request.getParameter("after_level");
            if(afterLevel.equals("用户")){
                UserDataAccessObjects userDao = new UserDataAccessObjects();

               if(userName.length() == 8){
                   StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
                   try {
                       studentDao.deleteStudent(userName);
                       userDao.deleteUser(userName);
                   } catch (Exception e) {
                       StringBuilder html = new StringBuilder();
                       html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                       html.append("<div>");
                       html.append("<label>没有该人员信息，请重新输入</label>");
                       html.append("</div>");
                       html.append("</div>");
                       response.getWriter().write(html.toString());
                   }
               }else if(userName.length() == 4){
                   TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();
                   try {
                       teacherDao.deleteTeacher(userName);
                       userDao.deleteUser(userName);
                   } catch (Exception e) {
                       StringBuilder html = new StringBuilder();
                       html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                       html.append("<div>");
                       html.append("<label>没有该人员信息，请重新输入</label>");
                       html.append("</div>");
                       html.append("</div>");
                       response.getWriter().write(html.toString());
                   }
               }

               if(afterUserName.length() == 8){
                   StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
                   try {
                       userDao.insertUser(afterUserName,afterPassword);
                       studentDao.insertStudent(userName,"null","null","null","null","null","null","null",afterPassword);
                   } catch (Exception e) {
                       throw new RuntimeException(e);
                   }
               }else if(afterUserName.length() == 4){
                   TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();
                   try {
                       userDao.insertUser(afterUserName,afterPassword);
                       teacherDao.insertTeacher(userName,"null","null","null","null","null",afterPassword);
                   } catch (Exception e) {
                       throw new RuntimeException(e);
                   }
               }
                StringBuilder html = new StringBuilder();
                html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                html.append("<div>");
                html.append("<label>信息已保存</label>");
                html.append("</div>");
                html.append("</div>");
                response.getWriter().write(html.toString());
            }
        } else if (action.equals("alter_student")) {
            String studentNo = request.getParameter("sno");
            String afterStudentNo = request.getParameter("after_sno");
            String afterStudentName = request.getParameter("after_sname");
            String afterStudentSex = request.getParameter("after_ssex");
            String afterAge = request.getParameter("after_sage");
            String afterClno = request.getParameter("after_clno");

            Calendar calendar = Calendar.getInstance();
            int age = Integer.parseInt(afterAge);
            calendar.add(Calendar.YEAR,-age);
            Date birthDate = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String studentBirthday = dateFormat.format(birthDate);

            StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
            try {
                if(studentDao.selectFromStudentBySno(studentNo) == null){
                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                    html.append("<div>");
                    html.append("<label>未找到该学生信息，请重新输入</label>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }else{
                    Student s = studentDao.selectFromStudentBySno(studentNo);
                    studentDao.updateStudentInfo(studentNo,afterClno,afterStudentName,studentBirthday,afterStudentSex,"null","null","null",s.getPassword());
                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                    html.append("<div>");
                    html.append("<label>更改信息成功</label>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("alter_course")) {
            String courseNo = request.getParameter("cno");
            String afterCourseNo = request.getParameter("after_cno");
            String afterCname = request.getParameter("after_cname");
            String afterCcredit = request.getParameter("after_ccredit");

            CourseDataAccessObjects courseDao = new CourseDataAccessObjects();

            try {
                courseDao.updateCourse(courseNo,afterCname,afterCcredit);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            html.append("<label>更改信息成功</label>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        }

    }
}
