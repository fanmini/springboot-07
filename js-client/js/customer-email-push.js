layui.use(['form', 'layedit','layer', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate;
    // 拿到所有的预约用户
    var data = myAjax("/back/customer/query",null,'GET').data;
    // 拿到存放标签div
    var customer = $('#customer-list');
    // 动态添加
    if(null != data && data.length > 0){
        customer.append('<input type="checkbox" lay-skin="primary" name="customer" value="0" title="所有顾客">');
        $.each(data,function (index,item){
            customer.append('<input type="checkbox" lay-skin="primary" name="customer" value="' + item.id + '" title="' + item.name + '">');
        })
    }else{
        customer.append('<input type="checkbox" lay-skin="primary" name="customer" value="-1" title="暂无顾客" checked>');
    }
    // 刷新
    form.render('checkbox');

    // 监听提交按钮
    form.on('submit(email-push)',function (data){
        data = data.field ;
        var labelArray = $('input[name="customer"]');
        //获取input[name="labelType"]的值
        var labels = [];
        for (k in labelArray) {
            if (labelArray[k].checked) {
                //获取所有选中的复选框，并将其值放入数组中
                labels.push(labelArray[k].value);
            }
        }
        data.customer = labels;
        var res = myAjax("/back/customer/customerPush",data,"POST");
        if(res.code == 0 ){
            layer.alert(
                res.msg,
                {icon:6},
                function (){
                    xadmin.close();// 关闭添加窗口
                    xadmin.father_reload();// 刷新父窗口
                });
        }else {
            layer.alert(res.msg)
        }
        return false;
    });



});
      