let layedit = null ;
layui.use(['form', 'layedit','layer', 'laydate','upload'], function () {
    var form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        laydate = layui.laydate;
    layedit =layui.layedit ;
    let  imgHref ="";


    // 时间渲染
    laydate.render({
        elem:'#L_date',
        trigger:'click'
    });
    // 富文本
    layedit.set({
        uploadImage: {
            url: '/back/file/upload',
            type: 'post' // 默认post
        }
    });
    let index = layedit.build("L_content");
    let res = myAjax("http://localhost:8080/back/news/newNav", null, "get");
    setSelect(res);
    findById();
    // 通过拿到的id值回显修改前的值
    // 查询
    function findById(){
        let id = sessionStorage.getItem("newsId");
         let res = myAjax("http://localhost:8080/back/news/query",{id:id},"get");
         if(res!=null) {
             setUserData(res.data);
         }
    }
    // 赋值
    function setUserData(data){
            form.val('news-edit', {
                "nId": data.nid // "name": "value"
                ,"date": data.date // "name": "value"
                ,"title": data.title // "name": "value"
                ,"enable": data.enable==0?'0':'1' // 状态 启用禁用
            });
        layedit.setContent(index,data.content);
        $('#demo1').attr("src",data.imgHref);
        imgHref = data.imgHref ;
    }
    //form 表单重新渲染
    form.render();
    // 监听按钮
    form.on('submit(edit)',function (data){
        if(data.field.nId==null){
            alert('请选择类型');
            return false;
        }
        data = data.field;
        data.imgHref=imgHref;
        data.id=sessionStorage.getItem('newsId');
        console.log(data);
        let res = myAjax("http://localhost:8080/back/news/set",data,'post');
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
        ,headers: {'token': localStorage.getItem("codeKey")}
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
      