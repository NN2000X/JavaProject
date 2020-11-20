<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>菜单页面</title>
</head>
<body>
<fieldset>
    <legend>请选择功能</legend>
    <br>
    <input type="button" value=" 开 始 购 物 " onclick="window.location.href='/catalogSelect?idcatalog=1'">
    <br>
    <br>
    <input type="button" value=" 查 看 购 物 车 " onclick="window.location.href='/showCart'">
    <br>
    <br>
    <input type="button" value=" 退 出 登 录 " onclick="window.location.href='/userLogout'">
    <br>
    <br>
</fieldset>
</body>
</html>
