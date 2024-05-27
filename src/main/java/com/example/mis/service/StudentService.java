package com.example.mis.service;

import com.example.mis.bean.Student;

import java.util.ArrayList;

public interface StudentService {
    /**
     *  向学生表中插入数据
     */
    public boolean insertStudent(String studentNo,String classNO,String studentName
            , String studentBirthday, String studentSex, String totalCredit
            , String phoneNumber,String studentEmail, String password) throws Exception;

    /**
     *  从学生表中删除数据
     */
    public boolean deleteStudent(String studentNo) throws Exception;


    /**
     *  更改学生表中的某条数据
     */
    public void updateStudentInfo(String studentNo,String classNO,String studentName,
                                  String studentBirthday, String studentSex, String totalCredit,
                                  String phoneNumber,String studentEmail , String password) throws Exception;

    /**
     *  查询学生表中的所有数据
     */
    public ArrayList<Student> selectFromStudent() throws Exception;

    /**
     *   通过学号查询某个同学的信息
     */
    public Student selectFromStudentBySno(String studentNo) throws Exception;

    /**
     *  通过班级号查询学生信息
     */
    public ArrayList<Student> selectStudentByClassNoFromStudent(String classNo) throws Exception;

    /**
     *   查询学生表中学生个数
     */
    public int selectStudentNumberFromStudent() throws Exception;

    /**
     *   根据班级名称查询学生人数
     */
    public int selectStudentNumberByClassNoFromStudent(String classNo) throws Exception;
}
