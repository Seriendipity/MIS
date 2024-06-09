package com.example.mis.servlet;

import com.example.mis.bean.Student;
import com.example.mis.bean.teaching;
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
 * 修改信息的方法
 */
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

        if(action.equals("alter_user")){//管理员界面修改用户信息
            //获取四个url携带的参数
            String userName = request.getParameter("username");
            String afterUserName = request.getParameter("after_username");
            String afterPassword = request.getParameter("after_password");
            String afterLevel = request.getParameter("after_level");
            if(afterLevel.equals("用户")){//判断用户类型，如果是用户，那么进行用户身份判断
                UserDataAccessObjects userDao = new UserDataAccessObjects();

               if(userName.length() == 8){//如果是学生，那么删除一个User的同时，在Student表中删除该学生
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
               }else if(userName.length() == 4){//如果是教师，在User表中删除一个用户，在Teacher表中删除该教师
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

               if(afterUserName.length() == 8){//判断修改之后的用户名，如果是学生，那么新插入一个User，并在Student中插入一个元组
                   StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
                   try {
                       userDao.insertUser(afterUserName,afterPassword);
                       studentDao.insertStudent(userName,"null","null","null","null","null","null","null",afterPassword);
                   } catch (Exception e) {
                       throw new RuntimeException(e);
                   }
               }else if(afterUserName.length() == 4){//如果是教师，那么插入一个User，并在Teacher表中插入一行元组
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
        } else if (action.equals("alter_student")) {//管理员界面修改学生信息
            String studentNo = request.getParameter("sno");//修改前的学号
            String afterStudentNo = request.getParameter("after_sno");//修改后的学号我认为不变，这里没进行修改
            String afterStudentName = request.getParameter("after_sname");//修改之后的学生姓名
            String afterStudentSex = request.getParameter("after_ssex");//修改之后的学生性别
            String afterAge = request.getParameter("after_sage");//修改之后的学生年龄
            String afterClno = request.getParameter("after_clno");//修改之后的班级信息
            String afterPassword = request.getParameter("after_password");//修改之后的密码
            //由于表中存放的是学生的生日是Date类型，这里进行一个转换
            Calendar calendar = Calendar.getInstance();
            int age = Integer.parseInt(afterAge);
            calendar.add(Calendar.YEAR,-age);
            Date birthDate = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String studentBirthday = dateFormat.format(birthDate);
            //修改学生信息
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
            //用于管理员修改课程信息
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
        }else if (action.equals("alter_teacher")) {
            String tno = request.getParameter("tno");
            String after_tname = request.getParameter("after_tname");
            String after_age = request.getParameter("after_age");

            //由于表中存放的是教师的生日是Date类型，这里进行一个转换
            Calendar calendar = Calendar.getInstance();
            int age = Integer.parseInt(after_age);
            calendar.add(Calendar.YEAR,-age);
            Date birthDate = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String teacherBirthday = dateFormat.format(birthDate);

            TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();

            try {
                teacherDao.updateTeacher(tno,after_tname,"男",teacherBirthday,"教授","Bjtu@1234.com","Bjtu@teacher");
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

        } else if (action.equals("alter_sc")) {
            String sno = request.getParameter("sno");
            String cid = request.getParameter("cno");
            String courseNo;
            String newGrade = request.getParameter("after_grade");

            com.example.mis.dao.scDataAccessObjects scDao = new scDataAccessObjects();
           teachingDataAccessObjects teachingDao = new teachingDataAccessObjects();
            com.example.mis.bean.teaching t = new teaching();

            try {
                t = teachingDao.selectFromTeachingByCid(cid);
                courseNo = t.getCourseNo();
                scDao.updateSc(sno,courseNo,newGrade,cid);
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
