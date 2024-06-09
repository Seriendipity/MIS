package com.example.mis.dao;
import com.example.mis.bean.teaching;
import com.example.mis.service.teachingService;
import java.sql.*;
import java.util.ArrayList;

public class teachingDataAccessObjects implements teachingService{
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

    @Override
    public boolean insertTeaching(String courseNo,String teacherNo,String language,String cid) throws Exception{
        initConnection();
        String sql = "insert into teaching(courseNo,teacherNo,language,cid) values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,courseNo);
        ps.setString(2,teacherNo);
        ps.setString(3,language);
        ps.setString(4,cid);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    @Override
    public boolean deleteTeaching(String courseNo,String teacherNo,String cid) throws Exception{
        initConnection();
        String sql = "delete from teaching where courseNo = ? and teacherNo = ? and cid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,courseNo);
        ps.setString(2,teacherNo);
        ps.setString(3,cid);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    @Override
    public void updateTeaching(String courseNo,String teacherNo,String language,String cid) throws Exception{
        initConnection();
        String sql = "update teaching set language = ?, courseNo = ? , teacherNo = ? where cid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,language);
        ps.setString(2,courseNo);
        ps.setString(3,teacherNo);
        ps.setString(4,cid);
        ps.executeUpdate();
        closeConnection();
    }

    @Override
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

    @Override
    public ArrayList<teaching> selectFromTeachingByTno(String teacherNo) throws Exception{
        initConnection();
        ArrayList<teaching> t = new ArrayList<>();
        String sql = "select * from teaching where teacherNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,teacherNo);
        ResultSet rs = ps.executeQuery();
        getMoreTeaching(t,rs);
        closeConnection();
        return t;
    }

    @Override
    public ArrayList<teaching> selectFromTeachingByLanguage(String language) throws Exception{
        initConnection();
        ArrayList<teaching> t = new ArrayList<>();
        String sql = "select * from teaching where language = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,language);
        ResultSet rs = ps.executeQuery();
        getMoreTeaching(t,rs);
        closeConnection();
        return t;
    }

    @Override
    public teaching selectFromTeachingByCid(String cid) throws Exception {
        initConnection();
        String sql = "select * from teaching where cid = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,cid);
        ResultSet rs = ps.executeQuery();
        teaching tg = getTeaching(rs);
        closeConnection();
        return tg;
    }

    //辅助方法
    private teaching getTeaching(ResultSet rs) throws Exception{
        teaching t = new teaching();
        if(rs.next()){
            t.setCid(rs.getString("cid"));
            t.setLanguage(rs.getString("language"));
            t.setTeacherNo(rs.getString("teacherNo"));
            t.setCourseNo(rs.getString("courseNo"));
            return t;
        }
        return null;
    }
    //辅助方法
    private void getMoreTeaching(ArrayList<teaching> t, ResultSet rs) throws Exception{
        while(rs.next()){
            teaching tg = new teaching();
            tg.setCourseNo(rs.getString("CourseNo"));
            tg.setTeacherNo(rs.getString("TeacherNo"));
            tg.setLanguage(rs.getString("Language"));
            tg.setCid(rs.getString("cid"));
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
        //new teachingDataAccessObjects().updateTeaching("00000001","1003","aa","9");
//        System.out.println(new teachingDataAccessObjects().selectFromTeachingByCid("2").getTeacherNo());
//        System.out.println(new teachingDataAccessObjects().selectFromTeachingByCid("2").getCourseNo());
//        teachingDataAccessObjects teachingDao = new teachingDataAccessObjects();
//        ArrayList<teaching> teachings = teachingDao.selectFromTeachingByTno("1001");
//        for(teaching t : teachings){
//            System.out.print(t.getCourseNo()+" ");
//            System.out.print(t.getCid());
//            System.out.println();
//        }

    }
}
