<%--
  Created by IntelliJ IDEA.
  User: Qian
  Date: 2022/8/22
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" href="../css/emnu-nav.css">
</head>
<body>
<div class="menu-nav ">
    <div class="starts">
        <div><img src="../img/p7up.png" alt=""></div>
        <div>网站首页</div>
        <div>&gt;&gt;</div>
        <div>${thisPage}</div>
    </div>
</div>
</body>
</html>