<%--
  Created by IntelliJ IDEA.
  User: Qian
  Date: 2022/8/22
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" href="../css/contact-us.css">
</head>
<body>
<div>
    <!--头部-->
    <jsp:include page="topnav.jsp"/>
    <!--实时导航-->
    <jsp:include page="real-time-navigation.jsp"/>
    <!--身体-->
    <div class="main">
        <div class="centers">
            <div><img src="../img/map.png" alt=""></div>
            <div class="company-info">
                <c:forEach items="${info}" var="info">
                    <div>
                        <div><img src="${info.getTitle()}"></div>
                        <div class="single-over">${info.getHref()}</div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
    <!--底板-->
    <div class="foot-bottom">
    </div>
</body>
</html>