<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form action="/register" method="post">
    <fieldset>
        <legend>来了老弟 ( *^_^* )</legend>
        <p><input type="text" name="username" placeholder=" 用 户 名 "/></p>
        <p><input type="password" name="password" placeholder=" 密 码 "/></p>
        <p><input type="text" name="email" placeholder=" 邮 箱 "/></p>
        <p><input type="submit" value=" 注  册 "/></p>
    </fieldset>
    <p><input type="button" value=" 返  回 " onclick="window.location.href='/index.jsp'"/></p>
</form>
</body>
</html>