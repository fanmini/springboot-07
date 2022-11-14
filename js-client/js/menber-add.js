layui.use(['form', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate;

    //form 表单重新渲染
    form.render();
    // 密码限制
    form.verify({
        username:[/^[a-zA-Z0-9_-]{4,16}$/,'用户名4到16位'],
        pass: [/^(?![0-9]+$)(?![a-z]+$)[0-9A-Za-z]{6,20}$/,"密码错误提示：至少5个字符，字母、数字"]
    })

    // 监听按钮
    form.on('submit(add)',function (data){
        data = data.field;
        let arr = [] ;
        $('input[type=checkbox][name=hobby]:checked').each(function (){
            arr.push($(this).attr('title'));
        });
        // 在把数组转换为字符串，
        data.hobby = arr.toLocaleString();
        let res = myAjax("/back/user/add",JSON.stringify(data),'post');
        if(res.code===0){
            layer.alert(
                '添加成功',
                {icon:6},
                function (){
                    xadmin.close();// 关闭添加窗口
                    xadmin.father_reload();// 刷新父窗口
                });
        } else{
            layer.alert("添加失败")
        }
        return false;
    });
});
      