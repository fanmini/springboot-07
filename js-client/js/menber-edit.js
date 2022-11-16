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
                "userName": data.userName
                ,"phone": data.phone
                ,"enable": data.enable==0?'0':'1'
                ,"email": data.email
            });
            if(data.hobby!=null){
               let arr = data.hobby.split(",");
                $('input[name=hobby][type=checkbox]').each(function(){
                    for (let i = 0; i < arr.length; i++) {
                        if($(this).attr('title')==arr[i]){
                            $(this).attr('checked','checked');
                        }
                    }
                });
            }

    }
    //form 表单重新渲染
    form.render();
    // 监听按钮
    let userdata = sessionStorage.getItem("userItem");
    userdata = JSON.parse(userdata)
    form.on('submit(edit)',function (data){
        data = data.field;
        let arr = [] ;
        $('input[type=checkbox][name=hobby]:checked').each(function (){
            arr.push($(this).attr('title'));
        });
        // 在把数组转换为字符串，
        userdata.hobby=arr.toLocaleString();

        userdata.userName=data.userName ;
        userdata.phone=data.phone ;
        userdata.enable=data.enable ;
        userdata.email=data.email ;
        let res = myAjax("/back/user/set",userdata,'PUT');
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
      