package com.example.mis.service;

import com.example.mis.bean.Exam;

import java.util.ArrayList;

public interface ExamService {
    /**
     *  插入考试时间安排
     */
    public boolean insertExam(String cid,String examDate) throws Exception;

    /**
     *  根据cid删除对应的考试计划
     */
    public boolean deleteExam(String cid) throws Exception;

    /**
     *  根据cid修改对应的考试安排
     */
    public void updateExam(String cid ,String examDate) throws Exception;

    /**
     *  查找所有的考试安排
     */
    public ArrayList<Exam> selectExam() throws Exception;

    /**
     *  根据cid信息返回所有的考试信息
     */
    public Exam selectExamByCid(String cid) throws Exception;
}
