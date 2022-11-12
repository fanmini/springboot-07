layui.use(['upload','laydate','layer','form'],function(){
    let $ = layui.jquery
        ,upload = layui.upload
        ,layer= layui.layer
        ,laydate = layui.laydate
        ,form = layui.form;
    let imgHref  = '';
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

    //
    form.on('submit(add)', function(data){
        data = data.field;
        data.imgHref = imgHref;
        let res = myAjax("http://localhost:8080/back/student/add",data,'post');
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