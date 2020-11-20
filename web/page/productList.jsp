<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>商品列表页面</title>
</head>
<body>
<% if (request.getAttribute("products") == null) {%>
<h3>抱歉，当前分类是空的</h3>
<% } else { %>
<h3>当前分类下商品有：</h3>
<table border="1">
    <tr>
        <th>商品名</th>
        <th>价格</th>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td><a href="/productDetail?idproduct=${product.idproduct}">${product.name}</a></td>
            <td>${product.price}</td>
        </tr>
    </c:forEach>
</table>
<% }%>
<br>
<br>
<p><input type="button" value=" 返 回 主 菜 单 " onclick="window.location.href='page/userMenu.jsp'"></p>

</body>
</html>
