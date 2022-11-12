<%--
  Created by IntelliJ IDEA.
  User: Qian
  Date: 2022/8/22
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" href="../css/news.css">
</head>
<body>
<div>
    <!--头部-->
    <jsp:include page="topnav.jsp" />
    <!--实时导航-->
    <jsp:include page="real-time-navigation.jsp" />
    <!--身体-->
    <div class="main">
        <div class="news">
            <!--标题-->
            <div class="centers border-test" >
                <c:forEach items="${newNavPojo}" var="nav">
                    <div onclick=window.open("${nav.getHref()}",'_self')>${nav.getTitle()}</div>
                </c:forEach>
            </div>
            <!--具体内容-->
            <div class="news-content">
                <c:forEach items="${ids}" var="content">
                <div class="centers">
                    <div>
                        <div>${content.getDay()}</div>
                        <div>${content.getYears()}-10</div>
                    </div>
                    <div>
                        <div class="news-title">${content.getTitle()}</div>
                        <div class="title-text">${content.getContent()}</div>
                    </div>
                </div>
                </c:forEach>
            </div>
            <!--分页-->
            <div id="page"></div>
        </div>
    </div>
    <!--底板-->
    <div class="foot-bottom">
    </div>
</div>
</body>
</html>