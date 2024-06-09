package com.example.mis.servlet;

import com.example.mis.bean.*;
import com.example.mis.bean.Class;
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
                        teacherDao.insertTeacher(userName,"null","男","null","null",userName+"@bjtu.edu.cn",password);
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
        } else if (action.equals("insert_class")) {
            /**
             * 管理员插入班级信息模块
             */
            String classNo = request.getParameter("clno");
            String className = request.getParameter("clname");
            String classMajor = request.getParameter("cmajor");
            String classDept = request.getParameter("dno");

            System.out.println(classMajor);
            ClassDataAccessObjects classDao = new ClassDataAccessObjects();

            try {
                classDao.insertClass(classNo,className,classMajor,classDept,"0");
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

        } else if(action.equals("insert_student")){
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

            ClassDataAccessObjects classDao = new ClassDataAccessObjects();

            String password;
            try {
                password = "Bjtu@123456";
                if(studentDao.selectFromStudentBySno(studentNo) != null){
                    studentDao.deleteStudent(studentNo);
                }

                Class c = classDao.selectFromClassByCno(classNo);
                String className = c.getClassName();
                String classMajor = c.getClassMajor();
                String classDept = c.getClassDept();
                int studentNumber =Integer.parseInt(c.getStudentNumber());
                studentNumber++;
                String newStudentNumber = String.valueOf(studentNumber);
                studentDao.insertStudent(studentNo,classNo,studentName,studentBirthday,studentSex,"0","null",studentNo+"@bjtu.edu.cn",password);
                classDao.updateClassInfo(classNo,className,classMajor,classDept,newStudentNumber);
                new UserDataAccessObjects().insertUser(studentNo,password);
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
        } else if (action.equals("insert_teacher")) {
            String teacherNo = request.getParameter("tno");
            String teacherName = request.getParameter("tname");
            String teacherSex = request.getParameter("tsex");
            String teacherAge = request.getParameter("tage");
            String teacherTitle = request.getParameter("title");

            Calendar calendar = Calendar.getInstance();
            int age = Integer.parseInt(teacherAge);
            calendar.add(Calendar.YEAR,-age);
            Date birthDate = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String teacherBirthday = dateFormat.format(birthDate);

            TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();
            Teacher t = new Teacher();

            try {
                t = teacherDao.selectFromTeacherByTno(teacherNo);
                if(t != null){
                    teacherDao.updateTeacher(teacherNo,teacherName,teacherSex,teacherBirthday,teacherTitle,t.getTeacherEmail(),t.getPassword());
                }else{
                    teacherDao.insertTeacher(teacherNo,teacherName,teacherSex,teacherBirthday,teacherTitle,teacherNo+"@bjtu.edu.cn","Bjtu@teacher");
                    new UserDataAccessObjects().insertUser(teacherNo,"Bjtu@teacher");
                }
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
        } else if(action.equals("insert_course")){
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
        } else if (action.equals("insert_teaching")) {
            /**
             * 管理员插入任课信息模块
             */
            String courseNo = request.getParameter("courseNo");
            String teacherNo = request.getParameter("teacherNo");
            String language = request.getParameter("language");
            String cid = request.getParameter("cid");

            teachingDataAccessObjects teachingDao = new teachingDataAccessObjects();

            try {
                teachingDao.insertTeaching(courseNo,teacherNo,language,cid);
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
        } else if (action.equals("insert_sc")) {
            /**
             * 用于教师插入学生成绩信息
             */
            String sno = request.getParameter("sno");
            String cid = request.getParameter("cno");
            String grade = request.getParameter("grade");
            String courseNo;
            scDataAccessObjects scDao = new scDataAccessObjects();
            teachingDataAccessObjects teachingDao = new teachingDataAccessObjects();
            com.example.mis.bean.teaching teaching = new teaching();

            try {
                teaching = teachingDao.selectFromTeachingByCid(cid);
                courseNo = teaching.getCourseNo();

                if(scDao.selectFromScBySnoCnoCid(sno,courseNo,cid) != null) scDao.deleteSC(sno,courseNo,cid);
                scDao.insertSC(sno,courseNo,grade,cid);

                int s_grade = Integer.parseInt(grade);
                if(s_grade >= 60){
                    StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
                    Student s = new Student();
                    s = studentDao.selectFromStudentBySno(sno);

                    CourseDataAccessObjects courseDao = new CourseDataAccessObjects();
                    Course c = new Course();
                    c = courseDao.selectFromCourseByCno(teaching.getCourseNo());

                    int beforeCredit = Integer.parseInt(s.getTotalCredit());
                    beforeCredit += Integer.parseInt(c.getCourseCredit());
                    String afterCredit = String.valueOf(beforeCredit);
                    studentDao.updateStudentInfo(sno,s.getClassNo(),s.getStudentName(),s.getStudentBirthday(),s.getStudentSex(),afterCredit,s.getPhoneNumber(),s.getStudentEmail(),s.getPassword());
                }
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
