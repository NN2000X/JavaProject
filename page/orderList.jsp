<%@ page import="entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="service.OrderService" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="service.UserService" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单列表</title>
</head>
<body>
<%
    List<Order> committed = OrderService.getCommitted();
    List<User> committedBuyers = new LinkedList<>();
    List<BigDecimal> committedPrices = new LinkedList<>();
    for (Order order : committed) {
        committedBuyers.add(UserService.get(order.getUser()));
        committedPrices.add(OrderService.getTotalPrice(order.getId()));
    }
%>

<%if (!committed.isEmpty()) {%>
<h3>以下为已提交订单：</h3>
<table border="1">
    <tr>
        <th>订单号</th>
        <th>买家</th>
        <th>总价</th>
        <th>创建时间</th>
        <th>提交时间</th>
        <th>邮箱</th>
    </tr>
    <%for (int i = 0; i < committed.size(); i++) {%>
    <tr>
        <td><a href="/page/orderDetail.jsp?id=<%=committed.get(i).getId()%>"><%=committed.get(i).getId()%></a></td>
        <td><%=committedBuyers.get(i).getUsername()%></td>
        <td><%=committedPrices.get(i)%></td>
        <td><%=committed.get(i).getCreateTime()%></td>
        <td><%=committed.get(i).getCommitTime()%></td>
        <td><%=committedBuyers.get(i).getEmail()%></td>
    </tr>
    <%}%>
</table>
<%} else {%>
<h3>还没有已经提交的订单！</h3>
<% } %>
<p><input type="button" value="  返 回 主 菜 单  " onclick="window.location.href='/page/saleMenu.jsp'"/></p>
</body>
</html>
