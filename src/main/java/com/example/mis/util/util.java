package com.example.mis.util;

import com.example.mis.bean.*;
import com.mysql.cj.util.Util;

import java.lang.Class;
import java.sql.*;
import java.util.ArrayList;

/**
 * S_C_Info类、S_C_T_Info类、T_C类,User类辅助方法，
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

    public ArrayList<User> getUsrInfo() throws ClassNotFoundException, SQLException{
        Connection conn = null;
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
        String sql = "SELECT StudentNo AS UserName, password " +
                "FROM student " +
                "UNION " +
                "SELECT TeacherNo AS UserName, password " +
                "FROM teacher";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()){
            User u = new User();
            u.setUserName(rs.getString("UserName"));
            u.setPassword(rs.getString("Password"));
            users.add(u);
        }
        conn.close();
        return users;
    }

    public void setValue(PreparedStatement ps, int parameterIndex, String value) throws SQLException{
        if(value.equals("null")){
            ps.setNull(parameterIndex,Types.VARCHAR);
        }else{
            ps.setString(parameterIndex,value);
        }
    }

    public ArrayList<CourseGrade> getCG() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
        String sql = "select CourseName,avg(Grade) as avgGrade "+
                    "from teaching "+
                    "join sc s on teaching.cid = s.cid "+
                    "join course c on s.CourseNo = c.CourseNo "+
                    "group by CourseName";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        ArrayList<CourseGrade> cgs = new ArrayList<>();
        while(rs.next()){
            CourseGrade cg = new CourseGrade();
            cg.setCourseName(rs.getString("courseName"));
            cg.setAvgGrade(rs.getString("avgGrade"));
            cgs.add(cg);
        }
        conn.close();
        return cgs;
    }

    public ArrayList<CourseGrade> getCGWithTeacherNo(String teacherNo) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
        String sql = "select CourseName,avg(Grade) as avgGrade "+
                "from teaching "+
                "join sc s on teaching.cid = s.cid "+
                "join course c on s.CourseNo = c.CourseNo "+
                "where teacherNo = ?"+
                "group by CourseName";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,teacherNo);
        ResultSet rs = ps.executeQuery();
        ArrayList<CourseGrade> cgs = new ArrayList<>();
        while(rs.next()){
            CourseGrade cg = new CourseGrade();
            cg.setCourseName(rs.getString("courseName"));
            cg.setAvgGrade(rs.getString("avgGrade"));
            cgs.add(cg);
        }
        conn.close();
        return cgs;
    }

    public ArrayList<CourseFail> getCF() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
        String sql = "SELECT " +
                "    c.CourseName as CourseName, " +
                "    ROUND(SUM(CASE WHEN s.Grade < 60 THEN 1 ELSE 0 END) * 1.0 / COUNT(*) * 100, 2) AS FailingRate " +
                "FROM " +
                "    teaching " +
                "    JOIN sc s ON teaching.cid = s.cid " +
                "    JOIN course c ON s.CourseNo = c.CourseNo " +
                "GROUP BY " +
                "    c.CourseName;";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        ArrayList<CourseFail> cfs = new ArrayList<>();
        while(rs.next()){
            CourseFail cf = new CourseFail();
            cf.setCourseName(rs.getString("courseName"));
            cf.setFailRate(rs.getString("FailingRate"));
            cfs.add(cf);
        }
        conn.close();
        return cfs;
    }

    public ArrayList<CourseFail> getCFWithTno(String teacherNo) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
        String sql = "SELECT " +
                "    c.CourseName as CourseName, " +
                "    ROUND(SUM(CASE WHEN s.Grade < 60 THEN 1 ELSE 0 END) * 1.0 / COUNT(*) * 100, 2) AS FailingRate " +
                "FROM " +
                "    teaching " +
                "    JOIN sc s ON teaching.cid = s.cid " +
                "    JOIN course c ON s.CourseNo = c.CourseNo " +
                "Where teacherNo = ? "+
                "GROUP BY " +
                "    c.CourseName;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,teacherNo);
        ResultSet rs = ps.executeQuery();
        ArrayList<CourseFail> cfs = new ArrayList<>();
        while(rs.next()){
            CourseFail cf = new CourseFail();
            cf.setCourseName(rs.getString("courseName"));
            cf.setFailRate(rs.getString("FailingRate"));
            cfs.add(cf);
        }
        conn.close();
        return cfs;
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
//        ArrayList<S_C_T_Info> sCTInfos = new com.example.mis.util.util().getS_C_T_Infos("11111");
//        for(S_C_T_Info s : sCTInfos){
//            System.out.print(s.getCid()+" ");
//            System.out.print(s.getLanguage()+" ");
//            System.out.print(s.getTeacherName() + " ");
//            System.out.print(s.getCourseName());
//            System.out.println();
//        }

//        ArrayList<User> users = new util().getUsrInfo();
//        for(User u : users){
//            System.out.print(u.getUserName() + " ");
//            System.out.print(u.getPassword());
//            System.out.println();
//        }
//        ArrayList<CourseGrade> cgs = new util().getCG();
//        for(CourseGrade cg:cgs){
//            System.out.print(cg.getCourseName()+" ");
//            System.out.print(cg.getAvgGrade());
//            System.out.println();
//        }

//        ArrayList<CourseFail> cfs = new util().getCF();
//        for(CourseFail cf : cfs){
//            System.out.print(cf.getCourseName()+" ");
//            System.out.print(cf.getFailRate());
//            System.out.println();
//        }

//        ArrayList<CourseGrade> cgs = new util().getCGWithTeacherNo("1001");
//        for(CourseGrade cg:cgs){
//            System.out.print(cg.getCourseName()+" ");
//            System.out.print(cg.getAvgGrade());
//            System.out.println();
//        }

                ArrayList<CourseFail> cfs = new util().getCF();
        for(CourseFail cf : cfs){
            System.out.print(cf.getCourseName()+" ");
            System.out.print(cf.getFailRate());
            System.out.println();
        }
    }
}
