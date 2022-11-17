layui.use(['form', 'layedit','layer', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate;

    // 回显修改前的值
    var dataItem = JSON.parse(sessionStorage.getItem('dataItem'));
    setUserData(dataItem);
    // 赋值
    function setUserData(data){
            form.val('customer-edit', {
                "name": data.name // "name": "value"
                ,"phone": data.phone // "name": "value"
                ,"email": data.email // "name": "value"
                ,"content": data.content // "name": "value"
                ,"enable": data.enable==0?'0':'1' // 状态 启用禁用
            });
    }
    //form 表单重新渲染
    form.render();
    // 监听按钮
    form.on('submit(edit)',function (data){
        data = data.field;
        dataItem.name = data.name;
        dataItem.phone = data.phone;
        dataItem.email = data.email;
        dataItem.content = data.content;
        dataItem.enable = data.enable;
        let res = myAjax("/back/customer/set",dataItem,'PUT');
        if(res.count>0){
            layer.alert(
                '修改成功',
                {icon:6},
                function (){
                    xadmin.close();// 关闭添加窗口
                    xadmin.father_reload();// 刷新父窗口
                });
        } else{
            layer.alert(res.msg)
        }
        return false;
    });
});
      