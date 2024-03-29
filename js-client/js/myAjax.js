// 自定义ajax
function myAjax(url,data,type){
    let result = null;
    $.ajax({
        url: "http://localhost:8080"+url,
        contentType: "application/json; charset=utf-8",
        data:JSON.stringify(data),
        type:type,
        dataType:'json',
        async:false,
        headers: {'token': sessionStorage.getItem('token')},
        success:function (res){
            if (res.code != 0 && res.code < 1003) {
                window.alert(res.msg);
                return;
            }
            result=res;
        }
    })
    return result;
}

// 渲染表单方法
var tableData = function (url,data,cols){
    var table = layui.table ;
    table.render({
        elem: '#test'
        ,url: "http://localhost:8080"+url
        ,headers: {'token': sessionStorage.getItem('token')}
        ,where: data
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

// 文件上传
let uploadFile = function(){
    var upload = layui.upload ;
    upload.render({
    elem: '#test1'
    ,url: 'http://localhost:8080/back/file/upload'
    ,headers: {'token': sessionStorage.getItem("token")}
    ,method: 'POST'
    ,before: function(obj){
        //预读本地文件示例，不支持ie8
        obj.preview(function(index, file, result){
            $('#demo1').attr('src', result); //图片链接（base64）
        });
        layer.msg("上传中.....",{   // 图片上传等待
            icon: 16,
            shade:0.01,
            time: 0
        })
    }
    ,done: function(res){
        //如果上传失败
        if(res.code > 0){ return layer.msg('上传失败'); }
        imgHref = res.data.src;
        layer.close(layer.msg());  // 关闭上传等待框
    }
})
}