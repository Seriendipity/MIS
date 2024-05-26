package com.example.mis.service;

import com.example.mis.bean.Teacher;

import java.util.ArrayList;

public interface TeacherService {
    /**
     *  向Teacher表中插入一行元组
     */
    public boolean insertTeacher(String teacherNo,String teacherName,String teacherSex,
                                 String teacherBirthday,String teacherTitle,String teacherEmail) throws Exception;

    /**
     *  根据教师号从Teacher表中删除一行元组
     */
    public boolean deleteTeacher(String teacherNo) throws Exception;

    /**
     *  根据教师号，更改Teacher表中的某个教师信息
     */
    public void updateTeacher(String teacherNo,String teacherName,String teacherSex,
                              String teacherBirthday,String teacherTitle,String teacherEmail) throws Exception;

    /**
     *  返回所有的教师信息
     */
    public ArrayList<Teacher> selectFromTeacher() throws Exception;

    /**
     *  返回指定教师号的教师信息
     */
    public Teacher selectFromTeacherByTno(String teacherNo) throws Exception;

    /**
     *  根据教师职称返回教师信息
     */
    public ArrayList<Teacher> selectFromTeacherByTitle(String teacherTitle) throws Exception;

    /**
     *  根据邮箱返回指定教师信息
     */
    public Teacher selectFromTeacherByEmail(String teacherEmail) throws Exception;
}
