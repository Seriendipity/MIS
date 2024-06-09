package com.example.mis.bean;

/**
 * 辅助类，存放课程名和课程平均成绩
 */
public class CourseGrade {
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(String avgGrade) {
        this.avgGrade = avgGrade;
    }

    private String avgGrade;

}
