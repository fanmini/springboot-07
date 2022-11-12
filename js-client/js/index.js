$(function () {
    if(sessionStorage.getItem("username")==null || sessionStorage.getItem("token")==null){
        alert("请登录");
        window.open("/login/login.html","_top")
    }else {
        let username = sessionStorage.getItem("username");
        $("#index-username").html(username);
    }
})