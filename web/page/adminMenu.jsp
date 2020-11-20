<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台菜单页面</title>
</head>
<body>
<fieldset>
    <legend>请选择管理员功能</legend>
    <br>
    <input type="button" value=" 修 改 分 类 / 商 品 信 息 " onclick="window.location.href='/catalogModify?idcatalog=1&action=show'">
    <br>
    <br>
    <input type="button" value=" 用 户 提 交 订 单 " onclick="window.location.href='/orderList'">
    <br>
    <br>
    <input type="button" value=" 退 出 登 录 " onclick="window.location.href='/userLogout'">
    <br>
    <br>
</fieldset>
</body>
</html>
