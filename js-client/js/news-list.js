layui.use(['table','form','laydate','jquery'], function(){
    var table = layui.table;
    let form = layui.form;
    let laydate = layui.laydate;
    let jquery = layui.jquery;

    // 渲染表单
    var cols = [[
        {field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
        ,{field:'imgHref', title:'图片', width:80, edit:'text',  templet:function (res){
                return "<img src='"+res.imgHref+"' width='50'/>"
            }}
        ,{field:'navId', title:'新闻类型', width:80, edit:'text', templet: function(res){
               if(res.navId==null){
                    return '<div>未定义</div>';
                }else{
                   var myselect=document.getElementById("navId");
                   for (var i=0; i<myselect.length; i++){
                       if(myselect.options[i].value==res.navId){
                           return myselect.options[i].text;
                       }
                   }
                }
            }}
        ,{field:'date', title:'新闻日期', width:80, edit:'text'}
        ,{field:'title', title:'新闻标题', width:80, edit:'text'}
        ,{field:'content', title:'内容简介', width:200, edit:'text'}
        ,{field:'enable', title:'状态', width:100, edit: 'text', templet: function(res){
                return res.enable==0?
                    '<em style="color: green">'+'启用'+'</em>':
                    '<em style="color: red">'+ '停用' +'</em>'
            }}
        ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:250 }
    ]]
    tableData('/back/news/query',null,cols)

    // 渲染类型
    let nav_res  = myAjax("/back/nav/typeAll/1", null, "get");
    setSelect(nav_res);

    form.render();
    // 渲染时间日期
    laydate.render({
        elem:'#historyDate'
    });
    // 模糊查询按钮监听
    form.on('submit(sreach)', function(data){
        data = data.field;
        tableData('/back/news/findAllByLike',data,cols);
        return false;
    })

    //触发单元格工具事件
    table.on('tool(test)', function (obj) { // 双击 toolDouble
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                // 删除数据库数据
                let myAjax1 = myAjax('/back/news/del/'+data.id,null ,'DELETE');
                if(myAjax1.count>0){ // 判断是否删除成功
                    layer.msg('删除成功',function (){
                        obj.del(); // 删除前端数据
                        layer.close(index);
                    });
                }else{
                    layer.msg(myAjax1.msg);
                }
            });
        } else if (obj.event === 'delNav') {
            layer.confirm('删除新闻类型', function (index) {
                // 删除数据库数据
                let myAjax1 = myAjax('/back/nav/del/'+data.id, null,'DELETE');
                if(myAjax1.count>0){ // 判断是否删除成功
                    layer.msg('删除成功',function (){
                        layer.close(index);
                    });
                }else{
                    layer.msg(myAjax1.msg);
                }
            });
        }
        else if (obj.event === 'edit') {
            sessionStorage.setItem('dataItem',JSON.stringify(data));
            xadmin.open('编辑','/html/news/news-edit.html',600,400);
        }
    });
});
                    