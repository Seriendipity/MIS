<%--
  Created by IntelliJ IDEA.
  User: cxh1015
  Date: 2024/6/6
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.example.mis.bean.Teacher"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
        pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>教师操作界面</title>
    <link rel="stylesheet" type="text/css" href="css/user&admin.css">
    <link rel="icon" type="image/x-ico" href="images/stu.png">
</head>
<body>
<%
        //获取教师的信息
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        //判断用户是否登录
        if(teacher != null){
%>
<header>
    <div class="title">
        <span>教师操作界面</span>
    </div>
    <nav>
        <div class="userinfo">
            <ul>
                <li><%=teacher.getTeacherName() %></li>
                <li>老师，您好！</li>
                <li><a href="login.html">退出登录</a></li>
                <li><a href="login.html">返回首页</a></li>
            </ul>
        </div>
    </nav>
</header>

<main><% }else{
            response.sendRedirect("login.html");
}%>
    <div class="container">
        <div class="selcet">
            <h3>请选择操作</h3>
            <ul id="accordion" class="accordion">
                <li>
                    <div id="teacher-info" class="link">教师信息</div>
                    <ul class="submenu">
                        <li><a onclick="query_teacher({Teacher : teacher})">查看信息</a></li>
                        <li><a onclick="show_alter('teacher')">修改信息</a></li>
                    </ul>
                </li>
                <li><div class="link">课程信息</div>
                    <ul class="submenu">
                        <TODO:这两个还没写></TODO:这两个还没写>
                        <li><a onclick="query_all(teacher)">查看教授的课程</a></li>
                        <li><a onclick="show_student('sc')">查看选课的学生名单</a></li>
                    </ul>
                </li>
                <li>
                    <div class="link">成绩登记</div>
                    <ul class="submenu">
                        <li><a onclick="query_course_all()">查看指定课程所有学生成绩</a></li>
                        <li><a onclick="show_insert_grade()">添加成绩</a></li>
                        <li><a onclick="show_alter('sc')">修改成绩</a></li>
                        <li><a onclick="course_avg()">查看课程平均分</a></li>
                        <li><a onclick="fail_rate()">查询课程不及格率</a></li>
                        <li><a onclick="show_course_ranking()">查询课程排名情况</a></li>
                    </ul>
                </li>
            </ul>
        </div>

        <div id="result" class="result">
            <p class="welcome">欢迎使用学生信息管理系统</p>
        </div>
    </div>
</main>

<footer>
    <div class="copyright">
        &copy; Copyright. All rights reserved. Design by <a href="https://github.com/tomacx/">tomacx and her friends</a>
    </div>
</footer>

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/teacher.js"></script>
</body>
</html>