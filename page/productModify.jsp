<%@ page import="entity.Product" %>
<%@ page import="service.ProductService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品修改</title>
</head>
<body>
<% if (request.getParameter("action").equals("modify")) {
    Product product = ProductService.get(Integer.parseInt(request.getParameter("id")));
%>
<h3>请修改商品信息</h3>
<form action="/productModify?id=<%=product.getId()%>&action=name" method="post">
    修改名称：
    <input type="text" name="newName">
    （原名称：<%=product.getName()%>）
    <input type="submit" value="  确 认 修 改 名 称  ">
</form>
<form action="/productModify?id=<%=product.getId()%>&action=price" method="post">
    修改价格：
    <input type="text" name="newPrice">
    （原价格：<%=product.getPrice()%>）
    <input type="submit" value="  确 认 修 改 价 格  ">
</form>
<form action="/productModify?id=<%=product.getId()%>&action=stock" method="post">
    修改库存：
    <input type="text" name="newStock">
    （原库存：<%=product.getStock()%>）
    <input type="submit" value="  确 认 修 改 库 存  ">
</form>
<form action="/productModify?id=<%=product.getId()%>&action=description" method="post">
    修改介绍：
    <input type="text" name="newDescription">
    （原介绍：<%=product.getDescription()%>）
    <input type="submit" value="  确 认 修 改 介 绍  ">
</form>

<input type="button" value="  下 架 此 商 品  "
       onclick="window.location.href='/productModify?id=<%=product.getId()%>&action=suspend'">
<%
} else if (request.getParameter("action").equals("add")) {
%>
<h3>请创建新的商品：</h3>
<form action="/productModify?action=add" method="post">
    名称：<input type="text" name="name"><br>
    价格：<input type="text" name="price"><br>
    库存：<input type="text" name="stock"><br>
    简介：<input type="text" name="description"><br><br>
    <input type="submit" value=" 确 认 添 加 商 品 ">
</form>
<%}%>
<p><input type="button" value=" 返  回 " onclick="window.location.href='/page/saleMenu.jsp'"/></p>
</body>
</html>
