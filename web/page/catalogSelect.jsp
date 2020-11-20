<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分类页面</title>
</head>
<body>
<p>
<h3>当前商品分类：${currentCatalog.name}</h3>
</p>
<ul>
    <c:forEach var="catalog" items="${catalogs}">
        <li>
            <a href="/catalogSelect?idcatalog=${catalog.idcatalog}"><b>${catalog.name}</b></a>
        </li>
    </c:forEach>
</ul>
<br>
<br>
<p><input type="button" value=" 返 回 主 菜 单 " onclick="window.location.href='page/userMenu.jsp'"></p>
</body>
</html>
