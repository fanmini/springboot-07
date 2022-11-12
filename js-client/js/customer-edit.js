layui.use(['form', 'layedit','layer', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate;

    findById();
    // 通过拿到的id值回显修改前的值
    // 查询
    function findById(){
        let id = sessionStorage.getItem("customerId");
         let res = myAjax("http://localhost:8080/back/customer/query",{id:id},"get");
         if(res!=null) {
             setUserData(res.data);
         }
    }
    // 赋值
    function setUserData(data){
            form.val('customer-edit', {
                "name": data.name // "name": "value"
                ,"phone": data.phone // "name": "value"
                ,"email": data.email // "name": "value"
                ,"content": data.content // "name": "value"
                ,"enable": data.enable==0?'0':'1' // 状态 启用禁用
            });
    }
    //form 表单重新渲染
    form.render();
    // 监听按钮
    form.on('submit(edit)',function (data){
        data = data.field;
        data.id=sessionStorage.getItem('customerId');
        console.log(data);
        let res = myAjax("http://localhost:8080/back/customer/set",data,'post');
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
});
      