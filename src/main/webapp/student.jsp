<%--
  Created by IntelliJ IDEA.
  User: cxh1015
  Date: 2024/6/6
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@page import ="com.example.mis.bean.Student"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
      pageEncoding="UTF-8" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>学生操作界面</title>
    <link rel="stylesheet" type="text/css" href="css/user&admin.css">
    <link rel="icon" type="image/x-ico" href="images/stu.png">
</head>
<body>
<%
  //获取登录成功的学生信息
  Student student = (Student) session.getAttribute("student");
  String studentNo = student.getStudentNO();
  //判断用户是否登录
  if(student != null){
%>
<header>
  <div class="title">
    <span>学生操作界面</span>
  </div>
  <nav>
    <div class="student-info">
      <ul>
        <li><%=student.getStudentName()%></li>
        <li>同学</li>
        <li><a href="login.html">退出登录</a></li>
        <li><a href="login.html">返回首页</a></li>
      </ul>
    </div>
  </nav>
</header>

<main>
  <%}else{
    response.sendRedirect("login.html");
  }
  %>
  <div class="container">
    <div class="select">
      <h3>请选择操作</h3>
      <ul id="accordion"  class="accordion">
        <li>
          <div id="student_info" class="link">学生信息</div>
          <ul class="submenu">
            <li><a onclick ="query_all_information(<%= studentNo%>)">查看信息</a></li>
            <li><a onclick ="show_alter_information()"></a>修改信息</li>
          </ul>
        </li>
      <li>
        <div id="apply_course" class="link"></i>选课信息</div>
        <ul class="submenu">
          <li><a onclick="query_all(<%= studentNo %>)">查看已选课程</a></li>
          <li><a onclick="query_all_course()">查看可选修课程</a></li>
          <li><a onclick="show_apply_course(<%= studentNo%>)">申请选课</a></li>
          <li><a onclick="delete_course(<%= studentNo%>)">删除课程</a></li>
        </ul>
      </li>
      <li>
        <div class="link">课程成绩</div>
        <ul class="submenu">
          <li><a onclick="query_all_grade(<%= studentNo%>)">查看所有课程成绩</a></li>
          <li><a onclick="show_course_grade(<%= studentNo %>)">查看某门课程成绩</a></li>
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
<script src="js/student.js"></script>
</body>
</html>
