<%--
  Created by IntelliJ IDEA.
  User: Qian
  Date: 2022/8/22
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../css/topnav.css">
    <link rel="stylesheet" href="../css/public.css">
</head>
<body>
<div class="topnav">
    <div class="tops border-test">
        <div class="nav centers">
            <div class="loge centers">
                <div>
                    <img src="../img/4idy.png" alt="">
                </div>
                <div>
                    <div>万息健身</div>
                    <div>FITNESS</div>
                </div>
            </div>
            <div class="menu">
                <c:forEach var="s" items="${nav}">
                    <div onclick="window.open('${s.href}','_self')">${s.title}</div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
