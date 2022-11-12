let layedit = null;

layui.use(['layedit', 'form', 'upload', 'laydate', 'table'], function () {
    let form = layui.form;
    let laydate = layui.laydate;
    let upload = layui.upload;
    layedit = layui.layedit;
    let imgHref = '';

    //    页面加载完成首先需要加载菜系
    //    先将菜系内容查询出来，然后赋值即可
    let res = myAjax("http://localhost:8080/back/news/newNav", null, "get");
    setSelect(res);

    laydate.render({
        elem:'#L_date',
        trigger:'click'
    });
    form.render();
    //富文本的图片上传路径配置
    layedit.set({
        uploadImage:{
            url: "/back/file/upload"
        }
    });
    //富文本初始化     构建一个默认的编辑器
    let index = layedit.build('L_content');

    // 添加
    form.on('submit(add)', function (data) {
        data = data.field;
        data.imgHref=imgHref;
        data.content=layedit.getContent(index);
        console.log(data);
        let res=myAjax("http://localhost:8080/back/news/add",data,'post');
        if (res.count > 0) {
            layer.alert("增加成功", {
                    icon: 6
                },
                function () {
                    //关闭当前frame
                    xadmin.close();

                    // 可以对父窗口进行刷新
                    xadmin.father_reload();
                });
        } else {
            layer.alert("增加失败");
        }
        return false;
    })


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