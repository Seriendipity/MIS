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
    <th>CourseNo</th>
    <th>CourseName</th>
    <th>CourseCredit</th>
  </tr>
  <jsp:useBean id="courses" scope="request" type="java.util.ArrayList"/>
  <c:forEach var="course" items= "${courses}">
      <tr>
        <td>${course.courseNo}</td>
        <td>${course.courseName}</td>
        <td>${course.courseCredit}</td>
      </tr>
  </c:forEach>
</table>
</body>
</html>
