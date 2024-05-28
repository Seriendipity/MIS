package com.example.mis.dao;

import com.example.mis.bean.Exam;
import com.example.mis.service.ExamService;

import java.sql.*;
import java.util.ArrayList;

public class examDataAccessObjects  implements ExamService{
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

    @Override
    public boolean insertExam(String cid, String examDate) throws Exception {
        initConnection();
        String sql = "insert into exam(cid,examDate) values(?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,cid);
        ps.setString(2,examDate);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    @Override
    public boolean deleteExam(String cid) throws Exception {
        initConnection();
        String sql = "delete from exam where cid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,cid);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    @Override
    public void updateExam(String cid, String examDate) throws Exception {
        initConnection();
        String sql = "update exam set examDate = ? where cid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,examDate);
        ps.setString(2,cid);
        ps.executeUpdate();
        closeConnection();
    }

    @Override
    public ArrayList<Exam> selectExam() throws Exception {
        initConnection();
        ArrayList<Exam> exams = new ArrayList<>();
        String sql = "select * from exam";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        getMoreExam(exams,rs);
        closeConnection();
        return exams;
    }

    @Override
    public Exam selectExamByCid(String cid) throws Exception {
        initConnection();
        String sql = "select * from exam where cid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,cid);
        ResultSet rs = ps.executeQuery();
        Exam e = getExam(rs);
        closeConnection();
        return e;
    }

    //辅助方法
    private Exam getExam(ResultSet rs) throws Exception{
        Exam e = new Exam();
        if(rs.next()){
            e.setCid(rs.getString("cid"));
            e.setExamDate(rs.getString("examDate"));
        }
        return e;
    }

    //辅助方法
    private void getMoreExam(ArrayList<Exam> exams , ResultSet rs) throws Exception{
        while(rs.next()){
            Exam e = new Exam();
            e.setCid(rs.getString("cid"));
            e.setExamDate(rs.getString("examDate"));
            exams.add(e);
        }

    }

    public static void main(String[] args) throws Exception{
//        new examDataAccessObjects().insertExam("1","2024-05-27");
//        new examDataAccessObjects().insertExam("2","2024-05-02");
//        new examDataAccessObjects().insertExam("3","2024-05-13");
//        new examDataAccessObjects().insertExam("4","2024-05-22");
//        new examDataAccessObjects().insertExam("5","2024-05-19");
       // new examDataAccessObjects().updateExam("1","2011-01-01");

//        ArrayList<Exam> exams = new ArrayList<>();
//        exams = new examDataAccessObjects().selectExam();
//        for(Exam e : exams){
//            System.out.print(e.getCid()+" ");
//            System.out.print(e.getExamDate());
//            System.out.println();
//        }

        //System.out.println(new examDataAccessObjects().selectExamByCid("2").getExamDate());
    }
}
