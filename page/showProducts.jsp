<%@ page import="java.util.List" %>
<%@ page import="entity.Product" %>
<%@ page import="service.ProductService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>所有商品</title>
</head>
<body>
<%
    List<Product> products = ProductService.getAll();
    if (!products.isEmpty()) {
%>
<p>点击对应商品查看详情：</p>
<ul>
    <%
        for (Product product : products) {
            if (product.getStock() > 0) {
    %>
    <li>
        <a href="/page/product.jsp?id=<%=product.getId()%>"><%=product.getName()%>
        </a>
    </li>
    <%
            }
        }
    %>
</ul>
<%}%>
<p>推荐商品：<%
    for (Product product : products) {
        if (product.getStock() > 0) {
%>
    <a href="/page/product.jsp?id=<%=products.get(0).getId()%>"><%=products.get(0).getName()%>
        <%
                    break;
                }
            }
        %>
    </a></p>
<p><input type="button" value="  返 回 主 菜 单  " onclick="window.location.href='/page/userMenu.jsp'"/></p>
</body>
</html>
