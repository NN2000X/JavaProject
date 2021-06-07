<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<%
    List<User> users = UserService.getAll();
%>
<%if (!users.isEmpty()) {%>
<h3>以下为已注册账号：</h3>
<table border="1">
    <tr>
        <th>用户ID</th>
        <th>用户名</th>
        <th>邮箱</th>
    </tr>
    <%for (User user : users) {%>
    <tr>
        <td><a href="/page/userModify.jsp?id=<%=user.getId()%>"><%=user.getId()%></a></td>
        <td><%=user.getUsername()%></td>
        <td><%=user.getEmail()%></td>
    </tr>
    <%}%>
</table>
<%} %>
<p>
    <button onclick="window.location.href='/page/userAdd.jsp'">
        点 击 新 增 一 个 账 号
    </button>
</p>
<p><input type="button" value="  返 回 主 菜 单  " onclick="window.location.href='/page/adminMenu.jsp'"/></p>
</body>
</html>
