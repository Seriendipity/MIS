<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/28
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Exam List</h1>
<table border="1">
  <tr>
    <th>Cid</th>
    <th>CourseName</th>
    <th>ExamDate</th>
  </tr>
  <jsp:useBean id="exams" scope="request" type="java.util.ArrayList"/>
  <jsp:useBean id="courseNames" scope="request" type="java.util.ArrayList"/>
  <c:forEach var="item" items="${exams}" varStatus="loop">
  <tr>
    <td>${item.cid}</td>
    <td>${courseNames[loop.index]}</td>
    <td>${item.examDate}</td>
  </tr>
  </c:forEach>
</body>
</html>
