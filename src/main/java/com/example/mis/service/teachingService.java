package com.example.mis.service;

import com.example.mis.bean.teaching;

import java.util.ArrayList;

public interface teachingService {
    /**
     *  向teaching表中插入一行数据
     */
    public boolean insertTeaching(String courseNo,String teacherNo,String language,String cid) throws Exception;

    /**
     *  根据课程号和教师号从teaching中删除一行数据
     */
    public boolean deleteTeaching(String courseNo,String teacherNo,String cid) throws Exception;

    /**
     *  根据课程号和教师号在teaching中更改指定教师的教课信息
     */
    public void updateTeaching(String courseNo,String teacherNo,String language,String cid) throws Exception;

    /**
     *  返回所有的教课信息
     */
    public ArrayList<teaching> selectFromTeaching() throws Exception;

    /**
     *  返回指定教师号的所有教课信息
     */
    public ArrayList<teaching> selectFromTeachingByTno(String teacherNo) throws Exception;

    /**
     *  返回指定cid的教师信息
     */
    public teaching selectFromTeachingByCid(String cid) throws Exception;

    /**
     *  返回以某种语言教授课程的所有教课信息
     */
    public ArrayList<teaching> selectFromTeachingByLanguage(String language) throws Exception;
}
