<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品详情页面</title>
</head>
<body>
<h3>商品详情</h3>
<table>
    <tr>
        <th>名称：</th>
        <td>${product.name}</td>
    </tr>
    <tr>
        <th>价格：</th>
        <td>${product.price}</td>
    </tr>
    <tr>
        <th>库存：</th>
        <td>${product.stock}</td>
    </tr>
    <tr>
        <th>介绍：</th>
        <td>${product.description}</td>
    </tr>
</table>
<br>
<form action="/cartAdd" method="post">
    <input type="hidden" name="productid" value="${product.idproduct}">
    <input type="text" name="quantity" placeholder="加购数量">
    <input type="submit" value=" 加入购物车 ">
</form>
<br>
<br>
<p><input type="button" value=" 返 回 主 菜 单 " onclick="window.location.href='page/userMenu.jsp'"></p>
</body>
</html>
