$(function() {
    getCode();

    layui.use(['form','jquery'], function(){
        let form = layui.form;
        let jquery=layui.jquery;
        // 登录输入验证限制
        form.verify({
            username: [/^[\u4e00-\u9fa5a-zA-Z0-9]{5,12}$/, "用户名错误提示：请输入5-12位"],
            password: [/^(?![0-9]+$)(?![a-z]+$)[0-9A-Za-z]{5,20}$/,"密码错误提示：至少5个字符，字母、数字"]
        })
        //监听提交
        form.on('submit(login)', function(data){
            data=data.field;
            console.log(data);
            $.ajax({
                url:'http://localhost:8080/login',
                data:JSON.stringify(data),
                contentType:'application/json;charset=UTF-8',
                type:'post',
                dataType:'json',
                success:function (res){
                    layer.msg(res.msg);
                    if(res.code==0){
                        sessionStorage.setItem("username",res.data.userName);
                        sessionStorage.setItem("token",res.data.token)
                        location.href='/html/index.html'
                    }
                }
            });
            return false;
        });
    });
})
function getCode(){
    $.ajax({
        url:"http://localhost:8080/code"
        ,async:true
        ,dataType:'json'
        ,type:'get'
        ,success:function (res) {
            // 接收验证码
            var codeKey = res.data.codeKey;
            $("#imgCode").attr("src",res.data.code);
            $("#codeKey").attr("value",codeKey);
        }
    })
}

