package com.example.mis.servlet;

import com.example.mis.bean.*;
import com.example.mis.bean.Class;
import com.example.mis.dao.*;
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
            html.append("<div style='display: block;" +
                        "justify-content: center;" +
                        "align-items: center; " +
                        "height: 195px;" +
                        "border:0;"+
                        "border-spacing:0;"+
                        "border-collapse:collapse;"+
                        "cursor:default"+
                        "'>");
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
        } else if (action.equals("class")) {
            ClassDataAccessObjects classDao = new ClassDataAccessObjects();
            ArrayList<Class> classes = new ArrayList<>();
            try {
                classes = classDao.selectFromClass();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            StringBuilder html = new StringBuilder();
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>班级信息</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>班级号</th>");
            html.append("<th>班级名</th>");
            html.append("<th>系名</th>");
            html.append("<th>专业名</th>");
            html.append("<th>班级人数</th>");
            html.append("</tr>");
            
            for(Class c : classes){
                html.append("<tr>");
                html.append("<td>").append(c.getClassNo()).append("</td>");
                html.append("<td>").append(c.getClassName()).append("</td>");
                html.append("<td>").append(c.getClassMajor()).append("</td>");
                html.append("<td>").append(c.getClassDept()).append("</td>");
                html.append("<td>").append(c.getStudentNumber()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        } else if(action.equals("student")){
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
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
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
        } else if (action.equals("teacher")) {
            ArrayList<Teacher> teachers = new ArrayList<>();
            TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();

            try {
                teachers = teacherDao.selectFromTeacher();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>教师列表</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>教师号</th>");
            html.append("<th>姓名</th>");
            html.append("<th>生日</th>");
            html.append("<th>性别</th>");
            html.append("<th>职称</th>");
            html.append("<th>邮箱</th>");
            html.append("<th>密码</th>");
            html.append("</tr>");

            for(Teacher t : teachers){
                html.append("<tr>");
                html.append("<td>").append(t.getTeacherNo()).append("</td>");
                html.append("<td>").append(t.getTeacherName()).append("</td>");
                html.append("<td>").append(t.getTeacherBirthday()).append("</td>");
                html.append("<td>").append(t.getTeacherSex()).append("</td>");
                html.append("<td>").append(t.getTeacherTitle()).append("</td>");
                html.append("<td>").append(t.getTeacherEmail()).append("</td>");
                html.append("<td>").append(t.getPassword()).append("</td>");
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
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
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
        } else if (action.equals("courses")) {
            /**
             * 管理员查看所有课程信息模块
             */
            CourseDataAccessObjects courseDao = new CourseDataAccessObjects();
            ArrayList<Course> courses = new ArrayList<>();

            try {
                courses = courseDao.selectFromCourse();

                StringBuilder html = new StringBuilder();
                html.append("<div style='display: block;" +
                        "justify-content: center;" +
                        "align-items: center; " +
                        "height: 195px;" +
                        "border:0;"+
                        "border-spacing:0;"+
                        "border-collapse:collapse;"+
                        "cursor:default"+
                        "'>");
                html.append("<div>");
                html.append("<h1 style='text-align: center;'>课程列表</h1>");
                html.append("<table border='1' style='margin: 0 auto;'>");
                html.append("<tr>");
                html.append("<th>课程号</th>");
                html.append("<th>课程名</th>");
                html.append("<th>学分</th>");
                html.append("</tr>");

                for(Course c : courses){
                    html.append("<tr>");
                    html.append("<td>").append(c.getCourseNo()).append("</td>");
                    html.append("<td>").append(c.getCourseName()).append("</td>");
                    html.append("<td>").append(c.getCourseCredit()).append("</td>");
                    html.append("</tr>");
                }
                html.append("</table>");
                html.append("</div>");
                html.append("</div>");
                response.getWriter().write(html.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("course_avg")) {
            /**
             * 管理员查看课程平均分模块、教师查看均分
             */
            String tno = request.getParameter("teacher_no");
            ArrayList<CourseGrade> cgs = new ArrayList<>();
            try {
                if(tno == null) cgs = new com.example.mis.util.util().getCG();//url中没有tno，即管理员查看课程平均分
                else cgs = new com.example.mis.util.util().getCGWithTeacherNo(tno);//url中有tno，即教师查看，只能查看自己教授课程的均分
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
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
             * 管理员查看课程不及格率模块、教师查看不及格率模块
             */
            ArrayList<CourseFail> cfs = new ArrayList<>();
            String tno = request.getParameter("teacher_no");
            try {
                if(tno == null) cfs = new com.example.mis.util.util().getCF();//url中没有tno，管理员查看
                else cfs = new com.example.mis.util.util().getCFWithTno(tno);//url中有tno，教师只能查看自己教授课程均分
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
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
             * 管理员查看某一个门课程的排名情况、教师查看某一门课程的排名情况
             */
            String courseNo = request.getParameter("cno");
            String cid = request.getParameter("cid");
            String teacherNo = request.getParameter("teacher_no");

            scDataAccessObjects scDao = new scDataAccessObjects();
            ArrayList<sc> scs = new ArrayList<>();
            ArrayList<teaching> teachings;
            ArrayList<String> strings = new ArrayList<>();
            try {
                if(cid==null) scs = scDao.selectFromSCByCno(courseNo);//cid为null，管理员查看课程排名情况
                else {//cid不为空，首先检查教师教授哪些课程，判断输入的cid是否在教师教授的cid中，如果在，显示，不在，返回。
                    teachings = new teachingDataAccessObjects().selectFromTeachingByTno(teacherNo);
                    for(teaching t : teachings){
                        strings.add(t.getCid());
                    }
                    if(strings.contains(cid)){
                        scs = scDao.selectFromSCByCid(cid);
                    }else{
                        StringBuilder html = new StringBuilder();
                        html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                        html.append("<div>");
                        html.append("<label>您没有教授该门课</label>");
                        html.append("</div>");
                        html.append("</div>");
                        response.getWriter().write(html.toString());
                        return;
                    }
                }

                StringBuilder html = new StringBuilder();
                html.append("<div style='display: block;" +
                        "justify-content: center;" +
                        "align-items: center; " +
                        "height: 195px;" +
                        "border:0;"+
                        "border-spacing:0;"+
                        "border-collapse:collapse;"+
                        "cursor:default"+
                        "'>");
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
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } else if (action.equals("sc")) {
            /**
             * 管理员查看所有的学生信息
             */
            scDataAccessObjects scDao = new scDataAccessObjects();
            ArrayList<sc> scs = new ArrayList<>();

            try {
                scs = scDao.selectFromSC();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>学生成绩信息</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>学号</th>");
            html.append("<th>课程号</th>");
            html.append("<th>cid</th>");
            html.append("<th>成绩</th>");
            html.append("</tr>");

            for(sc s : scs){
                html.append("<tr>");
                html.append("<td>").append(s.getStudentNo()).append("</td>");
                html.append("<td>").append(s.getCourseNo()).append("</td>");
                html.append("<td>").append(s.getCid()).append("</td>");
                html.append("<td>").append(s.getGrade()).append("</td>");
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
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
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
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
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
                    html.append("<div style='display: block;" +
                            "justify-content: center;" +
                            "align-items: center; " +
                            "height: 195px;" +
                            "border:0;"+
                            "border-spacing:0;"+
                            "border-collapse:collapse;"+
                            "cursor:default"+
                            "'>");
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
        } else if (action.equals("teacher_Info")) {
            String teacherNo = request.getParameter("teacherNo");
            TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();
            Teacher t =  new Teacher();
            try {
                t = teacherDao.selectFromTeacherByTno(teacherNo);
                if(t != null){
                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: block;" +
                            "justify-content: center;" +
                            "align-items: center; " +
                            "height: 195px;" +
                            "border:0;"+
                            "border-spacing:0;"+
                            "border-collapse:collapse;"+
                            "cursor:default"+
                            "'>");
                    html.append("<div>");
                    html.append("<h1 style='text-align: center;'>教师信息</h1>");
                    html.append("<table border='1' style='margin: 0 auto;'>");
                    html.append("<tr>");
                    html.append("<th>教师号</th>");
                    html.append("<th>教师姓名</th>");
                    html.append("<th>教师性别</th>");
                    html.append("<th>教师生日</th>");
                    html.append("<th>教师职称</th>");
                    html.append("<th>教师邮箱</th>");
                    html.append("</tr>");

                    html.append("<tr>");
                    html.append("<td>").append(t.getTeacherNo()).append("</td>");
                    html.append("<td>").append(t.getTeacherName()).append("</td>");
                    html.append("<td>").append(t.getTeacherSex()).append("</td>");
                    html.append("<td>").append(t.getTeacherBirthday()).append("</td>");
                    html.append("<td>").append(t.getTeacherTitle()).append("</td>");
                    html.append("<td>").append(t.getTeacherEmail()).append("</td>");
                    html.append("</tr>");

                    html.append("</table>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if(action.equals("select_teaching_course")){
            String tno = request.getParameter("teacherNo");
            System.out.println(tno);
            teachingDataAccessObjects teachingDao = new teachingDataAccessObjects();
            ArrayList<teaching> teachings = new ArrayList<>();

            try {
                teachings = teachingDao.selectFromTeachingByTno(tno);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>教授课程</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>课程名</th>");
            html.append("<th>课程号</th>");
            html.append("<th>授课语言</th>");
            html.append("<th>cid</th>");
            html.append("</tr>");
            Course c = new Course();
            CourseDataAccessObjects courseDao = new CourseDataAccessObjects();
            for(teaching t : teachings){
                html.append("<tr>");
                try {
                    c = courseDao.selectFromCourseByCno(t.getCourseNo());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                html.append("<td>").append(c.getCourseName()).append("</td>");
                html.append("<td>").append(t.getCourseNo()).append("</td>");
                html.append("<td>").append(t.getLanguage()).append("</td>");
                html.append("<td>").append(t.getCid()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        }else if(action.equals("query_student_name")){
            String cid = request.getParameter("cid");
            System.out.println(cid);
            String tno = request.getParameter("teacherNo");

            scDataAccessObjects scDao = new scDataAccessObjects();
            ArrayList<sc> scs = new ArrayList<>();

            teachingDataAccessObjects t = new teachingDataAccessObjects();

            try {
                String teacherNo = t.selectFromTeachingByCid(cid).getTeacherNo();
                System.out.println(teacherNo);
                if(teacherNo.equals(tno)){
                    scs = scDao.selectFromSCByCid(cid);

                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: block;" +
                            "justify-content: center;" +
                            "align-items: center; " +
                            "height: 195px;" +
                            "border:0;"+
                            "border-spacing:0;"+
                            "border-collapse:collapse;"+
                            "cursor:default"+
                            "'>");
                    html.append("<div>");
                    html.append("<h1 style='text-align: center;'>选修本门课程的学生</h1>");
                    html.append("<table border='1' style='margin: 0 auto;'>");
                    html.append("<tr>");
                    html.append("<th>学号</th>");
                    html.append("<th>姓名</th>");
                    html.append("</tr>");

                    Student student = new Student();
                    StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
                    for(sc s : scs){
                        html.append("<tr>");
                        try {
                            student = studentDao.selectFromStudentBySno(s.getStudentNo());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        html.append("<td>").append(s.getStudentNo()).append("</td>");
                        html.append("<td>").append(student.getStudentName()).append("</td>");
                        html.append("</tr>");
                    }
                    html.append("</table>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }else{
                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                    html.append("<div>");
                    html.append("<label>错误的cid</label>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } else if (action.equals("selected_course_grade")) {
            String cid = request.getParameter("cno");

            scDataAccessObjects scDao = new scDataAccessObjects();
            ArrayList<sc> scs = new ArrayList<>();
            try {
                scs = scDao.selectFromSCByCid(cid);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            StringBuilder html = new StringBuilder();
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>本门课程成绩</h1>");
            html.append("<table border='1' style='margin: 0 auto;'>");
            html.append("<tr>");
            html.append("<th>学号</th>");
            html.append("<th>姓名</th>");
            html.append("<th>成绩</th>");
            html.append("</tr>");
            Student student = new Student();
            StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
            for(sc s : scs){
                html.append("<tr>");
                try {
                   student = studentDao.selectFromStudentBySno(s.getStudentNo());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                html.append("<td>").append(s.getStudentNo()).append("</td>");
                html.append("<td>").append(student.getStudentName()).append("</td>");
                html.append("<td>").append(s.getGrade()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        } else if (action.equals("astudent")) {
            String sno = request.getParameter("studentNo");

            StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
            Student s = new Student();
            try {
                s = studentDao.selectFromStudentBySno(sno);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: block;" +
                    "justify-content: center;" +
                    "align-items: center; " +
                    "height: 195px;" +
                    "border:0;"+
                    "border-spacing:0;"+
                    "border-collapse:collapse;"+
                    "cursor:default"+
                    "'>");
            html.append("<div>");
            html.append("<h1 style='text-align: center;'>学生信息</h1>");
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

            html.append("<td>").append(s.getStudentNO()).append("</td>");
            html.append("<td>").append(s.getClassNo()).append("</td>");
            html.append("<td>").append(s.getStudentName()).append("</td>");
            html.append("<td>").append(s.getStudentBirthday()).append("</td>");
            html.append("<td>").append(s.getStudentSex()).append("</td>");
            html.append("<td>").append(s.getTotalCredit()).append("</td>");
            html.append("<td>").append(s.getPhoneNumber()).append("</td>");
            html.append("<td>").append(s.getStudentEmail()).append("</td>");
            html.append("<td>").append(s.getPassword()).append("</td>");

            html.append("</table>");
            html.append("</div>");
            html.append("</div>");
            response.getWriter().write(html.toString());
        }

    }


}
