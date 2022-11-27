function myAjax(url,data,type){
    let result = null;
    $.ajax({
        url:url,
        data:data,
        type:type,
        dataType:'json',
        async:false,
        success:function (res){
            result=res;
        }
    })
    return result;
}