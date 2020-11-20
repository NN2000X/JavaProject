<%@ page import="mall.service.OrderService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单查询页面</title>
</head>
<body>
<h3>以下是已确认的订单</h3>
<table border="1">
    <tr>
        <th>订单号</th>
        <th>买家</th>
        <th>总价</th>
        <th>时间</th>
        <th>E-mail</th>
    </tr>
    <c:forEach items="${orders}" var="order" varStatus="loop">
        <tr>
            <td><a href="/orderDetail?idorder=${order.idorder}">${order.idorder}</a></td>
            <td>${buyers[loop.count-1].username}</td>
            <td>${totalPrices[loop.count-1]}</td>
            <td>${order.time}</td>
            <td>${order.email}</td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<br>
<br>
<p><input type="button" value=" 返 回 主 菜 单 " onclick="window.location.href='page/adminMenu.jsp'"></p>
</body>
</html>
