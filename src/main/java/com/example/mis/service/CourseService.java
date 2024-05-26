package com.example.mis.service;

import com.example.mis.bean.Course;

import java.util.ArrayList;

public interface CourseService {
    /**
       向Course表中插入数据
     */
    public boolean insertCourse(String courseNo,String courseName,String courseCredit) throws Exception;

    /**
     * 从Course中根据课程号删除课程
     */
    public boolean deleteCourse(String courseNo) throws Exception;

    /**
     *  根据课程号修改某一个课程的信息
     */
    public void updateCourse(String courseNo,String courseName,String courseCredit) throws Exception;

    /**
     *  查询所有课程信息
     */
    public ArrayList<Course> selectFromCourse() throws Exception;

    /**
     *  查询指定课程名的课程信息
     */
    public ArrayList<Course> selectFromCourseByCourseName(String courseName) throws Exception;

    /**
     * 查询指定课程号的课程信息
     */
    public Course selectFromCourseByCno(String courseNo) throws Exception;
}
