package com.example.mis.bean;

public class Evaluation {
    private String studentNo;
    private String courseNo;
    private String teacherNo;
    private int evaluationGrade;
    private String evaluationComment;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public int getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(int evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public String getEvaluationComment() {
        return evaluationComment;
    }

    public void setEvaluationComment(String evaluationComment) {
        this.evaluationComment = evaluationComment;
    }
}