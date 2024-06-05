<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/27
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Teaching Registration Test</h1>
<form action="delete_teaching" method="post">
  <div>
    <label for="course_no">课程号：</label>
    <input type="text" id="course_no" name = "course_no" required>
  </div>
  <div>
    <label for="teacher_no">教师号：</label>
    <input type="text" id="teacher_no" name="teacher_no" required>
  </div>
  <div>
    <label for="cid">CID：</label>
    <input type="text" id="cid" name="cid" required>
  </div>
  <div>
    <button type="submit">提交</button>
  </div>
</form>
</body>
</html>
