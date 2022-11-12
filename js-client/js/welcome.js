$(function (){
    // 登录用户名
    let username = sessionStorage.getItem("username");
    $("#userName").html(username);

    setInterval("getTime()",1000)
})
function getTime(){
    // 当前系统时间
    let date = new Date();
    let year  = date.getFullYear(); // 获取年
    let month = date.getMonth()+1; // 获取月分
    let day = date.getDate(); // 获取日
    // 时分秒
    let hours = date.getHours();
    let minutes = date.getMinutes();
    let seconds = date.getSeconds();
    let thisTime=year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    $("#nowTime").html(thisTime);
}