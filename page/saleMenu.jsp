<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售菜单</title>
</head>
<body>
<fieldset>
    <legend>请选择销售功能</legend>
    <p><input type="button" value="  修 改 商 品 信 息  "
              onclick="window.location.href='/page/productList.jsp'"/></p>
    <p><input type="button" value="  查 看 用 户 提 交 订 单  " onclick="window.location.href='/page/orderList.jsp'"/></p>
    <p><input type="button" value="  退 出 登 录  " onclick="window.location.href='/logout'"/></p>
</fieldset>
</body>
</html>
