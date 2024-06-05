package com.example.mis.dao;
import com.example.mis.bean.Class;
import com.example.mis.bean.Student;
import com.example.mis.service.StudentService;
import com.example.mis.util.util;

import java.sql.*;
import java.util.ArrayList;


public class StudentDataAccessObjects implements StudentService {
    private Connection conn = null;
    private void initConnection() throws Exception{
        java.lang.Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
    }
    private void closeConnection() throws Exception{
        conn.close();
    }

    @Override
    public boolean insertStudent(String studentNo,String classNO,String studentName
                                , String studentBirthday, String studentSex, String totalCredit
                                , String phoneNumber,String studentEmail , String password) throws Exception {
        initConnection();
        String sql = "insert into student(StudentNo,ClassNo,StudentName,StudentBirthday,StudentSex,TotalCredit,PhoneNumber,StudentEmail,password) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        com.example.mis.util.util u = new util();
        u.setValue(ps,2,classNO);
        u.setValue(ps,3,studentName);
        u.setValue(ps,4,studentBirthday);
        u.setValue(ps,5,studentSex);
        u.setValue(ps,6,totalCredit);
        u.setValue(ps,7,phoneNumber);
        u.setValue(ps,8,studentEmail);
        u.setValue(ps,9,password);
        u.setValue(ps,1,studentNo);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    @Override
    public boolean deleteStudent(String studentNo) throws Exception{
        initConnection();
        String sql = "delete from student where StudentNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentNo);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    @Override
    public void updateStudentInfo(String studentNo,String classNO,String studentName,
                                  String studentBirthday, String studentSex, String totalCredit,
                                  String phoneNumber,String studentEmail, String password) throws Exception{
        initConnection();
        String sql = "UPDATE student SET ClassNo = ?, StudentName = ?, studentBirthday = ?, studentSex = ?, totalCredit = ?, phoneNumber = ?, studentEmail = ? ,password = ? WHERE studentNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        com.example.mis.util.util u = new util();
        u.setValue(ps,1,classNO);
        u.setValue(ps,2,studentName);
        u.setValue(ps,3,studentBirthday);
        u.setValue(ps,4,studentSex);
        u.setValue(ps,5,totalCredit);
        u.setValue(ps,6,phoneNumber);
        u.setValue(ps,7,studentEmail);
        u.setValue(ps,8,password);
        u.setValue(ps,9,studentNo);
        ps.executeUpdate();
        closeConnection();
    }

    @Override
    public ArrayList<Student> selectFromStudent() throws Exception{
        initConnection();
        ArrayList<Student> students = new ArrayList<>() ;
        String sql = "select * from student";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        getMoreStudents(students,rs);
        closeConnection();
        return students;
    }

    @Override
    public Student selectFromStudentBySno(String studentNo) throws Exception{
        initConnection();
        String sql = "select * from student where studentNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentNo);
        ResultSet rs = ps.executeQuery();
        Student s = getStudent(rs);
        closeConnection();
        return s;
    }

    @Override
    public ArrayList<Student> selectStudentByClassNoFromStudent(String classNo) throws Exception{
        initConnection();
        ArrayList<Student> students = new ArrayList<>();
        String sql = "select * from student where classNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,classNo);
        ResultSet rs = ps.executeQuery();
        getMoreStudents(students,rs);
        closeConnection();
        return students;
    }

    @Override
    public int selectStudentNumberFromStudent() throws Exception{
        initConnection();
        String sql = "select count(*) from student";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    @Override
    public int selectStudentNumberByClassNoFromStudent(String classNo) throws Exception{
        initConnection();
        String sql = "select count(*) from student where ClassNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,classNo);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    //辅助方法
    private Student getStudent(ResultSet rs) throws Exception{
        Student s = new Student();
        if(rs.next()){
            s.setStudentNO(rs.getString("StudentNo"));
            s.setStudentName(rs.getString("StudentName"));
            s.setStudentBirthday(rs.getString("StudentBirthday"));
            s.setStudentSex(rs.getString("StudentSex"));
            s.setClassNo(rs.getString("ClassNo"));
            s.setPhoneNumber(rs.getString("PhoneNumber"));
            s.setTotalCredit(rs.getString("TotalCredit"));
            s.setStudentEmail(rs.getString("StudentEmail"));
            s.setPassword(rs.getString("password"));
        }
        return s;
    }
    private void getMoreStudents(ArrayList<Student> students, ResultSet rs) throws SQLException {
        while(rs.next()){
            Student s = new Student();
            s.setStudentNO(rs.getString("StudentNo"));
            s.setStudentName(rs.getString("StudentName"));
            s.setStudentBirthday(rs.getString("StudentBirthday"));
            s.setStudentSex(rs.getString("StudentSex"));
            s.setClassNo(rs.getString("ClassNo"));
            s.setPhoneNumber(rs.getString("PhoneNumber"));
            s.setTotalCredit(rs.getString("TotalCredit"));
            s.setStudentEmail(rs.getString("StudentEmail"));
            s.setPassword(rs.getString("password"));
            students.add(s);
        }
    }




    public static void main(String[] args) throws Exception {
      //  System.out.println(new StudentDataAccessObjects().insertStudent("22992222","null","null","null","null","null","null","null","aaaa"));
//        System.out.println(new StudentDataAccessObjects().insertStudent("22902222","RJ2205","正义","2003-01-02","男","33.5","1343476275","115162@1165.com"));
//        System.out.println(new StudentDataAccessObjects().insertStudent("22903333","RJ2206","李阳","2004-09-05","男","21","16532354352","358762@3476.com"));
//        System.out.println(new StudentDataAccessObjects().insertStudent("22904444","RJ2201","孙伟","2004-08-09","女","31","12226436758","168757@19483.com"));
//        System.out.println(new StudentDataAccessObjects().insertStudent("22905555","RJ2202","魏毅","2003-12-09","女","19.5","1651236274","8764532@127.com"));
//        System.out.println(new StudentDataAccessObjects().insertStudent("22906666","RJ2203","天和","2005-04-23","男","22.5","1623547623","256874623@4574.com"));
//        System.out.println(new StudentDataAccessObjects().insertStudent("22907777","RJ2204","郑思","2004-09-09","女","31.5","1487356425","12345678@1494.com"));
//        System.out.println(new StudentDataAccessObjects().insertStudent("22908888","RJ2205","公孙烨","2005-11-07","女","20","1523637877","238745456@3638.com"));
//        System.out.println(new StudentDataAccessObjects().insertStudent("22909999","RJ2206","周瑜","2003-08-24","男","18","1834352736","14752634@4837.com"));
//        System.out.println(new StudentDataAccessObjects().insertStudent("22900000","RJ2206","诸葛亮","2003-02-12","男","11","1257463513","8372341@1376.com"));

 //   ArrayList<Student> s =  new StudentDataAccessObjects().selectFromStudent();
//        ArrayList<Student> s =  new StudentDataAccessObjects().selectStudentByClassNoFromStudent("RJ2201");
//    for(Student student : s){
//        System.out.print(student.getStudentNO()+" ");
//        System.out.print(student.getStudentName()+" ");
//        System.out.print(student.getPassword());
//        System.out.println();
//    }

//        Student s = new Student();
//        s = new StudentDataAccessObjects().selectFromStudentBySno("1111");
//        System.out.print(s.getStudentNO()+" ");
//        System.out.print(s.getStudentName()+" ");
//        System.out.print(s.getPassword());

        //System.out.println( new StudentDataAccessObjects().selectStudentNumberByClassNoFromStudent("RJ2203"));
        //System.out.println(new StudentDataAccessObjects().selectStudentNumberFromStudent());

        //new StudentDataAccessObjects().updateStudentInfo("22901111","RJ2204","王五","2004-09-09","女","25","14988882222","11@124.com");


        //System.out.println(new StudentDataAccessObjects().insertStudent("22991111","RJ2204","王五","2004-09-09","女","25","14988882222","154312315@124.com"));
       // System.out.println(new StudentDataAccessObjects().deleteStudent("22991111"));

    }
}
