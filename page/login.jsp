<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
<form action="/login" method="post">
    <fieldset>
        <legend>欢迎登录 ( *^_^* )</legend>
        <p><input type="text" name="username" placeholder=" 用 户 名 "/></p>
        <p><input type="password" name="password" placeholder=" 密 码 "/></p>
        <p><input type="submit" value=" 登  录 "/></p>
    </fieldset>
    <p><input type="button" value=" 返  回 " onclick="window.location.href='/index.jsp'"/></p>
</form>
</body>
</html>