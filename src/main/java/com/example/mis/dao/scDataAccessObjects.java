package com.example.mis.dao;
import com.example.mis.bean.sc;
import com.example.mis.service.scService;
import java.sql.*;
import java.util.ArrayList;

public class scDataAccessObjects implements scService{
    private Connection conn = null;
    private void initConnection() throws Exception{
        java.lang.Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
    }
    private void closeConnection() throws Exception{
        conn.close();
    }

    public boolean insertSC(String studentNo,String courseNo,String grade) throws Exception{
        initConnection();
        String sql = "insert into sc(studentNO,courseNo,grade) values (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentNo);
        ps.setString(2,courseNo);
        ps.setString(3,grade);
        int SQLSA = ps.executeUpdate();
        closeConnection();
        return SQLSA == 1;
    }

    public boolean deleteSC(String studentNo,String courseNo) throws Exception{
        initConnection();
        String sql = "delete from sc where StudentNo = ? and courseNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentNo);
        ps.setString(2,courseNo);
        int SQLSA = ps.executeUpdate();
        closeConnection();
        return SQLSA == 1;
    }
    public void updateSc(String studentNo,String courseNo,String grade) throws Exception{
        initConnection();
        String sql = "update sc set grade = ? where StudentNo = ? and courseNo = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,grade);
        ps.setString(2,studentNo);
        ps.setString(3,courseNo);
        ps.executeUpdate();
        closeConnection();
    }
    public ArrayList<sc> selectFromSC() throws Exception{
        initConnection();
        ArrayList<sc> scs = new ArrayList<>();
        String sql = "select * from sc";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        getMoreSc(scs,rs);
        closeConnection();
        return scs;
    }
    public ArrayList<sc> selectFromSCBySno(String studentNo) throws Exception{
        initConnection();
        ArrayList<sc> scs = new ArrayList<>();
        String sql = "select * from sc where studentNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentNo);
        ResultSet rs = ps.executeQuery();
        getMoreSc(scs,rs);
        closeConnection();
        return scs;
    }
    public ArrayList<sc> selectFromSCByCno(String courseNo) throws Exception{
        initConnection();
        ArrayList<sc> scs = new ArrayList<>();
        String sql = "select * from sc where CourseNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,courseNo);
        ResultSet rs = ps.executeQuery();
        getMoreSc(scs,rs);
        closeConnection();
        return scs;
    }
    private void getMoreSc(ArrayList<sc> scs , ResultSet rs) throws Exception{
        while(rs.next()){
            sc s = new sc();
            s.setStudentNo(rs.getString("StudentNo"));
            s.setCourseNo(rs.getString("CourseNo"));
            s.setGrade(rs.getString("Grade"));
            scs.add(s);
        }
    }

    public static void main(String[] args) throws Exception{
//        System.out.println(new scDataAccessObjects().insertSC("11111","00000001","88"));
//        System.out.println(new scDataAccessObjects().insertSC("11111","00000004","79"));
//        System.out.println(new scDataAccessObjects().insertSC("11111","00000006","90"));
//        System.out.println(new scDataAccessObjects().insertSC("22901111","00000002","92"));
//        System.out.println(new scDataAccessObjects().insertSC("22901111","00000004","87"));
//        System.out.println(new scDataAccessObjects().insertSC("22902222","00000005","100"));
//        System.out.println(new scDataAccessObjects().insertSC("22903333","00000003","49"));
//        System.out.println(new scDataAccessObjects().insertSC("22903333","00000002","77"));
//        System.out.println(new scDataAccessObjects().insertSC("22905555","00000004","97"));
//        System.out.println(new scDataAccessObjects().insertSC("22906666","00000003","66"));

    //    new scDataAccessObjects().updateSc("11111","00000001","90");

//        ArrayList<sc> scs = new ArrayList<>();
        //scs = new scDataAccessObjects().selectFromSC();
        //scs = new scDataAccessObjects().selectFromSCByCno("00000002");
        //scs = new scDataAccessObjects().selectFromSCBySno("22901111");
//        for(sc c : scs){
//            System.out.print(c.getStudentNo());
//            System.out.print(c.getCourseNo());
//            System.out.println();
//        }


       // System.out.println(new scDataAccessObjects().insertSC("11111","00000005","88"));
       // System.out.println(new scDataAccessObjects().deleteSC("11111","00000005") );
    }
}
