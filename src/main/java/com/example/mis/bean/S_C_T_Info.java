package com.example.mis.bean;

/**
 * 辅助类，查看学生自己的已选课列表。课程名称、课程CID，老师姓名、授课语言
 */
public class S_C_T_Info {
    private String courseName;
    private String cid;
    private String teacherName;
    private String language;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
