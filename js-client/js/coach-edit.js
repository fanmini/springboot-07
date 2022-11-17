let imgHref = '';
layui.use(['form', 'layedit','layer', 'laydate','upload'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate,
        upload = layui.upload;

    let dataItem = sessionStorage.getItem("dataItem");
    dataItem = JSON.parse(dataItem);
    console.log(dataItem);
    setUserData(dataItem);
    // 回显赋值
    function setUserData(data){
            form.val('coach-edit', {
                "name": data.name
                ,"position": data.position
                ,"enable": data.enable==0?'0':'1'
            });
            $('#demo1').attr("src",data.imgHref);
            imgHref = data.imgHref ;
    }
    //form 表单重新渲染
    form.render();
    // 监听按钮
    form.on('submit(edit)',function (data){
        data = data.field;
        dataItem.imgHref=imgHref ;
        dataItem.name = data.name;
        dataItem.position = data.position;
        dataItem.enable = data.enable;
        let res = myAjax("/back/ourTeam/set",dataItem,'PUT');
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
    // 文件上传
   uploadFile();

});
      