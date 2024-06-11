package com.example.mis.servlet;

import com.example.mis.bean.Class;
import com.example.mis.bean.Student;
import com.example.mis.bean.Teacher;
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
        } else if (action.equals("alter_class")) {
            String classNo = request.getParameter("clno");
            String afterClassName = request.getParameter("after_clname");
            String afterClassDept = request.getParameter("after_dno");

            ClassDataAccessObjects classDao = new ClassDataAccessObjects();
            Class c = new Class();

            try {
                c = classDao.selectFromClassByCno(classNo);
                System.out.println(c.getClassName());
                System.out.println(c.getClassMajor());
                classDao.updateClassInfo(classNo,afterClassName,c.getClassMajor(),afterClassDept,c.getStudentNumber());
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
        } else if (action.equals("alter_student")) {//管理员界面修改学生信息
            String studentNo = request.getParameter("sno");//修改前的学号
            String afterStudentNo = request.getParameter("after_sno");//修改后的学号我认为不变，这里没进行修改
            String afterStudentName = request.getParameter("after_sname");//修改之后的学生姓名
            String afterStudentSex = request.getParameter("after_ssex");//修改之后的学生性别
            String afterAge = request.getParameter("after_sage");//修改之后的学生年龄
            String afterClno = request.getParameter("after_clno");//修改之后的班级信息

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
                    String beforeClassNo = s.getClassNo();

                    System.out.println(beforeClassNo);

                    if(beforeClassNo != null){
                        ClassDataAccessObjects classDao = new ClassDataAccessObjects();
                        Class c = classDao.selectFromClassByCno(beforeClassNo);
                        String className = c.getClassName();
                        String classMajor = c.getClassMajor();
                        String classDept = c.getClassDept();
                        int studentNumber =Integer.parseInt(c.getStudentNumber());
                        studentNumber--;
                        String newStudentNumber = String.valueOf(studentNumber);

                        classDao.updateClassInfo(beforeClassNo,className,classMajor,classDept,newStudentNumber);

                        c = classDao.selectFromClassByCno(afterClno);
                        className = c.getClassName();
                        classMajor = c.getClassMajor();
                        classDept = c.getClassDept();
                        studentNumber = Integer.parseInt(c.getStudentNumber());
                        studentNumber++;
                        newStudentNumber = String.valueOf(studentNumber);
                        classDao.updateClassInfo(afterClno,className,classMajor,classDept,newStudentNumber);
                        studentDao.updateStudentInfo(studentNo, afterClno, afterStudentName, studentBirthday, afterStudentSex, s.getTotalCredit(),"null", studentNo+"@bjtu.edu.cn", s.getPassword());
                    }else{
                        ClassDataAccessObjects classDao = new ClassDataAccessObjects();
                        Class c = classDao.selectFromClassByCno(afterClno);
                        int studentNumber =Integer.parseInt(c.getStudentNumber());
                        studentNumber++;
                        System.out.println(afterClno);
                        System.out.println(c.getClassName());
                        System.out.println(c.getClassMajor());
                        System.out.println(c.getStudentNumber());
                        System.out.println(c.getClassDept());
                        classDao.updateClassInfo(afterClno,c.getClassName(),c.getClassMajor(),c.getClassDept(),String.valueOf(studentNumber));
                        studentDao.updateStudentInfo(studentNo,afterClno,afterStudentName,studentBirthday,afterStudentSex,"0","null",studentNo+"@bjtu.edu.cn","Bjtu@123456");
                    }


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
        } else if (action.equals("alter_students")) {
            String studentName = request.getParameter("sname");
            String studentNo = request.getParameter("sno");
            String studentSex = request.getParameter("ssex");
            String studentPhoneNumber = request.getParameter("pNumber");
            String after_password = request.getParameter("after_password");
            String after_age = request.getParameter("after_sage");

            Calendar calendar = Calendar.getInstance();
            int age = Integer.parseInt(after_age);
            calendar.add(Calendar.YEAR,-age);
            Date birthDate = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String studentBirthday = dateFormat.format(birthDate);

            StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
            try {
                Student s = studentDao.selectFromStudentBySno(studentNo);
                studentDao.updateStudentInfo(studentNo,s.getClassNo(),studentName,studentBirthday,studentSex,s.getTotalCredit(),studentPhoneNumber,s.getStudentEmail(),after_password);
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
            String after_age = request.getParameter("after_tage");
            String after_sex = request.getParameter("after_tsex");
            String after_title = request.getParameter("after_ttitle");

            String email,password;
            //由于表中存放的是教师的生日是Date类型，这里进行一个转换
            Calendar calendar = Calendar.getInstance();
            int age = Integer.parseInt(after_age);
            calendar.add(Calendar.YEAR,-age);
            Date birthDate = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String teacherBirthday = dateFormat.format(birthDate);

            TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();

            try {
                Teacher t = new Teacher();
                t = teacherDao.selectFromTeacherByTno(tno);
                email = t.getTeacherEmail();
                password = t.getPassword();
                teacherDao.updateTeacher(tno,after_tname,after_sex,teacherBirthday,after_title,email,password);
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

        } else if (action.equals("alter_teachers")) {
            String tno = request.getParameter("tno");
            String after_tname = request.getParameter("after_tname");
            String after_age = request.getParameter("after_age");

            String email,password;
            //由于表中存放的是教师的生日是Date类型，这里进行一个转换
            Calendar calendar = Calendar.getInstance();
            int age = Integer.parseInt(after_age);
            calendar.add(Calendar.YEAR,-age);
            Date birthDate = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String teacherBirthday = dateFormat.format(birthDate);

            TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();

            try {
                Teacher t = new Teacher();
                t = teacherDao.selectFromTeacherByTno(tno);
                String sex = t.getTeacherSex();
                String title = t.getTeacherTitle();
                email = t.getTeacherEmail();
                password = t.getPassword();
                teacherDao.updateTeacher(tno,after_tname,sex,teacherBirthday,title,tno+"@bjtu.edu.cn",password);
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

        } else if (action.equals("alter_teaching")) {
            /**
             * 用于管理员修改任课信息
             */
            String cid = request.getParameter("cid");
            String courseNo = request.getParameter("courseNo");
            String teacherNo = request.getParameter("teacherNo");
            String language = request.getParameter("language");

            teachingDataAccessObjects teachingDao = new teachingDataAccessObjects();

            teaching t = new teaching();
            try {
                t = teachingDao.selectFromTeachingByCid(cid);
                if(t == null){
                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                    html.append("<div>");
                    html.append("<label>没有该cid的课程，修改失败</label>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }else{
                    teachingDao.updateTeaching(courseNo,teacherNo,language,cid);
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
        } else if (action.equals("alter_teacher")) {
            String teacherNo = request.getParameter("tno");
            String teacherName = request.getParameter("after_tname");
            String teacherSex = request.getParameter("after_tsex");
            String teacherAge = request.getParameter("after_tage");
            String teacherTitle = request.getParameter("after_ttitle");

            Calendar calendar = Calendar.getInstance();
            int age = Integer.parseInt(teacherAge);
            calendar.add(Calendar.YEAR,-age);
            Date birthDate = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String teacherBirthday = dateFormat.format(birthDate);

            Teacher t = new Teacher();
            TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();

            try {
                t = teacherDao.selectFromTeacherByTno(teacherNo);
                if(t != null){
                    teacherDao.updateTeacher(teacherNo,teacherName,teacherSex,teacherBirthday,teacherTitle,teacherNo+"@bjtu.edu.cn",t.getPassword());
                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                    html.append("<div>");
                    html.append("<label>更改信息成功</label>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }else{
                    StringBuilder html = new StringBuilder();
                    html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
                    html.append("<div>");
                    html.append("<label>更改信息失败，请检查输入的教师号</label>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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

                if(Integer.parseInt(newGrade) < 60){
                    StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
                    CourseDataAccessObjects courseDao = new CourseDataAccessObjects();

                    Student s = studentDao.selectFromStudentBySno(sno);

                    String credit = courseDao.selectFromCourseByCno(teachingDao.selectFromTeachingByCid(cid).getCourseNo()).getCourseCredit();
                    int beforeCredit = Integer.parseInt(studentDao.selectFromStudentBySno(sno).getTotalCredit());
                    beforeCredit -= Integer.parseInt(credit);
                    String afterCredit = String.valueOf(beforeCredit);

                    studentDao.updateStudentInfo(sno,s.getClassNo(),s.getStudentName(),s.getStudentBirthday(),s.getStudentSex(),afterCredit,s.getPhoneNumber(),s.getStudentEmail(),s.getPassword());
                }else{
                    StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
                    CourseDataAccessObjects courseDao = new CourseDataAccessObjects();

                    Student s = studentDao.selectFromStudentBySno(sno);

                    String credit = courseDao.selectFromCourseByCno(teachingDao.selectFromTeachingByCid(cid).getCourseNo()).getCourseCredit();
                    int beforeCredit = Integer.parseInt(studentDao.selectFromStudentBySno(sno).getTotalCredit());
                    beforeCredit += Integer.parseInt(credit);
                    String afterCredit = String.valueOf(beforeCredit);

                    studentDao.updateStudentInfo(sno,s.getClassNo(),s.getStudentName(),s.getStudentBirthday(),s.getStudentSex(),afterCredit,s.getPhoneNumber(),s.getStudentEmail(),s.getPassword());
                }
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
