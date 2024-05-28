package com.example.mis.bean;

/**
 *  表Class，其中存放班级号、班级名称、班级专业、班级系名、班级人数
 */
public class Class {
    private String classNo;
    private String className;
    private String classMajor;
    private String classDept;
    private String studentNumber;

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassMajor() {
        return classMajor;
    }

    public void setClassMajor(String classMajor) {
        this.classMajor = classMajor;
    }

    public String getClassDept() {
        return classDept;
    }

    public void setClassDept(String classDept) {
        this.classDept = classDept;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
