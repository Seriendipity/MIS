package com.example.mis.service;

import com.example.mis.bean.Class;

import java.util.ArrayList;

public interface ClassService {
    /**
     *   向Class表中插入数据
     */
    public boolean insertClass(String classNo, String className, String classMajor,
                               String classDept, String studentNumber ) throws Exception;

    /**
     *   从Class表中按照班级号删除数据
     */
    public boolean deleteClass(String classNo) throws Exception;

    /**
     *   根据班级号对班级信息进行修改
     */
    public void updateClassInfo(String classNo,String className,String classMajor,
                                String classDept,String studentNumber) throws Exception;

    /**
     *  查找所有班级信息
     */

    public ArrayList<Class> selectFromClass() throws Exception;

    /**
     *  根据班级号查找信息
     */
    public Class selectFromClassByCno(String classNo) throws Exception;

    /**
     *   根据班级所属专业信息查找班级
     */
    public ArrayList<Class> selectFromClassWithClassMajor(String classMajor) throws Exception;

    /**
     *   根据班级所属系别查找班级
     */
    public ArrayList<Class> selectFromClassWithClassDept(String classDept) throws Exception;


}
