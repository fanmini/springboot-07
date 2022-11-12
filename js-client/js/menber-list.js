layui.use(['table','form','laydate'], function(){
    var table = layui.table;
    let form = layui.form;
    let laydate = layui.laydate;
    // 定义表格字段
    var cols = [[
        {field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
        ,{field:'userName', title:'用户名', width:120, edit: 'text'}
        ,{field:'phone', title:'电话', width:120, edit: 'text'}
        ,{field:'email', title:'邮箱', width:150, edit: 'text', templet: function(res){
                return '<em>'+ res.email +'</em>'
            }}
        ,{field:'enable', title:'状态', width:150, edit: 'text', templet: function(res){
                return res.enable==0?
                    '<em style="color: green">'+'启用'+'</em>':
                    '<em style="color: red">'+ '停用' +'</em>'
            }}
        ,{field:'hobby', title:'爱好', width:120, edit: 'text'}
        ,{field: 'createTime', title:'创建时间',width:120, edit: 'text' }
        // templet: function (res){return "<div>"+layui.util.toDateString(res.createTime, 'yyyy-MM-dd')+"</div>"}
        ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:240 }
    ]];
    // 渲染表单
    tableData('/back/user/query',null,cols);
    // 渲染时间日期
    laydate.render({
        elem:'#createTime'
    });
    form.render()
    // 模糊查询按钮监听
    form.on('submit(sreach)', function(data){
         data = data.field;
         // 获取技能数据
        let arr=[];
        $('input[type=checkbox][name=hobby]:checked').each(function(){
            arr.push($(this).attr("title"));
        })
        data.hobby=arr.toLocaleString();
        console.log(data)
        tableData("/back/user/query",data,null);
        return false;
    })
    // 模糊查询数据格式验证
    form.verify({
        username: [/^[a-zA-Z0-9_-]{4,16}$/,'用户名4到16位'],
        phone: [/r'[1-9][0-9]{10}'/,'请输入正确的手机号'],
        email: [/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,'请输入正确的邮箱格式'],
        hobby: [/^[a-zA-Z0-9_-]{4,16}$/,'用户名4到16位']
    })


    //触发单元格工具事件
    table.on('tool(test)', function (obj) { // 双击 toolDouble
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                // 删除数据库数据
                let myAjax1 = myAjax('/back/user/del/'+data.id, null,'DELETE');
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
            sessionStorage.setItem('userId',data.id);
            xadmin.open('添加用户','/html/user/member-edit.html',600,400);
        }else if (obj.event === 'password'){
            sessionStorage.setItem('userId',data.id);
            xadmin.open('修改密码','/html/user/member-password.html',600,400);
        }
    });
});
                    