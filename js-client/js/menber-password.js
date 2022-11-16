layui.use(['form', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate;


    // 前端数据验证
    form.verify({
        username:[/^[a-zA-Z0-9_-]{4,16}$/,'用户名4到16位'],
        pass: [/^(?![0-9]+$)(?![a-z]+$)[0-9A-Za-z]{6,20}$/,"密码错误提示：至少5个字符，字母、数字"]
    });
    // 通过拿到的id值回显修改前的值
    findById();
    function findById(){
        let id = sessionStorage.getItem("userId");
         let res = myAjax("/back/user/query/"+id,null,"get");
         if(res!=null) {
             setUserData(res.data);
         }
    }
    function setUserData(data){
            form.val('user-pwd', {
                "userName": data.userName, // "name": "value"
                "password": data.password //
            });
    }
    //form 表单重新渲染显示回显数据
    form.render();

    // 监听修改按钮
    form.on('submit(pwd)',function (data){
        let userdata = JSON.parse(sessionStorage.getItem("userItem"));
        userdata.password=data.field.password ;
        let res = myAjax("/back/user/pwd",userdata,'PUT');
        if(res.count>0){
            layer.alert(
                '修改成功',
                {icon:6},
                function (){
                    xadmin.close();// 关闭添加窗口
                    xadmin.father_reload();// 刷新父窗口
                });
        } else{
            layer.alert("修改失败")
        }
        return false;
    });


});
