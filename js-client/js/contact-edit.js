layui.use(['form', 'layedit','layer', 'laydate','upload'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate,
        upload = layui.upload;
    let imgHref = '';



    findById();

    // 通过拿到的id值回显修改前的值
    // 查询
    function findById(){
        let id = sessionStorage.getItem("contactId");
         let res = myAjax("http://localhost:8080/back/contact/query",{id:id},"get");
         if(res!=null) {
             setUserData(res.data);
         }
    }
    // 赋值
    function setUserData(data){
            form.val('contact-edit', {
                "text": data.text // "name": "value"
                ,"enable": data.enable==0?'0':'1' // 状态 启用禁用
            });
            // 数据状态赋值 2
            //     $('input[type=radio][name=enable][value='+data.enable+']').attr('checked','checked');
            // 数据赋值 3
                // $("#username").val(data.username)
        // 图片回显
        $('#demo1').attr("src",data.imgHref);
        imgHref = data.imgHref ;
    }
    // 监听按钮
    form.on('submit(edit)',function (data){
        data = data.field;
        data.id=sessionStorage.getItem('contactId');
        data.imgHref=imgHref ;
        console.log(data);
        let res = myAjax("http://localhost:8080/back/contact/set",data,'post');
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
    upload.render({
        elem: '#test1'
        ,url: 'http://localhost:8080/back/file/upload'
        ,headers: {'token': localStorage.getItem('codeKey')}
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
    });


});
      