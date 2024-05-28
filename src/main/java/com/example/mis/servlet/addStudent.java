package com.example.mis.servlet;

import com.example.mis.bean.Class;
import com.example.mis.bean.Student;
import com.example.mis.dao.ClassDataAccessObjects;
import com.example.mis.dao.StudentDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 向学生表中插入一个学生信息
 */
@WebServlet(name = "addStudent",value = "/add_student")
public class addStudent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out =  response.getWriter();

        StudentDataAccessObjects studentDao = new StudentDataAccessObjects();

        String studentNo = request.getParameter("student_no");
        String studentName = request.getParameter("student_name");
        String classNo = request.getParameter("class_no");
        String studentBirthday = request.getParameter("student_birthday");
        String studentSex = request.getParameter("student_sex");
        String totalCredit = request.getParameter("total_credit");
        String phoneNumber = request.getParameter("phone_number");
        String studentEmail = request.getParameter("student_email");
        String password = request.getParameter("password");

        System.out.println(studentName);
        System.out.println(classNo);
        ClassDataAccessObjects classDao = new ClassDataAccessObjects();

        try {
            if(classDao.selectFromClassByCno(classNo) == null){
                //TODO:
            }else{
                studentDao.insertStudent(studentNo,classNo,studentName,studentBirthday,
                        studentSex,totalCredit,phoneNumber,studentEmail,password);
                //给他加入的班级的人数加一
                Class c = classDao.selectFromClassByCno(classNo);
                String className = c.getClassName();
                String classMajor = c.getClassMajor();
                String classDept = c.getClassDept();
                int studentNumber =Integer.parseInt(c.getStudentNumber());
                studentNumber++;
                String newStudentNumber = String.valueOf(studentNumber);
                classDao.updateClassInfo(classNo,className,classMajor,classDept,newStudentNumber);

            }
        } catch (Exception e) {
            out.println(e);
        }


    }
}
