package com.example.mis.service;

import com.example.mis.bean.Evaluation;

import java.util.ArrayList;

public interface EvaluationService {
    /**
     *  向评教信息表中插入一行数据
     */
    public boolean insertEvaluation(String studentNo,String courseNo,String teacherNo,
                                    String evaluationGrade,String evaluationComment) throws Exception;

    /**
     *  根据学号，课程号、教师号从评教表中删除一行数据
     */
    public boolean deleteEvaluation(String studentNo,String courseNo,String teacherNo) throws Exception;

    /**
     * 根据学号，课程号、教师号从评教表中更改一行数据
     */
    public void updateEvaluation(String studentNo,String courseNo,String teacherNo,
                                 String evaluationGrade,String evaluationComment) throws Exception;

    /**
     *  返回所有的评教信息
     */
    public ArrayList<Evaluation> selectFromEvaluation()throws Exception;

    /**
     *  根据教师号返回指定教师的所有评教信息
     */
    public ArrayList<Evaluation> selectFromEvaluationByTno(String teacherNo) throws Exception;

    /**
     *  返回指定教师号的评教成绩
     */
    public float selectAvgEvaluationByTno(String teacherNo) throws Exception;
}
