layui.use(['laydate','layer','form'],function(){
    let $ = layui.jquery
        ,layer= layui.layer
        ,form = layui.form;

    form.on('submit(add)', function(data){
        data = data.field;
        let res = myAjax("/back/customer/add",data,'post');
        if(res.count>0){
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
})