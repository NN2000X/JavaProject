<%@ page import="mall.entity.Catalog" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品修改页面</title>
</head>
<body>
<% if (request.getParameter("action").equals("insert")) {%>
<h3>请在当前分类中创建新的商品：</h3>
<form action="/productModify" method="post">
    <input type="hidden" name="catalogid" value=<%=request.getParameter("catalogid")%>>
    <input type="hidden" name="action" value="product">
    名称：<input type="text" name="name"><br>
    价格：<input type="text" name="price"><br>
    库存：<input type="text" name="stock"><br>
    简介：<input type="text" name="description"><br><br>
    <input type="submit" value=" 确 认 添 加 商 品 ">
</form>
<%} else if (request.getParameter("action").equals("modify")) {%>
<h3>请修改商品信息</h3>
<form action="/productModify" method="post">
    修改名称：
    <input type="hidden" name="idproduct" value=<%=request.getParameter("idproduct")%>>
    <input type="hidden" name="action" value="name">
    <input type="text" name="newName">
    <input type="submit" value="确认修改名称">
</form>
<form action="/productModify" method="post">
    修改价格：
    <input type="hidden" name="idproduct" value=<%=request.getParameter("idproduct")%>>
    <input type="hidden" name="action" value="price">
    <input type="text" name="newPrice">
    <input type="submit" value="确认修改价格">
</form>
<form action="/productModify" method="post">
    修改库存：
    <input type="hidden" name="idproduct" value=<%=request.getParameter("idproduct")%>>
    <input type="hidden" name="action" value="stock">
    <input type="text" name="newStock">
    <input type="submit" value="确认修改库存">
</form>
<form action="/productModify" method="post">
    修改分类：
    <input type="hidden" name="idproduct" value=<%=request.getParameter("idproduct")%>>
    <input type="hidden" name="action" value="catalog">
    <input type="text" name="newCatalogName" placeholder="输入划入的分类名称">
    <input type="submit" value="确认修改分类">
</form>
<form action="/productModify" method="post">
    修改介绍：
    <input type="hidden" name="idproduct" value=<%=request.getParameter("idproduct")%>>
    <input type="hidden" name="action" value="description">
    <input type="text" name="newDescription">
    <input type="submit" value="确认修改介绍">
</form>


<br>
<input type="button" value=" 删 除 此 商 品 "
       onclick="window.location.href='/productModify?idproduct=<%=request.getParameter("idproduct")%>&action=delete'">
<%}%>
</body>
</html>
