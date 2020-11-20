<%@ page import="mall.entity.Catalog" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!------ 传进来的参数有currentCatalog和catalogs/products两个----->
<html>
<head>
    <title>分类修改页面</title>
</head>
<body>
<h3>当前分类是：${currentCatalog.name}</h3>
可以对当前分类重命名：
<form style="display: inline" action="/catalogModify?idcatalog=${currentCatalog.idcatalog}&action=update" method="post">
    <input type="text" name="newName" placeholder="新的分类名">
    <input type="submit" value=" 确 定 ">
</form>

<% // ALL分类不允许删除
    if (!((Catalog) request.getAttribute("currentCatalog")).getIdcatalog().equals(1)) { %>
或谨慎地
<input type="button" onclick="window.location.href='/catalogModify?idcatalog=${currentCatalog.idcatalog}&action=delete'"
       value=" 删 除 此 分 类 及 以 下 商 品 ">
<% } %>
<br>
<br>
<% // catalogs参数非空说明不是最低类，则展示下面的分类
    if (request.getAttribute("catalogs") != null) { %>
<h3>此分类下存在：</h3>
<ul>
    <c:forEach var="catalog" items="${catalogs}">
        <li>
            <a href="/catalogModify?idcatalog=${catalog.idcatalog}&action=show">${catalog.name}</a>
        </li>
    </c:forEach>
</ul>
<br>
<br>
<br>
<p>你可以在此分类下新增一个分类：
<form action="/catalogModify?idcatalog=${currentCatalog.idcatalog}&action=insert" method="post">
    <input type="text" name="newName" placeholder="新分类的名称">
    <input type="submit" value=" 确 认 增 加 ">
</form>
</p>
<% // catalogs参数为空但是products参数不空，说明是最低类，展示可以修改的商品
} else if (request.getAttribute("products") != null) { %>
<p>此分类下存在商品（点击对应商品修改）：</p>
<ul>
    <c:forEach var="product" items="${products}">
        <li>
            <a href="page/productModify.jsp?idproduct=${product.idproduct}&action=modify">${product.name}</a>
        </li>
    </c:forEach>
</ul>
<p>你可以在此分类下
    <button
            onclick="window.location.href='page/productModify.jsp?catalogid=${currentCatalog.idcatalog}&action=insert'">
        点 击 新 增 一 个
        商 品
    </button>
</p>

<% //其他情况说明是新添加的类，还空着
} else {%>
<p>你可以在此分类${currentCatalog.name}下新增一个分类：
<form action="/catalogModify?idcatalog=${currentCatalog.idcatalog}&action=insert" method="post">
    <input type="text" name="newName" placeholder="新分类的名称">
    <input type="submit" value=" 确 认 增 加 ">
</form>
</p>
<br>
<p>或将此作为最低类，在此分类下
    <button
            onclick="window.location.href='page/productModify.jsp?catalogid=${currentCatalog.idcatalog}&action=insert'">
        点 击 新 增 一 个
        商 品
    </button>
</p>
<% } %>
<br>
<br>
<br>
<br>
<p><input type="button" value=" 返 回 主 菜 单 " onclick="window.location.href='page/adminMenu.jsp'"></p>
</body>
</html>
