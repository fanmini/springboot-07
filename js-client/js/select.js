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
    $("#nId").html(html);
}

