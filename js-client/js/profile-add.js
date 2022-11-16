let imgHref = '' ;
layui.use(['upload','laydate','layer','form'],function(){
    let $ = layui.jquery
        ,upload = layui.upload
        ,layer= layui.layer
        ,laydate = layui.laydate
        ,form = layui.form;
    // 时间渲染组件
    laydate.render({
        elem:"#historyDate",
        trigger:'click'
    });
    // 刷新表单
    form.render();
    // 文件上传
    uploadFile();
    // 监听添加按钮
    form.on('submit(add)', function(data){
        data = data.field;
        data.imgHref=imgHref;
        let res = myAjax("/back/companyProfile/add",data,'POST');
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