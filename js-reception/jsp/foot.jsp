<%--
  Created by IntelliJ IDEA.
  User: Qian
  Date: 2022/8/22
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" href="../css/foot.css">
</head>
<body>
<!--底部 form表单-->
<div class="foot">
    <div class="foot-bg centers border-test">
        <div class="border-test"><img src="../img/acfa5605108c3150158055c03e58b035.png" alt=""></div>
        <div class="info">
            <div class="single-over">填表格可在私教带领下免费体验一次!</div>
            <div>
                <div class="single-over" >请仔细填写好下方预约表格，我们的客服人员会在24小时内与您联系，</div>
                <div class="single-over">谢谢您的支持与关注!</div>
            </div>
            <div class="form">
                <div>
                    <div>姓名</div>
                    <div><input  class="inp" type="text" name="username"></div>
                </div>
                <div>
                    <div>电话</div>
                    <div><input type="text" class="inp" name="phone"></div>
                </div>
            </div>
            <div class="form">
                <div>
                    <div>邮箱</div>
                    <div><input type="email" class="inp" name="useremail"></div>
                </div>
                <div>
                    <div>内容</div>
                    <div ><input type="text" class="inp"  name="content"></div>
                </div>
            </div>
            <div><input  id="button" type="button" value="提交"></div>
            <div>THANKS</div>
        </div>
    </div>
</div>
</body>
</html>
