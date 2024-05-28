<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/28
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Selected Course List</h1>
<table border="1">
  <tr>
    <th>CourseName</th>
    <th>TeacherName</th>
    <th>Cid</th>
    <th>Language</th>

  </tr>
  <jsp:useBean id="sCTInfos" scope="request" type="java.util.ArrayList<com.example.mis.bean.S_C_T_Info>"/>
  <c:forEach var="item" items= "${sCTInfos}">
  <tr>
    <td>${item.courseName}</td>
    <td>${item.teacherName}</td>
    <td>${item.cid}</td>
    <td>${item.language}</td>
  </tr>
  </c:forEach>
</body>
</html>
