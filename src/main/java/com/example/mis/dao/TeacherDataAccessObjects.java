package com.example.mis.dao;

import com.example.mis.bean.Teacher;
import com.example.mis.service.TeacherService;
import com.example.mis.util.util;

import java.sql.*;
import java.util.ArrayList;

public class TeacherDataAccessObjects implements TeacherService {
    private Connection conn = null;
    private void initConnection() throws Exception{
        java.lang.Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
    }
    private void closeConnection() throws Exception{
        conn.close();
    }

    @Override
    public boolean insertTeacher(String teacherNo,String teacherName,String teacherSex,
                                 String teacherBirthday,String teacherTitle,
                                 String teacherEmail, String password) throws Exception{
        initConnection();
        String sql = "insert into teacher(teacherNo,teacherName,teacherSex,teacherBirthday,teacherTitle,teacherEmail,password) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        com.example.mis.util.util u = new util();
        u.setValue(ps,1,teacherNo);
        u.setValue(ps,2,teacherName);
        u.setValue(ps,3,teacherSex);
        u.setValue(ps,4,teacherBirthday);
        u.setValue(ps,5,teacherTitle);
        u.setValue(ps,6,teacherEmail);
        u.setValue(ps,7,password);
        int SQLSA = ps.executeUpdate();
        closeConnection();
        return SQLSA == 1;
    }

    @Override
    public boolean deleteTeacher(String teacherNo) throws Exception{
        initConnection();
        String sql = "delete from teacher where teacherNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,teacherNo);
        int SQLSA = ps.executeUpdate();
        return SQLSA == 1;
    }

    @Override
    public void updateTeacher(String teacherNo,String teacherName,String teacherSex,
                              String teacherBirthday,String teacherTitle,
                              String teacherEmail,String password) throws Exception{
        initConnection();
        String sql = "update teacher set teacherName = ? , teacherSex = ? , teacherBirthday = ? ,teacherTitle = ? , teacherEmail = ? , password = ? where teacherNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,teacherName);
        ps.setString(2,teacherSex);
        ps.setString(3,teacherBirthday);
        ps.setString(4,teacherTitle);
        ps.setString(5,teacherEmail);
        ps.setString(6,password);
        ps.setString(7,teacherNo);
        ps.executeUpdate();
        closeConnection();
    }

    @Override
    public ArrayList<Teacher> selectFromTeacher() throws Exception{
        initConnection();
        ArrayList<Teacher> teachers = new ArrayList<>();
        String sql = "select * from teacher";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        getMoreTeacher(teachers,rs);
        closeConnection();
        return teachers;
    }

    @Override
    public Teacher selectFromTeacherByTno(String teacherNo) throws Exception{
        initConnection();
        String sql = "select * from teacher where teacherNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,teacherNo);
        ResultSet rs = ps.executeQuery();
        Teacher t = getTeacher(rs);
        closeConnection();
        return t;
    }

    @Override
    public ArrayList<Teacher> selectFromTeacherByTitle(String teacherTitle) throws Exception{
        initConnection();
        ArrayList<Teacher> teachers = new ArrayList<>();
        String sql = "select * from teacher where teacherTitle = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,teacherTitle);
        ResultSet rs = ps.executeQuery();
        getMoreTeacher(teachers,rs);
        closeConnection();
        return teachers;
    }

    @Override
    public Teacher selectFromTeacherByEmail(String teacherEmail) throws Exception{
        initConnection();
        String sql = "select * from teacher where teacherEmail = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,teacherEmail);
        ResultSet rs = ps.executeQuery();
        Teacher t = getTeacher(rs);
        closeConnection();
        return t;
    }

    //辅助方法
    private Teacher getTeacher(ResultSet rs) throws Exception{
        Teacher t = new Teacher();
        if(rs.next()){
            t.setTeacherNo(rs.getString("TeacherNo"));
            t.setTeacherName(rs.getString("TeacherName"));
            t.setTeacherSex(rs.getString("TeacherSex"));
            t.setTeacherBirthday(rs.getString("TeacherBirthday"));
            t.setTeacherEmail(rs.getString("TeacherEmail"));
            t.setTeacherTitle(rs.getString("TeacherTitle"));
            t.setPassword(rs.getString("password"));
            return t;
        }
        return null;
    }
    //辅助方法
    private void getMoreTeacher(ArrayList<Teacher> teachers , ResultSet rs) throws Exception{
        while(rs.next()){
            Teacher t = new Teacher();
            t.setTeacherNo(rs.getString("TeacherNo"));
            t.setTeacherName(rs.getString("TeacherName"));
            t.setTeacherSex(rs.getString("TeacherSex"));
            t.setTeacherBirthday(rs.getString("TeacherBirthday"));
            t.setTeacherEmail(rs.getString("TeacherEmail"));
            t.setTeacherTitle(rs.getString("TeacherTitle"));
            t.setPassword(rs.getString("password"));
            teachers.add(t);
        }
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(new TeacherDataAccessObjects().insertTeacher("1555","null","null","null","null","null","35412"));
//        System.out.println(new TeacherDataAccessObjects().insertTeacher("1002","徐勇","男","1987-11-24","讲师","2765312@265.cn"));
//        System.out.println(new TeacherDataAccessObjects().insertTeacher("1003","李甜","女","1986-04-04","讲师","12876345@265.cn"));
//        System.out.println(new TeacherDataAccessObjects().insertTeacher("1004","张光","男","1973-02-13","教授","485736@265.cn"));
//        System.out.println(new TeacherDataAccessObjects().insertTeacher("1005","吴萌","女","1983-11-24","副教授","3874623@265.cn"));
//        System.out.println(new TeacherDataAccessObjects().insertTeacher("1006","张天","男","1962-09-14","教授","182538@265.cn"));

     //   new TeacherDataAccessObjects().updateTeacher("1001","张军","男","1977-08-04","教授","a@265.cn");

//        ArrayList<Teacher> teachers = new ArrayList<>();
//        //teachers = new TeacherDataAccessObjects().selectFromTeacher();
//        teachers = new TeacherDataAccessObjects().selectFromTeacherByTitle("教授");
//        for(Teacher t : teachers){
//            System.out.print(t.getTeacherName());
//            System.out.print(t.getTeacherEmail());
//            System.out.print(t.getPassword());
//            System.out.println();
//        }

    //    System.out.println(new TeacherDataAccessObjects().selectFromTeacherByEmail("a@265.cn").getTeacherName());
        //System.out.println(new TeacherDataAccessObjects().selectFromTeacherByTno("1001").getTeacherName());


       // System.out.println(new TeacherDataAccessObjects().insertTeacher("1011","张张","男","1977-08-04","教授","631541@265.cn"));
     //   System.out.println(new TeacherDataAccessObjects().deleteTeacher("1011"));
    }
 }
