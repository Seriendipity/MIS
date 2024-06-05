package com.example.mis.service;

import com.example.mis.bean.User;

import java.util.ArrayList;

public interface UserService {
    /**
     * 向User表中插入一个数据
     */
    public boolean insertUser(String userName , String password) throws Exception;

    /**
     * 从User表中删除一个数据
     */
    public boolean deleteUser(String userName) throws Exception;

    /**
     * 查询所有的用户
     */
    public ArrayList<com.example.mis.bean.User> selectUsers() throws Exception;

    /**
     * 返回指定用户名的用户
     */
    public User selectUser(String userName) throws Exception;

    /**
     * 修改用户信息
     */
    public void updateUser(String userName,String password) throws Exception;
}
