$(function (){
    let data;
    let html;
    let i;
    // 实时导航
    sessionStorage.setItem('nav','联系我们');
    // 私人教练
    let contactData = myAjax("http://localhost:8080/back/contact/query",{},"get");
    if(contactData.data!=null){
        data = contactData.data;
        html = "";
        for(i = 0; i <data.length ; i++){
            html+='<div>\n' +
                '                        <div><img src="'+data[i].imgHref+'"></div>\n' +
                '                        <div class="single-over">'+data[i].text+'</div>\n' +
                '                    </div>';
        }
        $('.company-info').html(html);
    }


})

