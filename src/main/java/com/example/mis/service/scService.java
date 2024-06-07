package com.example.mis.service;

import com.example.mis.bean.sc;

import java.util.ArrayList;

public interface scService {
    /**
     *  向sc表中插入一行数据
     */
    public boolean insertSC(String studentNo,String courseNo,String grade,String cid) throws Exception;

    /**
     *  根据学号和课程号，从sc表中删除一行数据
     */
    public boolean deleteSC(String studentNo,String courseNo,String cid) throws Exception;

    /**
     *  根据学号和课程号，从sc表中修改一行数据
     */
    public void updateSc(String studentNo,String courseNo,String grade,String cid) throws Exception;

    /**
     *  返回所有的 学生-选课 信息
     */
    public ArrayList<sc> selectFromSC() throws Exception;

    /**
     *  返回指定学号的 学生-选课 信息
     */
    public ArrayList<sc> selectFromSCBySno(String studentNo) throws Exception;

    /**
     *  返回指定课程号的 学生-选课 信息
     */
    public ArrayList<sc> selectFromSCByCno(String courseNo) throws Exception;

    /**
     *  返回指定cid的所有学生选课信息
     */
    public ArrayList<sc> selectFromSCByCid(String cid) throws Exception;

    /**
     * 返回指定学号、课程号、cid的学生选课信息
     */
    public sc selectFromScBySnoCnoCid(String Sno,String Cno,String cid) throws Exception;

}
