package com.example.mis.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ClassDataAccessObjects {
    private Connection conn = null;
    private void initConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
    }
    private void closeConnection() throws Exception{
        conn.close();
    }

    public boolean insertClass(String classNo, String className, String classMajor,
                               String classDept, String studentNumber ) throws Exception{
        initConnection();
        String sql = "insert into class(ClassNo,ClassName,ClassMajor,ClassDept,StudentNumber) values (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,classNo);
        ps.setString(2,className);
        ps.setString(3,classMajor);
        ps.setString(4,classDept);
        ps.setString(5,studentNumber);
        int SQLCA = ps.executeUpdate();
        return SQLCA == 1;
    }

//    public static void main(String[] args) throws Exception {
//        System.out.println(new ClassDataAccessObjects().insertClass("RJ2201","软件2201","软件工程","软件开发","20"));
//    }
}
