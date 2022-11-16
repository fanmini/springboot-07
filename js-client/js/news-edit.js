let imgHref =  '';
let layedit = null ;
layui.use(['form', 'layedit','layer', 'laydate','upload'], function () {
    var form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        laydate = layui.laydate;
        layedit =layui.layedit ;



    // 时间渲染
    laydate.render({
        elem:'#L_date',
        trigger:'click'
    });
    // 富文本
    layedit.set({
        uploadImage: {
            url: 'http://localhost:8080/back/file/upload',
            type: 'post' // 默认post
        }
    });
    let index = layedit.build("L_content");
    // 查询组件
    let res = myAjax("/back/nav/typeAll/1", null, "get");
    setSelect(res);
    // sessionStorage 获取当前需要修改的数据
    let dataItem = JSON.parse(sessionStorage.getItem("dataItem"));
    setUserData(dataItem);
    // 赋值
    function setUserData(data){
            form.val('news-edit', {
                "navId": data.navId
                ,"date": data.date
                ,"title": data.title
                ,"enable": data.enable==0?'0':'1' // 状态 启用禁用
            });
        layedit.setContent(index,data.content);
        $('#demo1').attr("src",data.imgHref);
        imgHref=data.imgHref ;
    }
    //form 表单重新渲染
    form.render();
    // 监听按钮
    form.on('submit(edit)',function (data){
        if(data.field.navId==null){
            alert('请选择类型');
            return false;
        }
        data = data.field;
        dataItem.imgHref=imgHref;
        dataItem.navId=data.navId;
        dataItem.date = data.date ;
        dataItem.title = data.title ;
        dataItem.enable = data.enable ;
        dataItem.content = layedit.getContent(index);
        console.log(dataItem);
        let res = myAjax("/back/news/set",dataItem,'PUT');
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
      