layui.use(['table','form','laydate'], function(){
    let form = layui.form;
    let laydate = layui.laydate;
    let table = layui.table;
    // 拿到所有的用户角色以及当前选中角色
    var data = myAjax("/back/user/role/"+sessionStorage.getItem("userId"),null,'GET').data;
    // 拿到存放标签div
    var roles = $('#user-role');
    // 动态添加
    if(null != data) {
        $.each(data, function (index, item) {
            console.log(item.id+item.name);
            if (item.status === 0) {
                roles.append('<input checked type="checkbox" lay-skin="primary" name="permi" value="' + item.id + '" title="' + item.name + '">');
            } else {
                roles.append('<input type="checkbox" lay-skin="primary" name="permi" value="' + item.id + '" title="' + item.name + '">');
            }
        })
    }
    // 刷新
    form.render('checkbox');
    // 监听提交按钮
    form.on('submit(set-permissions)',function (){
        var labelArray = $('input[name="permi"]');
        //获取input[name="labelType"]的值
        var permissions = [];
        for (k in labelArray) {
            if (labelArray[k].checked) {
                //获取所有选中的复选框，并将其值放入数组中
                permissions.push(labelArray[k].value);
            }
        }

        var res = myAjax("/back/user/role/"+sessionStorage.getItem("userId"),permissions,'POST');
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
                    