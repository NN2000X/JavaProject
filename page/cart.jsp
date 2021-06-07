<%@ page import="entity.Order" %>
<%@ page import="service.OrderService" %>
<%@ page import="entity.Product" %>
<%@ page import="entity.Detail" %>
<%@ page import="java.util.List" %>
<%@ page import="service.DetailService" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="service.ProductService" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<% Order cart = OrderService.getCart((Integer) request.getSession().getAttribute("currentUser"));
    List<Detail> details = DetailService.getByOrder(cart.getId());
    if (details.size() == 0) {
%>
<h3>你的购物车还空空如也～</h3>
<input type="button" value="  返 回  " onclick="window.location.href='/page/userMenu.jsp'">
<% } else {
    List<Product> products = new LinkedList<>();
    for (Detail detail : details)
        products.add(ProductService.get(detail.getProduct()));
%>
<h3>购物车里有这些宝贝：</h3>
<table border="1">
    <tr>
        <th>商品名</th>
        <th>价格</th>
        <th>数量</th>
        <th>小计</th>
    </tr>
    <%for (int i = 0; i < details.size(); i++) {%>
    <tr>
        <td><a href="/page/product.jsp?id=<%=products.get(i).getId()%>"><%=products.get(i).getName()%>
        </a></td>
        <td><%=products.get(i).getPrice()%>
        </td>
        <td><%=details.get(i).getQuantity()%>
        </td>
        <td><%=products.get(i).getPrice().multiply(BigDecimal.valueOf(details.get(i).getQuantity()))%>
        </td>
    </tr>
    <%}%>
</table>
<p>总价合计：<%=OrderService.getTotalPrice(cart.getId())%>元</p>
<br>
<form action="/commit?id=<%=cart.getId()%>" method="post">
    <input type="submit" value=" 提 交 订 单 ">
</form>
<%}%>
</body>
</html>
