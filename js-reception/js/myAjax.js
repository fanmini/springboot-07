function myAjax(url,data,type){
    let result = null;
    $.ajax({
        url:url,
        data:data,
        type:type,
        dataType:'json',
        async:false,
        headers: {'token': 'reception'},
        success:function (res){
            result=res;
        }
    })
    return result;
}