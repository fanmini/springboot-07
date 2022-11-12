<%--
  Created by IntelliJ IDEA.
  User: Qian
  Date: 2022/8/22
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" href="../css/personal-traniner.css">
</head>
<body>
<div>
    <!--头部-->
    <jsp:include page="topnav.jsp"/>
    <!--实时导航-->
    <jsp:include page="real-time-navigation.jsp"/>
    <!--身体-->
    <div class="main">
        <div class="personal">
            <!--标题-->
            <div class="centers border-test">
                <div class="thread"></div>
                <div>
                    <div class="title-red">${otp.getTitle()}</div>
                    <div class="title-black">${otp.getSubTitle()}</div>
                </div>
                <div class="thread"></div>
            </div>
            <!--图片展示-->
            <div class="coach-content border-test">
                <c:forEach items="${otp.getCip()}" var="cip">

                <div>
                    <div><img src="${cip.getImg()}" alt=""></div>
                    <div class="title-black">${cip.getName()}</div>
                    <div>${cip.getPosition()}</div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!--底部 form表单-->
    <jsp:include page="foot.jsp"/>
    <!--底板-->
    <div class="foot-bottom">
    </div>
</div>
</body>
</html>
