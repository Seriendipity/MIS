<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/27
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>CLass Registration Test</h1>
  <form action="update_class" method="post">
    <div>
      <label for="class_no">班级号：</label>
      <input type="text" id="class_no" name = "class_no" required>
    </div>
    <div>
      <label for="class_name">班级名：</label>
      <input type="text" id="class_name" name="class_name" required>
    </div>
    <div>
      <label for="class_major">专业：</label>
      <input type="text" id="class_major" name="class_major" required>
    </div>
    <div>
      <label for="class_dept">系名：</label>
      <input type="text" id="class_dept" name="class_dept" required>
    </div>
    <div>
      <label for="student_number">班级人数：</label>
      <input type="text" id="student_number" name="student_number" required>
    </div>
    <div>
      <button type="submit">提交</button>
    </div>
  </form>
</body>
</html>
