<%--
  Created by IntelliJ IDEA.
  User: Qian
  Date: 2022/8/22
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/public.css">
    <script src="../js/jquery-3.5.1.js"></script>
    <script src="../js/commen.js"></script>
    <link rel="stylesheet" href="../css/product.css">
</head>
<body>
<div>
    <!--头部-->
    <jsp:include page="topnav.jsp" />
    <!--实时导航-->
    <jsp:include page="real-time-navigation.jsp" />

    <!--身体-->
    <div class="main">
        <div class="product">
            <!--标题-->
            <div class="centers border-test">
                <c:forEach items="${ProductNav}" var="nav">
                <div onclick="window.open('${nav.getHref()}','_self')">${nav.getTitle()}</div>
                </c:forEach>
            </div>
            <!--图片展示-->
            <div class="product-content border-test">
                <c:forEach items="${content}" var="c">
                <div>
                    <div><img src="${c.getImg()}" alt=""></div>
                    <div>${c.getName()}</div>
                    <div class="centers">
                        <div>价格：</div>
                        <div>${c.getPrice()}</div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
        <!--分页-->
        <div id="page">

        </div>
    </div>
    <!--底板-->
    <div class="foot-bottom">
    </div>
</div>
</body>
</html>