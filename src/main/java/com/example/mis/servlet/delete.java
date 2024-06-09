package com.example.mis.servlet;

import com.example.mis.bean.Class;
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

/**
 * 删除信息的方法
 */
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
            /**
             * 用于管理员删除用户信息
             */
            String userName = request.getParameter("username");
            if(userName.length() == 8){//删除学生信息
                UserDataAccessObjects userDao = new UserDataAccessObjects();
                StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
                try {
                    //从user表和student表中分别删除该同学信息
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
                //从user和teacher表中分别删除该教师信息
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
        } else if (action.equals("delete_class")) {
            /**
             * 用于管理员删除班级模块
             */
            String classNo = request.getParameter("clno");

            ClassDataAccessObjects classDao = new ClassDataAccessObjects();
            try {
                classDao.deleteClass(classNo);
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
        } else if(action.equals("delete_student")){
            /**
             * 用于管理员删除学生信息模块
             */
            String studentNo = request.getParameter("sno");//从url获取学号信息
            String classNo;
            StudentDataAccessObjects studentDao = new StudentDataAccessObjects();
            ClassDataAccessObjects classDao = new ClassDataAccessObjects();

            StringBuilder html = new StringBuilder();
            html.append("<div style='display: flex; justify-content: center; align-items: center; height: 100%;'>");
            html.append("<div>");
            try {
                classNo = studentDao.selectFromStudentBySno(studentNo).getClassNo();
                studentDao.deleteStudent(studentNo);//从student表删除指定学号的同学
                UserDataAccessObjects userDao = new UserDataAccessObjects();
                userDao.deleteUser(studentNo);//从user表删除该用户

                Class c = classDao.selectFromClassByCno(classNo);
                String className = c.getClassName();
                String classMajor = c.getClassMajor();
                String classDept = c.getClassDept();
                int studentNumber =Integer.parseInt(c.getStudentNumber());
                studentNumber--;
                if(studentNumber >= 0){
                    String newStudentNumber = String.valueOf(studentNumber);
                    classDao.updateClassInfo(classNo,className,classMajor,classDept,newStudentNumber);//对应班级人数减一

                    html.append("<label>信息已保存</label>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }else{
                    html.append("<label>删除失败</label>");
                    html.append("</div>");
                    html.append("</div>");
                    response.getWriter().write(html.toString());
                }

            } catch (Exception e) {
                html.append("<label>删除失败</label>");
                html.append("</div>");
                html.append("</div>");
                response.getWriter().write(html.toString());
            }
        } else if (action.equals("delete_course")) {
            /**
             * 用于管理员删除课程
             */
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
        } else if (action.equals("delete_teaching")) {
            String cid = request.getParameter("cid");
            com.example.mis.bean.teaching teaching = new teaching();
            teachingDataAccessObjects teachingDao = new teachingDataAccessObjects();
            try {
                teaching = teachingDao.selectFromTeachingByCid(cid);
                teachingDao.deleteTeaching(teaching.getCourseNo(),teaching.getTeacherNo(),cid);
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

        } else if (action.equals("delete_teacher")) {
            /**
             * 用于管理员删除指定教师
             */
            String teacherNo = request.getParameter("tno");
            TeacherDataAccessObjects teacherDao = new TeacherDataAccessObjects();

            try {
                teacherDao.deleteTeacher(teacherNo);
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
        } else if (action.equals("delete_scInfo")) {
            /**
             * 用于学生退课模块
             */
            com.example.mis.dao.scDataAccessObjects scDao = new scDataAccessObjects();
            String courseNo = request.getParameter("courseNo");
            String cid = request.getParameter("cid");
            String sno = request.getParameter("sno");
            try {
                scDao.deleteSC(sno,courseNo,cid);
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
}
