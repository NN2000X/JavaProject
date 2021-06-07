<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增用户</title>
</head>
<body>
<h3>请创建新的账号：</h3>
<form action="/userModify" method="post">
    <input type="hidden" name="action" value="user">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>
    E-mail：<input type="text" name="email"><br>
    <input type="submit" value=" 确 认 新 增 用 户 ">
</form>
<p><input type="button" value="  返 回 主 菜 单  " onclick="window.location.href='/page/adminMenu.jsp'"/></p>
</body>
</html>
