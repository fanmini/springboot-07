let imgHref = '';
layui.use(['form', 'layedit','layer', 'laydate','upload'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate,
        upload = layui.upload;

    // 渲染模糊类型
    let nav = myAjax("/back/nav/typeAll/2", null, "get");
    setSelect(nav);

    // 回显修改前的值
    var item = sessionStorage.getItem("dataItem");
    item=JSON.parse(item);
    setUserData(item);
    function setUserData(data){
        form.val('product-edit', {
            "navId": data.navId
            ,"name": data.name
            ,"price": data.price
            ,"enable": data.enable==0?'0':'1'
        });
        $('#demo1').attr("src",data.imgHref);
        imgHref = data.imgHref ;
    }
    // 刷新表单
    form.render()
    // 监听按钮
    form.on('submit(edit)',function (data){
        data = data.field;
        item.imgHref=imgHref ;
        item.navId=data.navId;
        item.name=data.name;
        item.price=data.price;
        item.enable=data.enable;
        let res = myAjax("/back/product/set",item,'PUT');
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
      