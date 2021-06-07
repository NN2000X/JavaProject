<%@ page import="service.UserService" %>
<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户修改</title>
</head>
<body>
<%
    User user = UserService.get(Integer.parseInt(request.getParameter("id")));
%>
<h3>请修改用户信息</h3>
<form action="/userModify?id=<%=user.getId()%>&action=username" method="post">
    修改用户名：
    <input type="text" name="newUsername">
    <input type="submit" value="  确 认 修 改 用 户 名  ">
</form>
<form action="/userModify?id=<%=user.getId()%>&action=password" method="post">
    修改密码：
    <input type="password" name="newPassword">
    <input type="submit" value="  确 认 修 改 密 码  ">
</form>
<form action="/userModify?id=<%=user.getId()%>&action=email" method="post">
    修改邮箱：
    <input type="text" name="newEmail">
    <input type="submit" value="  确 认 修 改 邮 箱  ">
</form>

<p><input type="button" value=" 返  回 " onclick="window.location.href='/page/adminMenu.jsp'"/></p>
</body>
</html>