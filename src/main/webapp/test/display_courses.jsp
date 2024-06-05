<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/27
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <th>Language</th>
        <th>CourseNo</th>
        <th>Cid</th>
        <th>CourseCredit</th>
    </tr>
    <jsp:useBean id="tCInfos" scope="request" type="java.util.ArrayList"/>
    <c:forEach var="item" items= "${tCInfos}">
    <tr>
        <td>${item.courseName}</td>
        <td>${item.teacherName}</td>
        <td>${item.language}</td>
        <td>${item.courseNo}</td>
        <td>${item.cid}</td>
        <td>${item.courseCredit}</td>
    </tr>
    </c:forEach>
</body>
</html>
