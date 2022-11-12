layui.use(['table','form','laydate'], function(){
    var table = layui.table;
    let form = layui.form;
    let laydate = layui.laydate;


    // 渲染表单
    findAll();
    // 渲染模糊类型
    let res = myAjax("http://localhost:8080/back/product/productNav", null, "get");
    setSelect(res);
    form.render()
    // 模糊查询按钮监听
    form.on('submit(sreach)', function(data){
        data = data.field;
        console.log(data)
        findAll(data);
        return false;
    })
    // 渲染表单方法
    function findAll(data){
        table.render({
            elem: '#test'
            ,url:'http://localhost:8080/back/product/query'
            ,headers: {'token': localStorage.getItem('codeKey')}
            ,where: data
            ,page: true
            ,cols: [[
                ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field:'imgHref', title:'图片', width:80, edit:'text',  templet:function (res){
                    return "<img src='"+res.imgHref+"' width='50'/>"
                    }}
                ,{field:'navTitle', title:'产品类型', width:80, edit:'text', templet: function(res){
                        return res.navTitle==null?
                            '<div>未定义</div>': res.navTitle;
                    }}
                ,{field:'name', title:'产品名称', width:200, edit:'text'}
                ,{field:'price', title:'产品价格', width:200, edit:'text'}
                ,{field:'enable', title:'状态', width:150, edit: 'text', templet: function(res){
                        return res.enable==0?
                            '<em style="color: green">'+'启用'+'</em>':
                            '<em style="color: red">'+ '停用' +'</em>'
                    }}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150 }
            ]],parseData: function (res){
                if(res.code==1003){
                    window.alert(res.msg);
                    window.open("/login/login.html");
                }
            }
            ,error:function(res){
                window.alert(res.msg);
                window.alert("错误代码"+res.code);
                window.open("/login/login.html");
            }
        });
    }


    //触发单元格工具事件
    table.on('tool(test)', function (obj) { // 双击 toolDouble
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                // 删除数据库数据
                let myAjax1 = myAjax('http://localhost:8080/back/product/del', {id:data.id,nId:data.nId},'post');

                if(myAjax1.count>0){ // 判断是否删除成功
                    layer.msg('删除成功',function (){
                        obj.del(); // 删除前端数据
                        layer.close(index);
                    });
                }else{
                    layer.msg('删除失败')
                }
            });
        } else if (obj.event === 'edit') {
            sessionStorage.setItem('productId',data.id);
            xadmin.open('简介修改','/html/product/product-edit.html',600,400);
        }
    });
});
                    