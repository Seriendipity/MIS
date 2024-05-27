<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/27
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>CLass Registration Test</h1>
<form id = "courseForm" action="display_courses" method="post">
  <div>
    <button type="submit">提交</button>
  </div>
</form>
<%--<script>--%>
<%--  // 提交表单时刷新页面--%>
<%--  document.getElementById("courseForm").addEventListener("submit", function(event) {--%>
<%--    event.preventDefault(); // 阻止表单的默认提交行为--%>
<%--    window.location.href = "display_courses"; // 跳转到 DisplayCourses Servlet 的 URL--%>
<%--  });--%>
<%--</script>--%>
</body>
</html>
