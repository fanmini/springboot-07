let imgHref  = '';
layui.use(['upload','laydate','layer','form'],function(){
    let $ = layui.jquery
        ,upload = layui.upload
        ,layer= layui.layer
        ,laydate = layui.laydate
        ,form = layui.form;
    // 下拉框赋值
    let res = myAjax("/back/nav/typeAll/2", null, "get");
    setSelect(res);
    form.render();

    // 添加按钮监听
    form.on('submit(add)', function(data){
        data = data.field;
        data.imgHref = imgHref;
        let res = myAjax("/back/product/add",data,'post');
        if(res.count>0){
            layer.alert(
                '添加成功',
                {icon:6},
                function (){
                    xadmin.close();// 关闭添加窗口
                    xadmin.father_reload();// 刷新父窗口
                });
        } else{
            layer.alert(res.msg);
        }
        return false;
    });

    // 文件上传
    uploadFile();
})