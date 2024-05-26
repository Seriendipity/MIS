package com.example.mis.dao;

import com.example.mis.bean.Course;
import com.example.mis.service.CourseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class CourseDataAccessObjects implements CourseService {
    private Connection conn = null;
    private void initConnection() throws Exception{
        java.lang.Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
    }
    private void closeConnection() throws Exception{
        conn.close();
    }

    public boolean insertCourse(String courseNo,String courseName,String courseCredit) throws Exception{
        initConnection();
        String sql = "insert into Course(courseNo,courseName,courseCredit) values (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,courseNo);
        ps.setString(2,courseName);
        ps.setString(3,courseCredit);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    public boolean deleteCourse(String courseNo) throws Exception{
        initConnection();
        String sql = "delete from course where courseNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,courseNo);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    public void updateCourse(String courseNo,String courseName,String courseCredit) throws Exception{
        initConnection();
        String sql = "update course set courseName= ? , courseCredit = ? where courseNo =?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,courseName);
        ps.setString(2,courseCredit);
        ps.setString(3,courseNo);
        ps.executeUpdate();
        closeConnection();
    }

    public ArrayList<Course> selectFromCourse() throws Exception{
        initConnection();
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "select * from course";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        getMoreCourses(courses,rs);
        closeConnection();
        return courses;
    }

    public ArrayList<Course> selectFromCourseByCourseName(String courseName) throws Exception{
        initConnection();
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "select * from course where courseName = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,courseName);
        ResultSet rs = ps.executeQuery();
        getMoreCourses(courses,rs);
        closeConnection();
        return courses;
    }

    public Course selectFromCourseByCno(String courseNo) throws Exception{
        initConnection();
        String sql = "select * from course where courseNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,courseNo);
        ResultSet rs = ps.executeQuery();
        Course c = getCourse(rs);
        closeConnection();
        return c;
    }
    private Course getCourse(ResultSet rs) throws Exception{
        Course c = new Course();
        if(rs.next()){
            c.setCourseNo(rs.getString("CourseNo"));
            c.setCourseName(rs.getString("CourseName"));
            c.setCourseCredit(rs.getString("CourseCredit"));
        }
        return c;
    }
    private void getMoreCourses(ArrayList<Course> courses , ResultSet rs) throws Exception{
        while(rs.next()){
            Course c = new Course();
            c.setCourseNo(rs.getString("CourseNo"));
            c.setCourseName(rs.getString("CourseName"));
            c.setCourseCredit(rs.getString("CourseCredit"));
            courses.add(c);
        }
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(new CourseDataAccessObjects().insertCourse("00000001","数据库","3"));
//        System.out.println(new CourseDataAccessObjects().insertCourse("00000002","离散数学","4"));
//        System.out.println(new CourseDataAccessObjects().insertCourse("00000003","算法","4"));
//        System.out.println(new CourseDataAccessObjects().insertCourse("00000004","微积分","6"));
//        System.out.println(new CourseDataAccessObjects().insertCourse("00000005","线性代数","5"));
//        System.out.println(new CourseDataAccessObjects().insertCourse("00000006","机器学习","3"));

        // new CourseDataAccessObjects().updateCourse("00000001","数据库","5");

 //       ArrayList<Course> courses = new ArrayList<>();
        //courses = new CourseDataAccessObjects().selectFromCourse();
//        courses = new CourseDataAccessObjects().selectFromCourseByCourseName("数据库");
//        for(Course c: courses){
//            System.out.print(c.getCourseNo());
//            System.out.print(c.getCourseName());
//            System.out.println();
//        }

//        Course c = new Course();
//        c = new CourseDataAccessObjects().selectFromCourseByCno("00000004");
//        System.out.println(c.getCourseName());

      // System.out.println(new CourseDataAccessObjects().insertCourse("00000010","C++","3"));
       // System.out.println(new CourseDataAccessObjects().deleteCourse("00000010"));
    }
}
