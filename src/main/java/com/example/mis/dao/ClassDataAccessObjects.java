package com.example.mis.dao;
import com.example.mis.bean.Class;
import com.example.mis.service.ClassService;

import java.sql.*;
import java.util.ArrayList;

public class ClassDataAccessObjects implements ClassService {
    private Connection conn = null;
    //初始化Connection
    private void initConnection() throws Exception{
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
    }
    //关闭Connection
    private void closeConnection() throws Exception{
        conn.close();
    }
    //向Class中插入数据。其中，ClassNo不能为空
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
    //根据ClassNo从表中删除数据
    public boolean deleteClass(String classNo) throws Exception{
        initConnection();
        String sql = "delete from class where classNo= ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,classNo);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA==1;
    }
    //根据CLassNo对原表数据进行更改。
    public void updateClassInfo(String classNo,String className,String classMajor,
                                String classDept,String studentNumber) throws Exception{
        initConnection();
        String sql = "update class set ClassName=?,ClassMajor=?,ClassDept=?,StudentNumber=? where ClassNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,className);
        ps.setString(2,classMajor);
        ps.setString(3,classDept);
        ps.setString(4,studentNumber);
        ps.setString(5,classNo);
        ps.executeUpdate();
        closeConnection();
    }
    //根据班级所属专业对班级进行查找
    public ArrayList<Class> selectFromClassWithClassMajor(String classMajor) throws Exception{
        initConnection();
        ArrayList<Class> classes = new ArrayList<>();
        String sql = "select * from class where classMajor = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,classMajor);
        ResultSet rs = ps.executeQuery();
        getMoreClasses(classes,rs);
        closeConnection();
        return classes;
    }

    //根据班级所属系别对班级进行查找
    public ArrayList<Class> selectFromClassWithClassDept(String classDept) throws Exception{
        initConnection();
        ArrayList<Class> classes = new ArrayList<>();
        String sql = "select * from class where ClassDept = ?" ;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,classDept);
        ResultSet rs = ps.executeQuery();
        getMoreClasses(classes,rs);
        closeConnection();
        return classes;
    }

    //对所有班级进行查找
    public ArrayList<Class> selectFromClass() throws Exception{
        initConnection();
        ArrayList<Class> classes = new ArrayList<>();
        String sql = "select * from class";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        getMoreClasses(classes,rs);
        closeConnection();
        return classes;
    }

    public Class selectFromClassByCno(String classNo) throws Exception{
        initConnection();
        String sql = "select * from class where classNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,classNo);
        ResultSet rs = ps.executeQuery();
        Class c = getClass(rs);
        closeConnection();
        return c;
    }

    private void getMoreClasses(ArrayList<Class> classes,ResultSet rs) throws SQLException {
        while(rs.next()){
            Class c = new Class();
            c.setClassNo(rs.getString("ClassNo"));
            c.setClassName(rs.getString("ClassName"));
            c.setClassMajor(rs.getString("ClassMajor"));
            c.setClassDept(rs.getString("ClassDept"));
            c.setStudentNumber(rs.getString("StudentNumber"));
            classes.add(c);
        }

    }

   private Class getClass(ResultSet rs) throws Exception{
        Class c = new Class();
        if(rs.next()){
            c.setClassNo(rs.getString("classNo"));
            c.setClassName(rs.getString("className"));
            c.setClassMajor(rs.getString("classMajor"));
            c.setClassDept(rs.getString("classDept"));
            c.setStudentNumber(rs.getString("studentNumber"));
        }
        return c;
   }
    public static void main(String[] args) throws Exception {
//        System.out.println(new ClassDataAccessObjects().insertClass("RJ2204","软件2204","软件工程","前端界面","30"));
//        System.out.println(new ClassDataAccessObjects().insertClass("RJ2205","软件2205","软件工程","后端开发","25"));
//        System.out.println(new ClassDataAccessObjects().insertClass("RJ2206","软件2206","软件工程","网页开发","25"));
//
//      ArrayList<Class> a = new ArrayList<>();
//       a = new ClassDataAccessObjects().selectFromClass();
//       a = new ClassDataAccessObjects().selectFromClassWithClassDept("AI");
//        a = new ClassDataAccessObjects().selectFromClassWithClassMajor("软件工程");
//       new ClassDataAccessObjects().updateClassInfo("RJ2202","软件2202","软件工程","AI","30");
//      for(Class c : a){
//          System.out.print(c.getClassNo() + " ");
//          System.out.print(c.getClassName()+ " ");
//          System.out.print(c.getClassMajor() + " ");
//          System.out.print(c.getClassDept()+ " ");
//          System.out.print(c.getStudentNumber()+ " ");
//          System.out.println();
//      }
//
//        System.out.println(new ClassDataAccessObjects().insertClass("RJ2207","软件2207","软件工程","前端界面","30"));
//        System.out.println(new ClassDataAccessObjects().deleteClass("RJ2207"));
//

      }
}
