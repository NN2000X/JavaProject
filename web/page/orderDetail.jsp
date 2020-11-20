
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情页面</title>
</head>
<body>
<h3>这里是${order.idorder}号订单的详情页</h3>
<ul>
    <li>买家：${buyer.username}</li>
    <li>提交时间：${order.time}</li>
    <li>确认邮箱：${order.email}</li>
</ul>
<br>
<p>以下为订单详请</p>
<table border="1">
    <tr>
        <th>商品名</th>
        <th>单价</th>
        <th>数量</th>
    </tr>
    <c:forEach items="${products}" var="product" varStatus="loop">
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${details[loop.count-1].quantity}</td>
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