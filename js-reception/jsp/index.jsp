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
    <title>万息健身</title>
    <link rel="stylesheet" href="/css/index.css">
</head>
<body>
<div>
    <!--  头部  loge  导航栏  宣传图-->
    <div class="top">
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
    <!--身体-->
    <div class="main">
        <!--关于我们-->
        <div class="about-us">
            <div class="infos">
                <div>
                    <div class="title-red">${aboutUs.getTitle()}</div>
                    <div class="title-white">${aboutUs.getSubTitle()}</div>
                </div>
                <div class="title-text many-over">
                    <div >${aboutUs.getContent()}</div>
                </div>
            </div>

            <div class="content betweens">
                <div >
                    <div class="title-text many-over">${aboutUs.getSubContent()}</div>
                </div>
                <div>
                    <img src="../img/iwpn.png"/>
                </div>
            </div>
        </div>
        <!--私人教练-->
        <div class="our-team">
            <!--标题-->
            <div class="centers">
                <div class="thread"></div>
                <div>
                    <div class="title-red">${ourTeam.getTitle()}</div>
                    <div class="title-white">${ourTeam.getSubTitle()}</div>
                </div>
                <div class="thread"></div>
            </div>
            <!--图片部分-->
            <div class="centers">
                <c:forEach items="${ourTeam.getCip()}" var="coach">
                    <div class="coach">
                        <div><img src="${coach.getImg()}" alt=""></div>
                        <div>${coach.getName()}</div>
                        <div>${coach.getPosition()}</div>
                    </div>
                </c:forEach>

            </div>
            <!--按钮-->
            <div class="centers">
                <div class="more">MORE+</div>
            </div>
            <!--底部-->
            <div class="our-team-foot  betweens border-test">
                <div>
                    <div class="single-over">${ourTeam.getContent()}</div>
                    <div class="single-over">${ourTeam.getSbuContent()}</div>
                </div>
                <div>
                    <div>
                        <div class="more2">MORE+</div>
                    </div>
                </div>
            </div>
        </div>
        <!--新闻资讯-->
        <div class="news ">
            <!--头-->
            <div class="centers">
                <div class="thread"></div>
                <div>
                    <div class="title-red">${news.getTitle()}</div>
                    <div class="title-black">${news.getSubTitle()}</div>
                </div>
                <div class="thread"></div>
            </div>
            <!--图片-->
            <div class="centers news-content">
                <c:forEach items="${news.getNc()}" var="nc">
                    <div class="news-img">
                        <div><img src="${nc.getImg()}" alt=""></div>
                        <div class="news-text">
                            <div>${nc.getTitle()}</div>
                            <div>${nc.getDate()}</div>
                            <div>${nc.getText()}</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!--按钮3-->
            <div class="centers">
                <div class="more3">MORE+</div>
            </div>
        </div>
    </div>
    <!--底部 form表单-->
    <jsp:include page="foot.jsp"/>
    <!--底板-->
    <div class="foot-bottom"></div>
</div>
</body>
</html>