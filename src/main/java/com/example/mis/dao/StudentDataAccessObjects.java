package com.example.mis.dao;
import java.sql.*;

public class StudentDataAccessObjects {
    private Connection conn = null;
    private void initConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
    }
    private void closeConnection() throws Exception{
        conn.close();
    }
    public boolean insertStudent(String studentNo,String classNO,String studentName
                                , String studentBirthday, String studentSex, String totalCredit
                                , String phoneNumber,String studentEmail) throws Exception {
        initConnection();
        String sql = "insert into student(StudentNo,ClassNo,StudentName,StudentBirthday,StudentSex,TotalCredit,PhoneNumber,StudentEmail) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentNo);
        ps.setString(2,classNO);
        ps.setString(3,studentName);
        ps.setString(4,studentBirthday);
        ps.setString(5,studentSex);
        ps.setString(6,totalCredit);
        ps.setString(7,phoneNumber);
        ps.setString(8,studentEmail);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

//    public static void main(String[] args) throws Exception {
//        System.out.println(new StudentDataAccessObjects().insertStudent("11111","RJ2202","张三","2024-5-25","男","38.5","12345678901","13333333@qq.com"));
//    }
}
