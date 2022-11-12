let table ;
layui.use(['table'], function() {
    table = layui.table;


})

// 自定义ajax
function myAjax(url,data,type){
    let result = null;
    $.ajax({
        url: "http://localhost:8080"+url,
        data:JSON.stringify(data),
        contentType:'application/json;charset=UTF-8',
        type:type,
        dataType:'json',
        async:false,
        headers: {'token': sessionStorage.getItem('token')},
        success:function (res){
            result=res;
        }
    })
    return result;
}

// 渲染表单方法
var tableData = function (url,data,cols){
    table.render({
        elem: '#test'
        ,url: "http://localhost:8080"+url
        ,headers: {'token': sessionStorage.getItem('token')}
        ,where: JSON.stringify(data)
        ,page: true
        ,list: 10
        ,cols: cols
        ,parseData: function (res) {
            if (res.code != 0 && res.code < 1003) {
                window.alert(res.msg);
                window.open("/login/login.html", "_top");
                return;
            }
        }
        ,error:function(res){
            window.alert(res.msg);
            window.alert("错误代码"+res.code);
            window.open("/login/login.html","_top");
        }
    });
}