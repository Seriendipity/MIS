<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/28
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Student List</h1>
<table border="1">
    <tr>
        <th>StudentNo</th>
        <th>CourseNo</th>
        <th>Cid</th>
    </tr>
    <jsp:useBean id="sc" scope="request" type="java.util.ArrayList<com.example.mis.bean.sc>"/>
    <c:forEach var="item" items= "${sc}">
    <tr>
        <td>${item.studentNo}</td>
        <td>${item.courseNo}</td>
        <td>${item.cid}</td>
    </tr>
    </c:forEach>
</body>
</html>
