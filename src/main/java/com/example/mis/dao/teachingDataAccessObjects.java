package com.example.mis.dao;
import com.example.mis.bean.teaching;

import java.sql.*;
import java.util.ArrayList;

public class teachingDataAccessObjects {
    private Connection conn = null;
    //初始化Connection
    private void initConnection() throws Exception{
        java.lang.Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
    }
    //关闭Connection
    private void closeConnection() throws Exception{
        conn.close();
    }

    public boolean insertTeaching(String courseNo,String teacherNo,String language) throws Exception{
        initConnection();
        String sql = "insert into teaching(courseNo,teacherNo,language) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,courseNo);
        ps.setString(2,teacherNo);
        ps.setString(3,language);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }
    public boolean deleteTeaching(String courseNo,String teacherNo) throws Exception{
        initConnection();
        String sql = "delete from teaching where courseNo = ? and teacherNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,courseNo);
        ps.setString(2,teacherNo);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    public void updateTeaching(String courseNo,String teacherNo,String language) throws Exception{
        initConnection();
        String sql = "update teaching set language = ? where courseNo = ? and teacherNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,language);
        ps.setString(2,courseNo);
        ps.setString(3,teacherNo);
        ps.executeUpdate();
        closeConnection();
    }

    public ArrayList<teaching> selectFromTeaching() throws Exception{
        initConnection();
        ArrayList<teaching> t = new ArrayList<>();
        String sql = "select * from teaching";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        getMoreTeaching(t,rs);
        closeConnection();
        return t;
    }
    public ArrayList<teaching> selectFromTeachingByTno(String teacherNo) throws Exception{
        initConnection();
        ArrayList<teaching> t = new ArrayList<>();
        String sql = "select * from teaching where teacherNo = '" + teacherNo + "'";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        getMoreTeaching(t,rs);
        closeConnection();
        return t;
    }

    public ArrayList<teaching> selectFromTeachingByLanguage(String language) throws Exception{
        initConnection();
        ArrayList<teaching> t = new ArrayList<>();
        String sql = "select * from teaching where language = '" + language + "'";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        getMoreTeaching(t,rs);
        closeConnection();
        return t;
    }
    private void getMoreTeaching(ArrayList<teaching> t, ResultSet rs) throws Exception{
        while(rs.next()){
            teaching tg = new teaching();
            tg.setCourseNo(rs.getString("CourseNo"));
            tg.setTeacherNo(rs.getString("TeacherNo"));
            tg.setLanguage(rs.getString("Language"));
            t.add(tg);
        }
    }

    public static void main(String[] args) throws Exception{
//        System.out.println(new teachingDataAccessObjects().insertTeaching("00000001","1001","中文"));
//        System.out.println(new teachingDataAccessObjects().insertTeaching("00000001","1002","英文"));
//        System.out.println(new teachingDataAccessObjects().insertTeaching("00000002","1003","中文"));
//        System.out.println(new teachingDataAccessObjects().insertTeaching("00000003","1004","双语"));
//        System.out.println(new teachingDataAccessObjects().insertTeaching("00000004","1006","中文"));
//        System.out.println(new teachingDataAccessObjects().insertTeaching("00000005","1002","中文"));
//        System.out.println(new teachingDataAccessObjects().insertTeaching("00000006","1005","中文"));

        //new teachingDataAccessObjects().updateTeaching("00000001","1001","英文");

 //       ArrayList<teaching> teachings = new ArrayList<>();
        //teachings = new teachingDataAccessObjects().selectFromTeaching();
        //teachings = new teachingDataAccessObjects().selectFromTeachingByLanguage("中文");
//        teachings = new teachingDataAccessObjects().selectFromTeachingByTno("1002");
//         for(teaching t : teachings){
//            System.out.print(t.getTeacherNo());
//            System.out.print(t.getCourseNo());
//            System.out.println();
//        }

       // System.out.println(new teachingDataAccessObjects().insertTeaching("00000001","1005","中文"));
       // System.out.println(new teachingDataAccessObjects().deleteTeaching("00000001","1005"));
    }
}
