package com.example.mis.dao;

import com.example.mis.bean.User;
import com.example.mis.service.UserService;

import java.sql.*;
import java.util.ArrayList;

public class UserDataAccessObjects implements UserService {
    private Connection conn = null;
    //初始化Connection
    private void initConnection() throws Exception{
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mis","root","123456");
    }
    //关闭Connection
    private void closeConnection() throws Exception{
        conn.close();
    }

    @Override
    public boolean insertUser(String userName, String password) throws Exception {
        initConnection();
        String sql = "insert into user(userName,password) values(?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,userName);
        ps.setString(2,password);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    @Override
    public boolean deleteUser(String userName) throws Exception {
        initConnection();
        String sql = "delete from user where userName = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,userName);
        int SQLCA = ps.executeUpdate();
        closeConnection();
        return SQLCA == 1;
    }

    @Override
    public ArrayList<User> selectUsers() throws Exception {
        initConnection();
        String sql = "select * from user";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        ArrayList<User> users = new ArrayList<>();
        getMoreUser(users,rs);
        closeConnection();
        return users;
    }

    @Override
    public User selectUser(String userName) throws Exception {
        initConnection();
        String sql = "select * from user where userName =?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,userName);
        ResultSet rs = ps.executeQuery();
        User u = getUser(rs);
        closeConnection();
        return u;
    }

    @Override
    public void updateUser(String userName, String password) throws Exception {
        initConnection();
        String sql = "update user set password = ? where userName = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,password);
        ps.setString(2,userName);
        int SQLCA = ps.executeUpdate();
        closeConnection();
    }

    private void getMoreUser(ArrayList<User> users,ResultSet rs) throws SQLException {
        while (rs.next()){
            User u = new User();
            u.setUserName(rs.getString("UserName"));
            u.setPassword(rs.getString("password"));
            users.add(u);
        }
    }

    private User getUser(ResultSet rs) throws SQLException {
        User u = new User();
        if(rs.next()){
            u.setPassword(rs.getString("password"));
            u.setUserName(rs.getString("userName"));
        }
        return u;
    }


    public static void main(String[] args) throws Exception {
        UserDataAccessObjects userDao = new UserDataAccessObjects();
        //System.out.println(userDao.insertUser("1122","123456"));

//        ArrayList<User> users = userDao.selectUsers();
//        for(User u : users){
//            System.out.print(u.getUserName() + " ");
//            System.out.print(u.getPassword());
//            System.out.println();
//        }
//        User u = userDao.selectUser("1001");
//        System.out.println(u.getUserName() + u.getPassword());

//        userDao.updateUser("1001","abcd");
//        User u = userDao.selectUser("1001");
//        System.out.println(u.getUserName() + u.getPassword());
//        System.out.println();

     //   System.out.println(userDao.deleteUser("1122"));
    }
}
