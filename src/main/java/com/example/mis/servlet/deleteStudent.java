package com.example.mis.servlet;

import com.example.mis.bean.Class;
import com.example.mis.dao.ClassDataAccessObjects;
import com.example.mis.dao.StudentDataAccessObjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "deleteStudent",value = "/delete_student")
public class deleteStudent extends HttpServlet {
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

        StudentDataAccessObjects studentDao = new StudentDataAccessObjects();

        String studentNo = request.getParameter("student_no");

        try {
            if(studentDao.selectFromStudentBySno(studentNo) == null){
                //TODO:
            }else{
                String classNo = studentDao.selectFromStudentBySno(studentNo).getClassNo();
                studentDao.deleteStudent(studentNo);

                ClassDataAccessObjects classDao = new ClassDataAccessObjects();
                Class c = classDao.selectFromClassByCno(classNo);
                String className = c.getClassName();
                String classMajor = c.getClassMajor();
                String classDept = c.getClassDept();
                int studentNumber = Integer.parseInt(c.getStudentNumber());
                studentNumber--;
                String newStudentNumber = String.valueOf(studentNumber);
                classDao.updateClassInfo(classNo,className,classMajor,classDept,newStudentNumber);

            }
        } catch (Exception e) {
            out.println(e);
        }
    }
}
