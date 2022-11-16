let imgHref = '';
layui.use(['form', 'layedit','layer', 'laydate','upload'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate,
        upload = layui.upload;




    // 显修改前的值
    var dataItem = JSON.parse(sessionStorage.getItem('dataItem'));
    setUserData(dataItem);
    // 赋值
    function setUserData(data){
        form.val('contact-edit', {
            "text": data.text // "name": "value"
            ,"enable": data.enable==0?'0':'1' // 状态 启用禁用
        });
        $('#demo1').attr("src",data.imgHref);
        imgHref = data.imgHref ;
    }
    // 监听按钮
    form.on('submit(edit)',function (data){
        data = data.field;
        dataItem.imgHref=imgHref ;
        dataItem.text = data.text;
        dataItem.enable = data.enable ;
        let res = myAjax("/back/contact/set",dataItem,'PUT');
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
    // 文件上传
    uploadFile();
});
      