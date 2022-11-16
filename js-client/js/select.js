function setSelect(res){
    if (res != null) {
        setCuisineData(res.data);
    }
}
function setCuisineData(data) {
    let html = '<option value="0">所有类型</option>';
    for (let i = 0; i < data.length; i++) {
        html += '<option value="' + data[i].id + '">' + data[i].navTitle + '</option>';
    }
    $("#navId").html(html);
}
function setType(res){
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
}
