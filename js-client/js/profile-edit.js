let imgHref = '' ;
layui.use(['form', 'layedit','layer', 'laydate','upload'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate,
        upload = layui.upload;

    // 时间组件
    laydate.render({
        elem:'#L_date',
        trigger: 'click'
    });
    // 回显修改前的值
    let dataItem = JSON.parse(sessionStorage.getItem("dataItem"));
    setUserData(dataItem)
    // 赋值
    function setUserData(data){
        form.val('profile-edit', {
            "date": data.date // "name": "value"
            ,"content": data.content // "name": "value"
            ,"enable": data.enable==0?'0':'1' // 状态 启用禁用
        });
        $('#demo1').attr("src",data.imgHref);
        imgHref =data.imgHref;
    }
    //form 表单重新渲染
    form.render();
    // 文件上传
    uploadFile();
    // 监听按钮
    form.on('submit(edit)',function (data){
        data = data.field ;
        dataItem.date=data.date;
        dataItem.content = data.content;
        dataItem.enable= data.enable;
        dataItem.imgHref=imgHref;
        let res = myAjax("/back/companyProfile/set",dataItem,'PUT');
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



   /* upload.render({
        elem: '#test1'
        ,url: '/back/file/upload'
        ,headers: {'token': sessionStorage.getItem("token")}
        ,method: 'POST'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo1').attr('src', result); //图片链接（base64）
            });
            layer.msg("上传中.....",{   // 图片上传等待
                icon: 16,
                shade:0.01,
                time: 0
            })
        }
        ,done: function(res){
            layer.close(layer.msg());  // 关闭上传等待框
            imgHref=res.data.src ;
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
        }
    });*/


});
      