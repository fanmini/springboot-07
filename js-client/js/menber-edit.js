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
    findById();
    // 通过拿到的id值回显修改前的值
    // 查询
    function findById(){
        let id = sessionStorage.getItem("userId");
         let res = myAjax("/back/user/query/"+id,null,"get");
         if(res!=null) {
             setUserData(res.data);
         }
    }
    // 赋值
    function setUserData(data){
            form.val('user-edit', {
                "userName": data.userName // "name": "value"
                ,"phone": data.phone // "name": "value"
                ,"enable": data.enable==0?'0':'1' // 状态 启用禁用
                ,"email": data.email //开关状态
            });
            let arr = data.hobby.split(",");
            $('input[name=hobby][type=checkbox]').each(function(){
                for (let i = 0; i < arr.length; i++) {
                    if($(this).attr('title')==arr[i]){
                        $(this).attr('checked','checked');
                    }
                };
            });
    }
    //form 表单重新渲染
    form.render();
    // 监听按钮
    form.on('submit(edit)',function (data){
        data = data.field;
        let arr = [] ;
        $('input[type=checkbox][name=hobby]:checked').each(function (){
            arr.push($(this).attr('title'));
        });
        // 在把数组转换为字符串，
        data.hobby = arr.toLocaleString();
        data.id=sessionStorage.getItem('userId');
        console.log(data);
        let res = myAjax("/back/user/set",JSON.stringify(data),'PUT');
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
      