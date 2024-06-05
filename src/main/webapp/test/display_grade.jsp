<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/28
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Course List</h1>
<table border="1">
  <tr>
    <th>CourseName</th>
    <th>TeacherName</th>
    <th>Cid</th>
    <th>Grade</th>

  </tr>
  <jsp:useBean id="sCInfos" scope="request" type="java.util.ArrayList<com.example.mis.bean.S_C_Info>"/>
  <c:forEach var="item" items= "${sCInfos}">
  <tr>
    <td>${item.courseName}</td>
    <td>${item.teacherName}</td>
    <td>${item.cid}</td>
    <td>${item.grade}</td>
  </tr>
  </c:forEach>
</body>
</html>
