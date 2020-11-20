<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车页面</title>
</head>
<body>
<% if (request.getAttribute("shortDetails") == null) { %>
<h2>你的购物车还空空如也~</h2><br>
<input type="button" value=" 开 始 购 物 " onclick="window.location.href='/catalogSelect?idcatalog=1'">
<input type="button" value=" 返 回 主 菜 单 " onclick="window.location.href='page/userMenu.jsp'">
<% } else { %>
<h3>购物车里有这些宝贝：</h3>
<table border="1">
    <tr>
        <th>商品名</th>
        <th>价格</th>
        <th>数量</th>
        <th>小计</th>
    </tr>
    <c:forEach items="${shortDetails}" var="shortDetail">
        <tr>
            <td><a href="/productDetail?idproduct=${shortDetail.idproduct}">${shortDetail.name}</a></td>
            <td>${shortDetail.price}</td>
            <td>${shortDetail.quantity}</td>
            <td>${shortDetail.subtotal}</td>
        </tr>
    </c:forEach>
</table>
<p>总价合计：${totalPrice}元</p>
<br>
<br>
<form action="/commitCart" method="post">
    <input type="hidden" name="idorder" value="${idorder}">
    <input type="hidden" name="totalPrice" value="${totalPrice}">
    <input type="text" name="email" placeholder="确认邮箱"><br>
    <br>
    <input type="submit" value=" 提 交 订 单 ">
</form>
<% } %>

</body>
</html>
