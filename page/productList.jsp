<%@ page import="java.util.List" %>
<%@ page import="entity.Product" %>
<%@ page import="service.ProductService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品修改</title>
</head>
<body>
<%
    List<Product> products = ProductService.getAll();
    if (!products.isEmpty()) {
%>
<p>点击对应商品修改：</p>
<ul>
    <%for (Product product : products) {%>
    <li>
        <a href="/page/productModify.jsp?id=<%=product.getId()%>&action=modify"><%=product.getName()%></a>
    </li>
    <%}%>
</ul>
<%}%>
<p>
    <button onclick="window.location.href='/page/productModify.jsp?action=add'">
        点 击 新 增 一 个 商 品
    </button>
</p>
<p><input type="button" value="  返 回 主 菜 单  " onclick="window.location.href='/page/saleMenu.jsp'"></p>
</body>
</html>