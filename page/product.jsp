<%@ page import="service.ProductService" %>
<%@ page import="entity.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品详情</title>
</head>
<body>
<%
    Product product= ProductService.get(Integer.parseInt(request.getParameter("id")));
%>
<h3>商品详情</h3>
<table>
    <tr>
        <th>名称：</th>
        <td><%=product.getName()%></td>
    </tr>
    <tr>
        <th>价格：</th>
        <td><%=product.getPrice()%></td>
    </tr>
    <tr>
        <th>库存：</th>
        <td><%=product.getStock()%></td>
    </tr>
    <tr>
        <th>介绍：</th>
        <td><%=product.getDescription()%></td>
    </tr>
</table>
<br>
<form action="/add2Cart?id=<%=product.getId()%>" method="post">
    <input type="text" name="quantity" placeholder=" 加 购 数 量 ">
    <input type="submit" value="  加 入 购 物 车  ">
</form>
<br>
<br>
<p><input type="button" value=" 返 回 主 菜 单 " onclick="window.location.href='/page/userMenu.jsp'"></p>
</body>
</html>
