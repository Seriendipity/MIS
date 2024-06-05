<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/27
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Teacher Registration Test</h1>
<form action="add_teacher" method="post">
    <div>
        <label for="teacher_no">教师号：</label>
        <input type="text" id="teacher_no" name = "teacher_no" required>
    </div>
    <div>
        <label for="teacher_name">教师姓名：</label>
        <input type="text" id="teacher_name" name="teacher_name" required>
    </div>
    <div>
        <label for="teacher_sex">性别：</label>
        <input type="text" id="teacher_sex" name="teacher_sex" required>
    </div>
    <div>
        <label for="teacher_birthday">教师生日：</label>
        <input type="text" id="teacher_birthday" name="teacher_birthday" required>
    </div>
    <div>
        <label for="teacher_title">教师职称：</label>
        <input type="text" id="teacher_title" name="teacher_title" required>
    </div>
    <div>
        <label for="teacher_email">教师邮箱：</label>
        <input type="text" id="teacher_email" name="teacher_email" required>
    </div>
    <div>
        <label for="password">密码：</label>
        <input type="text" id="password" name="password" required>
    </div>
    <div>
        <button type="submit">提交</button>
    </div>
</form>
</body>
</html>
