<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/27
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Student Registration Test</h1>
<form action="update_student" method="post">
  <div>
    <label for="student_no">学号：</label>
    <input type="text" id="student_no" name = "student_no" required>
  </div>
  <div>
    <label for="student_name">姓名：</label>
    <input type="text" id="student_name" name="student_name" required>
  </div>
  <div>
    <label for="class_no">班级号：</label>
    <input type="text" id="class_no" name="class_no" required>
  </div>
  <div>
    <label for="student_birthday">生日：</label>
    <input type="text" id="student_birthday" name="student_birthday" required>
  </div>
  <div>
    <label for="student_sex">性别：</label>
    <input type="text" id="student_sex" name="student_sex" required>
  </div>
  <div>
    <label for="total_credit">学分：</label>
    <input type="text" id="total_credit" name="total_credit" required>
  </div>
  <div>
    <label for="phone_number">电话号：</label>
    <input type="text" id="phone_number" name="phone_number" required>
  </div>
  <div>
    <label for="student_email">邮箱：</label>
    <input type="text" id="student_email" name="student_email" required>
  </div>
  <div>
    <button type="submit">提交</button>
  </div>
</form>
</body>
</html>
