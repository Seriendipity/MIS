package com.example.mis.util;

import com.example.mis.bean.S_C_Info;
import com.example.mis.bean.S_C_T_Info;
import com.example.mis.bean.T_C_Info;

import java.sql.*;
import java.util.ArrayList;

/**
 * S_C_Info类、S_C_T_Info类、T_C类辅助方法，
 */
public class util {
    public ArrayList<T_C_Info> getT_C_Infos() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
        String sql = "SELECT teacher.teacherName, course.CourseName, teaching.language ,course.CourseNo, teaching.cid, course.CourseCredit " +
                "FROM teacher " +
                "JOIN teaching ON teacher.TeacherNo = teaching.TeacherNo " +
                "JOIN course ON teaching.CourseNo = course.CourseNo";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<T_C_Info> tCInfos = new ArrayList<>();
        while(rs.next()){
            T_C_Info t_c_info = new T_C_Info();
            t_c_info.setCid(rs.getString("cid"));
            t_c_info.setTeacherName(rs.getString("teacherName"));
            t_c_info.setCourseName(rs.getString("courseName"));
            t_c_info.setCourseCredit(rs.getString("courseCredit"));
            t_c_info.setCourseNo(rs.getString("courseNo"));
            t_c_info.setLanguage(rs.getString("language"));
            tCInfos.add(t_c_info);
        }
        conn.close();
        return tCInfos;
    }

    public ArrayList<S_C_Info> getS_C_Infos(String studentNo) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
        String sql = "SELECT c.CourseName, s.cid, t.TeacherName, s.Grade " +
                "FROM teaching " +
                "JOIN course c ON teaching.CourseNo = c.CourseNo " +
                "JOIN sc s ON teaching.cid = s.cid " +
                "JOIN teacher t ON teaching.TeacherNo = t.TeacherNo WHERE studentNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentNo);
        ArrayList<S_C_Info> sCInfos = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            S_C_Info s_c_info = new S_C_Info();
            s_c_info.setCourseName(rs.getString("courseName"));
            s_c_info.setTeacherName(rs.getString("teacherName"));
            s_c_info.setGrade(rs.getString("grade"));
            s_c_info.setCid(rs.getString("cid"));
            sCInfos.add(s_c_info);
        }
        conn.close();
        return sCInfos;
    }

    public ArrayList<S_C_T_Info> getS_C_T_Infos(String studentNo) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
        String sql = "SELECT c.CourseName, s.cid, t.TeacherName, language " +
                "FROM teaching " +
                "JOIN course c ON teaching.CourseNo = c.CourseNo " +
                "JOIN sc s ON teaching.cid = s.cid " +
                "JOIN teacher t ON teaching.TeacherNo = t.TeacherNo WHERE studentNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentNo);
        ArrayList<S_C_T_Info> sCTInfos = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
           S_C_T_Info s_c_t_info = new S_C_T_Info();
           s_c_t_info.setCid(rs.getString("cid"));
           s_c_t_info.setLanguage(rs.getString("language"));
           s_c_t_info.setTeacherName(rs.getString("teacherName"));
           s_c_t_info.setCourseName(rs.getString("courseName"));
           sCTInfos.add(s_c_t_info);
        }
        conn.close();
        return sCTInfos;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//       ArrayList<T_C_Info> tCInfos= new com.example.mis.util.util().getT_C_Infos();
//       for(T_C_Info t : tCInfos){
//           System.out.print(t.getCourseName()+" ");
//           System.out.print(t.getTeacherName());
//           System.out.println();
//       }
//       ArrayList<S_C_Info> sCInfos= new com.example.mis.util.util().getS_C_Infos("11111");
//       for(S_C_Info s :sCInfos){
//           System.out.print(s.getCourseName()+" ");
//           System.out.print(s.getCid() + " ");
//           System.out.print(s.getTeacherName());
//           System.out.println();
//       }
        ArrayList<S_C_T_Info> sCTInfos = new com.example.mis.util.util().getS_C_T_Infos("11111");
        for(S_C_T_Info s : sCTInfos){
            System.out.print(s.getCid()+" ");
            System.out.print(s.getLanguage()+" ");
            System.out.print(s.getTeacherName() + " ");
            System.out.print(s.getCourseName());
            System.out.println();
        }
    }
}
