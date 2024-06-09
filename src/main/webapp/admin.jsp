<%--
  Created by IntelliJ IDEA.
  User: cxh1015
  Date: 2024/5/28
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.example.mis.bean.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>管理员操作界面</title>
    <link rel="stylesheet" type="text/css" href="css/user&admin.css">
    <link rel="icon" type="image/x-ico" href="images/stu.png">
</head>

<body>
<%
    //获取登录成功的用户信息
    Admin admin = (Admin) session.getAttribute("admin");
    //判断用户是否登录
    if(admin != null){
%>
<header>
    <div class="title">
        <span>管理员操作界面</span>
    </div>
    <nav>
        <div class="userinfo">
            <ul>
                <li><%=admin.getName() %></li>
                <li>管理员</li>>
                <li><a href="login.html">退出登录</a></li>
                <li><a href="login.html">返回首页</a></li>
            </ul>
        </div>
    </nav>
</header>

<main>
    <%
        }else{
            response.sendRedirect("login.html");
        }
    %>
    <div class="container">
        <div class="select">
            <h3>请选择操作</h3>
            <ul id="accordion" class="accordion">
                <li>
                    <div id="user-info" class="link"></i>用户信息管理</div>
                    <ul class="submenu">
                        <li><a onclick="query_all('user')">查看所有用户</a></li>
                        <li><a onclick="show_insert_user()">新增用户信息</a></li>
                        <li><a onclick="show_delete('user')">删除指定用户</a></li>
                        <li><a onclick="show_alter('user')">修改用户信息</a></li>
                    </ul>
                </li>
                <li>
                    <div class="link">班级信息管理</div>
                    <ul class="submenu">
                        <li><a onclick="query_all('class')">查看所有班级</a></li>
                        <li><a onclick="show_insert_class()">新增班级信息</a></li>
                        <li><a onclick="show_delete('class')">删除指定班级</a></li>
                        <li><a onclick="show_alter('class')">修改班级信息</a></li>
                    </ul>
                </li>
                <li>
                    <div class="link">学生信息管理</div>
                    <ul class="submenu">
                        <li><a  onclick="query_all('student')">查看所有学生</a></li>
                        <li><a onclick="show_insert_student()">新增学生信息</a></li>
                        <li><a onclick="show_delete('student')">删除指定学生</a></li>
                        <li><a onclick="show_alter('student')">修改学生信息</a></li>
                    </ul>
                </li>
                <li>
                    <div class="link">教师信息管理</div>
                    <ul class="submenu">
                        <li><a  onclick="query_all('teacher')">查看所有教师</a></li>
                        <li><a onclick="show_insert_teacher()">新增教师信息</a></li>
                        <li><a onclick="show_delete('teacher')">删除指定学生</a></li>
                        <li><a onclick="show_alter('teacher')">修改教师信息</a></li>
                    </ul>
                </li>
                <li>
                    <div class="link">课程信息管理</div>
                    <ul class="submenu">
                        <li><a onclick="query_all('courses')">查看所有课程信息</a></li>
                        <li><a onclick="show_insert_course()">新增课程信息</a></li>
                        <li><a onclick="show_delete('course')">删除课程信息</a></li>
                        <li><a onclick="show_alter('course')">修改课程信息</a></li>
                        <li><a onclick="course_avg()">查询课程平均分</a></li>
                        <li><a onclick="fail_rate()">查询课程不及格率</a></li>
                        <li><a onclick="show_course_ranking()">查询课程排名情况</a></li>
                    </ul>
                </li>
                <li>
                    <div class="link">任课信息管理</div>
                    <ul class="submenu">
                        <li><a onclick="query_all('course')">查看所有任课信息</a></li>
                        <li><a onclick="show_insert_teaching()">新增任课信息</a></li>
                        <li><a onclick="show_delete('teaching')">删除任课信息</a></li>
                        <li><a onclick="show_alter('teaching')">修改任课信息</a></li>
                    </ul>
                </li>
                <li>
                    <div class="link">学生成绩管理</div>
                    <ul class="submenu">
                        <li><a  onclick="query_all('sc')">查看全部学生成绩</a></li>
<%--                        <li><a onclick="show_insert_sc()">新增学生成绩记录</a></li>--%>
<%--                        <li><a onclick="show_delete('sc')">删除学生成绩记录</a></li>--%>
<%--                        <li><a onclick="show_alter('sc')">修改学生成绩记录</a></li>--%>
                    </ul>
                </li>
            </ul>
        </div>

        <div id="result" class="result">
            <p class="welcome">欢迎使用学生信息管理系统！</p>
        </div>
    </div>
</main>

<footer>
    <div class="copyright">
        &copy; Copyright. All rights reserved. Design by <a href="https://github.com/tomacx/">tomacx and her friends</a>
    </div>
</footer>

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/admin.js"></script>
</body>
</html>