$(function (){
    let data;
    let html;
    let i;
    // 实时导航
    sessionStorage.setItem('nav','私人教练');

    // 私人教练
    let ourTeamData = myAjax("http://localhost:8080/back/ourTeam/query",{},"get");
    if(ourTeamData.data!=null){
        data = ourTeamData.data;
        html = "";
        for(i = 0; i <data.length ; i++){
            html+='<div>\n' +
                '                        <div><img src="'+data[i].imgHref+'"></div>\n' +
                '                        <div class="title-black">'+data[i].name+'</div>\n' +
                '                        <div>'+data[i].position+'</div>\n' +
                '                    </div>';
        }
        $('.personal>').eq(1).html(html);
    }


})

