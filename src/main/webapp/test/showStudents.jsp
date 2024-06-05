<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/28
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>CLass Registration Test</h1>
<form id = "displayStudents" action="display_student_for_teacher" method="post">
    <div>
        <label for="cid">CID：</label>
        <input type="text" id="cid" name = "cid" required>
    </div>
    <div>
        <button type="submit">提交</button>
    </div>
</form>
</body>
</html>
