package com.example.mis.bean;

/**
 * 考试表。存放课程CID，考试时间
 */
public class Exam {
    private String cid;
    private String examDate;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }
}
