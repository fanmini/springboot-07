let imgHref =  '';
let layedit = null ;
layui.use(['layedit', 'form', 'upload', 'laydate', 'table'], function () {
    let form = layui.form;
    let laydate = layui.laydate;
    let upload = layui.upload;
    layedit = layui.layedit;

    //    页面加载完成首先需要加载菜系
    //    先将菜系内容查询出来，然后赋值即可
    let res = myAjax("/back/nav/typeAll/1", null, "get");
    setSelect(res);
    // 时间组件
    laydate.render({
        elem:'#L_date',
        trigger:'click'
    });
    form.render();
    //富文本的图片上传路径配置
    layedit.set({
        uploadImage:{
            url: "http://localhost:8080/back/file/upload"
            ,headers:{"token":sessionStorage.getItem("token")}
        }
    });
    //富文本初始化构建一个默认的编辑器
    let index = layedit.build('L_content');

    // 添加新闻
    form.on('submit(add)', function (data) {
        data = data.field;
        data.imgHref=imgHref ;
        data.content=layedit.getContent(index);
        let res=myAjax("/back/news/add",data,'post');
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
            layer.alert(res.msg);
        }
        return false;
    })

    // 添加新闻标题
    form.on('submit(addNav)', function (data) {
        data = data.field;
        data.type=1;
        let res=myAjax("/back/nav/add",data,'post');
        if (res.count > 0) {
            layer.alert("增加成功", {  icon: 6  },
                function () {
                    //关闭当前frame
                    xadmin.close();
                    // 可以对父窗口进行刷新
                    xadmin.father_reload();
                });
        } else { layer.alert(res.msg); }
        return false;
    })

    // 文件上传
    uploadFile();


});