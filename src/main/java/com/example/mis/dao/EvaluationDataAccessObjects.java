package com.example.mis.dao;

import com.example.mis.bean.Evaluation;
import com.example.mis.service.EvaluationService;

import java.sql.*;
import java.util.ArrayList;

public class EvaluationDataAccessObjects implements EvaluationService {
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

    public boolean insertEvaluation(String studentNo,String courseNo,String teacherNo,
                                    String evaluationGrade,String evaluationComment) throws Exception{
        initConnection();
        String sql = "insert into evaluation(studentNo,courseNo,teacherNo,evaluationGrade,evaluationComment) values(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentNo);
        ps.setString(2,courseNo);
        ps.setString(3,teacherNo);
        ps.setString(4,evaluationGrade);
        ps.setString(5,evaluationComment);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;

    }

    public boolean deleteEvaluation(String studentNo,String courseNo,String teacherNo) throws Exception{
        initConnection();
        String sql = "delete from evaluation where studentNo = ? and courseNo = ? and teacherNo = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentNo);
        ps.setString(2,courseNo);
        ps.setString(3,teacherNo);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    public void updateEvaluation(String studentNo,String courseNo,String teacherNo,
                                 String evaluationGrade,String evaluationComment) throws Exception{
        initConnection();
        String sql = "update evaluation set evaluationGrade = ? , evaluationComment = ? where studentNo = ? and courseNo = ? and teacherNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,evaluationGrade);
        ps.setString(2,evaluationComment);
        ps.setString(3,studentNo);
        ps.setString(4,courseNo);
        ps.setString(5,teacherNo);
        ps.executeUpdate();
        closeConnection();
    }

    public ArrayList<Evaluation> selectFromEvaluation()throws Exception{
        initConnection();
        ArrayList<Evaluation> e = new ArrayList<>();
        String sql = "select * from evaluation";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        getMoreEvaluation(e,rs);
        closeConnection();
        return e;
    }

    public ArrayList<Evaluation> selectFromEvaluationByTno(String teacherNo) throws Exception{
        initConnection();
        ArrayList<Evaluation> e = new ArrayList<>();
        String sql = "select * from evaluation where teacherNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,teacherNo);
        ResultSet rs = ps.executeQuery();
        getMoreEvaluation(e,rs);
        closeConnection();
        return e;
    }
    public float selectAvgEvaluationByTno(String teacherNo) throws Exception{
        initConnection();
        String sql = "select avg(evaluationGrade) from evaluation where teacherNO = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,teacherNo);
        ResultSet rs = ps.executeQuery();
        rs.next();
        float avgGrade = rs.getInt(1);
        closeConnection();
        return avgGrade;
    }
    private void getMoreEvaluation(ArrayList<Evaluation> e,ResultSet rs)throws Exception{
        while(rs.next()){
            Evaluation evaluation = new Evaluation();
            evaluation.setCourseNo(rs.getString("CourseNo"));
            evaluation.setStudentNo(rs.getString("StudentNo"));
            evaluation.setEvaluationComment(rs.getString("EvaluationComment"));
            evaluation.setTeacherNo(rs.getString("TeacherNo"));
            evaluation.setEvaluationGrade(rs.getString("EvaluationGrade"));
            e.add(evaluation);
        }
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(new EvaluationDataAccessObjects().insertEvaluation("11111","00000001","1001","90","Good"));
//        System.out.println(new EvaluationDataAccessObjects().insertEvaluation("11111","00000004","1006","88","Fantastic"));
//        System.out.println(new EvaluationDataAccessObjects().insertEvaluation("22901111","00000002","1001","95","Best"));
//        System.out.println(new EvaluationDataAccessObjects().insertEvaluation("22906666","00000003","1004","60","Common"));

      //  new EvaluationDataAccessObjects().updateEvaluation("11111","00000001","1001","90","WORST");

//        ArrayList<Evaluation> evaluations = new ArrayList<>();
//        evaluations = new EvaluationDataAccessObjects().selectFromEvaluationByTno("1001");
//        for(Evaluation e : evaluations){
//            System.out.print(e.getStudentNo());
//            System.out.print(e.getEvaluationGrade());
//            System.out.println();
//        }

       // System.out.println(new EvaluationDataAccessObjects().selectAvgEvaluationByTno("1001"));

      //  System.out.println(new EvaluationDataAccessObjects().insertEvaluation("11111","00000005","1001","90","Good"));
     //   System.out.println(new EvaluationDataAccessObjects().deleteEvaluation("11111","00000005","1001"));
    }
}
