<%@ page import="java.util.List" %>
<%@ page import="entity.Detail" %>
<%@ page import="service.DetailService" %>
<%@ page import="entity.Order" %>
<%@ page import="service.OrderService" %>
<%@ page import="entity.User" %>
<%@ page import="service.UserService" %>
<%@ page import="entity.Product" %>
<%@ page import="service.ProductService" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
</head>
<body>
<%
    Order order = OrderService.get(Integer.parseInt(request.getParameter("id")));
    User buyer = UserService.get(order.getUser());
    List<Detail> details = DetailService.getByOrder(order.getId());
    List<Product> products = new LinkedList<>();
    for (Detail detail : details)
        products.add(ProductService.get(detail.getProduct()));
%>
<h3>这里是<%=order.getId()%>号订单的详情页</h3>
<ul>
    <li>买家：<%=buyer.getUsername()%>
    </li>
    <li>总价：¥<%=OrderService.getTotalPrice(order.getId())%>
    </li>
    <li>创建时间：<%=order.getCreateTime()%>
    </li>
    <li>提交时间：<%=order.getCommitTime()%>
    </li>
    <li>联系邮箱：<%=buyer.getEmail()%>
    </li>
</ul>
<br>
<p>以下为订单详请</p>
<table border="1">
    <tr>
        <th>商品名</th>
        <th>单价</th>
        <th>数量</th>
    </tr>
    <%for (int i = 0; i < details.size(); i++) {%>
    <tr>
        <td><%=products.get(i).getName()%>
        </td>
        <td><%=products.get(i).getPrice()%>
        </td>
        <td><%=details.get(i).getQuantity()%>
        </td>
    </tr>
    <%}%>
</table>
<br>
<p><input type="button" value="  返 回 主 菜 单  " onclick="window.location.href='/page/saleMenu.jsp'"></p>
</body>
</html>
