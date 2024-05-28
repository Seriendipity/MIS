<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/27
  Time: 0:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>SC Registration Test</h1>
<form action="delete_sc" method="post">
    <div>
        <label for="student_no">学号：</label>
        <input type="text" id="student_no" name = "student_no" required>
    </div>
    <div>
        <label for="course_no">课程号：</label>
        <input type="text" id="course_no" name="course_no" required>
    </div>
    <div>
        <label for="cid">课程ID：</label>
        <input type="text" id="cid" name="cid" required>
    </div>
    <div>
        <button type="submit">提交</button>
    </div>
</form>
</body>
</html>
