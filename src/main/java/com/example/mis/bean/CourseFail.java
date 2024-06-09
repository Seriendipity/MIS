package com.example.mis.bean;

/**
 * 辅助类，存放课程名称和不及格率
 */
public class CourseFail {
    private String courseName;
    private String failRate;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFailRate() {
        return failRate;
    }

    public void setFailRate(String failRate) {
        this.failRate = failRate;
    }
}
