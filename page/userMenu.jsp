<%--
  Created by IntelliJ IDEA.
  User: nn2000x
  Date: 2021/2/11
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户菜单</title>
</head>
<body>
<fieldset>
    <legend>请选择功能</legend>
    <p><input type="button" value="  开 始 购 物  " onclick="window.location.href='/page/showProducts.jsp'"/></p>
    <p><input type="button" value="  查 看 购 物 车  " onclick="window.location.href='/page/cart.jsp'"/></p>
    <p><input type="button" value="  退 出 登 录  " onclick="window.location.href='/logout'"/></p>
</fieldset>
</body>
</html>
