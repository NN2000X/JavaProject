<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>LoginPage</title>
</head>
<body>
    <form action="/userLogin" method="post">
        <h3>请登录</h3>
        <input type="text" name="username" placeholder="用户名">
        <br>
        <br>
        <input type="password" name="password" placeholder="密码">
        <br>
        <br>
        <input type="submit" value=" 登  录 ">
        <input type="button" value=" 注  册 " onclick="window.location.href='userRegister.jsp'">
    </form>
</body>
</html>
