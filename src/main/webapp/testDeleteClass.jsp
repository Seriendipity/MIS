<%--
  Created by IntelliJ IDEA.
  User: MHB
  Date: 2024/5/27
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>Delete CLass Test</h1>
  <form action="delete_class" method="post">
    <div>
      <label for="class_no">班级号：</label>
      <input type="text" id="class_no" name = "class_no" required>
    </div>
    <div>
      <button type="submit">提交</button>
    </div>
  </form>
</body>
</html>
