package com.example.mis.servlet;

import com.example.mis.bean.*;
import com.example.mis.dao.CourseDataAccessObjects;
import com.example.mis.dao.StudentDataAccessObjects;
import com.example.mis.dao.UserDataAccessObjects;
import com.example.mis.dao.scDataAccessObjects;
import com.example.mis.util.util;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 展示查询结果模块
 */
@WebServlet(value = "/query_all_user")
public class display extends HttpServlet {
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

        if(action.equals("user")){
            /**
             * 管理员查看所有用户模块
             */
            UserDataAccessObjects userDao = new UserDataAccessObjects();
            ArrayList<User> users = new ArrayList<>();
            try {
                users = userDao.selectUsers();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>用户列表</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>用户名</th>");
            html.append("<th>密码</th>");
            html.append("</tr>");

            for (User u : users) {
                html.append("<tr>");
                html.append("<td>").append(u.getUserName()).append("</td>");
                html.append("<td>").append(u.getPassword()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        }else if(action.equals("student")){
            /**
             * 管理员查看所有学生信息模块
             */
            StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
            ArrayList<Student> students = new ArrayList<>();
            try {
                students = studentDao.selectFromStudent();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>学生列表</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>学号</th>");
            html.append("<th>班级号</th>");
            html.append("<th>姓名</th>");
            html.append("<th>生日</th>");
            html.append("<th>性别</th>");
            html.append("<th>总学分</th>");
            html.append("<th>电话号</th>");
            html.append("<th>邮箱</th>");
            html.append("<th>密码</th>");
            html.append("</tr>");
            for(Student s : students){
                html.append("<tr>");
                html.append("<td>").append(s.getStudentNO()).append("</td>");
                html.append("<td>").append(s.getClassNo()).append("</td>");
                html.append("<td>").append(s.getStudentName()).append("</td>");
                html.append("<td>").append(s.getStudentBirthday()).append("</td>");
                html.append("<td>").append(s.getStudentSex()).append("</td>");
                html.append("<td>").append(s.getTotalCredit()).append("</td>");
                html.append("<td>").append(s.getPhoneNumber()).append("</td>");
                html.append("<td>").append(s.getStudentEmail()).append("</td>");
                html.append("<td>").append(s.getPassword()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        } else if (action.equals("course")) {
            /**
             * 管理员查看所有选课信息模块
             */
            com.example.mis.util.util u = new util();
            ArrayList<T_C_Info> tCInfos;
            try {
                tCInfos = u.getT_C_Infos();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //教师姓名、课程名称、课程号、课程CID、课程学分

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>课程列表</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>课程号</th>");
            html.append("<th>课程名</th>");
            html.append("<th>学分</th>");
            html.append("<th>课程CID</th>");
            html.append("<th>教师姓名</th>");
            html.append("<th>授课语言</th>");
            html.append("</tr>");

            for (T_C_Info tc : tCInfos) {
                html.append("<tr>");
                html.append("<td>").append(tc.getCourseNo()).append("</td>");
                html.append("<td>").append(tc.getCourseName()).append("</td>");
                html.append("<td>").append(tc.getCourseCredit()).append("</td>");
                html.append("<td>").append(tc.getCid()).append("</td>");
                html.append("<td>").append(tc.getTeacherName()).append("</td>");
                html.append("<td>").append(tc.getLanguage()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        } else if (action.equals("course_avg")) {
            /**
             * 管理员查看课程平均分模块
             */
            ArrayList<CourseGrade> cgs = new ArrayList<>();
           // System.out.println(1);
            try {
                cgs = new com.example.mis.util.util().getCG();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>课程平均分列表</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>课程名</th>");
            html.append("<th>平均分</th>");
            html.append("</tr>");

            for(CourseGrade cg : cgs){
                html.append("<tr>");
                html.append("<td>").append(cg.getCourseName()).append("</td>");
                html.append("<td>").append(cg.getAvgGrade()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        } else if (action.equals("fail_rate")) {
            /**
             * 管理员查看课程不及格率模块
             */
            ArrayList<CourseFail> cfs = new ArrayList<>();

            try {
                cfs = new com.example.mis.util.util().getCF();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>课程不及格率</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>课程名</th>");
            html.append("<th>不及格率</th>");
            html.append("</tr>");

            for(CourseFail cf: cfs){
                html.append("<tr>");
                html.append("<td>").append(cf.getCourseName()).append("</td>");
                html.append("<td>").append(cf.getFailRate()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        } else if (action.equals("course_ranking")) {
            /**
             * 管理员查看某一个门课程的排名情况
             */
            String courseNo = request.getParameter("cno");
            scDataAccessObjects scDao = new scDataAccessObjects();
            ArrayList<sc> scs;
            try {
                scs = scDao.selectFromSCByCno(courseNo);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>课程排名</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>学号</th>");
            html.append("<th>成绩</th>");
            html.append("<th>CID</th>");
            html.append("</tr>");

            for(sc s : scs){
                html.append("<tr>");
                html.append("<td>").append(s.getStudentNo()).append("</td>");
                html.append("<td>").append(s.getGrade()).append("</td>");
                html.append("<td>").append(s.getCid()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        } else if (action.equals("S_select_course")) {
            /**
             * 学生查看自己选课信息模块
             */
            System.out.println(1);
            String studentNo = request.getParameter("studentNo");
            System.out.println(studentNo);
            ArrayList<S_C_T_Info> sCTInfos = new ArrayList<>();
            com.example.mis.util.util u = new util();
            try {
                sCTInfos = u.getS_C_T_Infos(studentNo);

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>选课信息</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>课程名</th>");
            html.append("<th>CID</th>");
            html.append("<th>老师姓名</th>");
            html.append("<th>授课语言</th>");
            html.append("</tr>");

            for(S_C_T_Info sct : sCTInfos){
                html.append("<tr>");
                html.append("<td>").append(sct.getCourseName()).append("</td>");
                html.append("<td>").append(sct.getCid()).append("</td>");
                html.append("<td>").append(sct.getTeacherName()).append("</td>");
                html.append("<td>").append(sct.getLanguage()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        } else if (action.equals("query_self_grade")) {
            /**
             * 学生查询自己选修所有课程的成绩模块
             */
            String sno = request.getParameter("sno");
            ArrayList<sc> scs = new ArrayList<>();
            com.example.mis.dao.scDataAccessObjects scDao = new scDataAccessObjects();
            try {
                scs = scDao.selectFromSCBySno(sno);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>课程成绩</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>课程名</th>");
            html.append("<th>成绩</th>");
            html.append("</tr>");
            CourseDataAccessObjects courseDao = new CourseDataAccessObjects();
            Course c = new Course();
            for(sc s : scs){
                html.append("<tr>");
                try {
                    c = courseDao.selectFromCourseByCno(s.getCourseNo());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                html.append("<td>").append(c.getCourseName()).append("</td>");
                html.append("<td>").append(s.getGrade()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        } else if (action.equals("query_One_grade")) {
            /**
             * 学生查询自己选修的某一门课程的成绩
             */
            String sno = request.getParameter("sno");
            String cno = request.getParameter("courseNo");
            String cid = request.getParameter("cid");

            com.example.mis.dao.scDataAccessObjects scDao = new scDataAccessObjects();

            sc s = new sc();
            try {
                s = scDao.selectFromScBySnoCnoCid(sno,cno,cid);
                if(s == null){
                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                    html.append("<div>");
                    html.append("<label>您并没有选修该门课</label>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }else{
                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                    html.append("<div>");
                    html.append("<h1 style='text-align: center;'>课程成绩</h1>");
                    html.append("<table border='1' style='margin: 0 auto;'>");
                    html.append("<tr>");
                    html.append("<th>课程名</th>");
                    html.append("<th>成绩</th>");
                    html.append("</tr>");
                    CourseDataAccessObjects courseDao = new CourseDataAccessObjects();
                    Course c = new Course();
                    html.append("<tr>");
                    try {
                        c = courseDao.selectFromCourseByCno(cno);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    html.append("<td>").append(c.getCourseName()).append("</td>");
                    html.append("<td>").append(s.getGrade()).append("</td>");
                    html.append("</tr>");

                    html.append("</table>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }


}
