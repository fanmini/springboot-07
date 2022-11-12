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
    <link rel="stylesheet" href="../css/about-us.css">
</head>
<body>
<div>
    <!--头部-->
    <jsp:include page="topnav.jsp" />
    <!--身体-->
    <div class="main">
        <!--实时导航-->
        <jsp:include page="real-time-navigation.jsp" />
        <!--关于我们-->
        <div class="about-us">
            <!--基本信息-->
            <div class="our-info">
                <div><img src="${companyInfo.getImg()}" alt="">

                </div>
                <div class="info-text title-text">
                    <div>${companyInfo.getText()}</div>
                </div>
                <div>
                    <div class="info-text title-text many-over">
                      ${companyInfo.getSubText()}
                    </div>
                </div>
                <div class="thread"></div>
            </div>
            <!--公司历史-->
            <div class="history">
                <c:forEach items="${companyHistory}" var="h">
                <div class="centers">
                    <div>
                        <img src="${h.getImg()}" alt="">
                    </div>
                    <div>
                        <div>${h.getDate()}</div>
                        <div> ${h.getText()} </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
        <!--学员风采-->
        <div class="student-style">
            <!--标题-->
            <div class="centers">
                <div class="thread"></div>
                <div>
                    <div class="title-red">学员风采</div>
                    <div class="title-white">STUDENT SHOW </div>
                </div>
                <div class="thread"></div>
            </div>
            <!--图片部分-->
            <div class="student-img">
                <div>
                <c:forEach items="${ssp.getImg()}" var="ssp">
                    <div><img src="${ssp}" alt=""></div>
                </c:forEach>
                </div>

                <div></div>
            </div>
            <!--预约按钮-->
            <div class="student-style-button">立即预约</div>
        </div>
    </div>
    <!--底板-->
    <div class="foot-bottom">
    </div>
</div>
</body>
</html>