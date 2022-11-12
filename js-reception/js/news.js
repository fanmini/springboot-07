let data;
let html;
let i;
$(function (){
// 新闻内容赋值
    // 实时导航
    sessionStorage.setItem('nav','新闻资讯');
    //标题赋值
    let navData = myAjax("http://localhost:8080/back/news/newNav",{},"get");
    if (navData.data!=null) {
        data = navData.data;
        html = "";
        for (i = 0; i < data.length; i++) {
            html += '<div onclick=newsContent(' + data[i].id+',this)>' + data[i].navTitle + '</div>';
        }
        $(".news>").eq(0).html(html);
        // 新闻内容
        let obj = $(".news>div:nth-child(1)>div:nth-child(1)");
        newsContent(data[0].id,obj);

    }

})
function newsContent(id,obj){
    let newsData = myAjax("http://localhost:8080/back/news/query",{nId:id},"get");
    if(newsData!=null){
        data = newsData.data ;
        // 添加背景颜色
        $(".news>div:nth-child(1)>div").css("background-color","black");
        let a = $(obj);
        a.css("background-color","red");
        html="";
        for(i = 0 ; i < data.length ; i++){
            html+='<div class="centers">\n' +
                '                        <div>\n' +
                '                            <div>'+data[i].date+'</div>\n' +
                '                        </div>\n' +
                '                        <div>\n' +
                '                            <div class="news-title">'+data[i].title+'</div>\n' +
                '                            <div class="title-text many-over content">'+data[i].content+'</div>\n' +
                '                        </div>\n' +
                '                    </div>'
        }
        $(".news>div").eq(1).html(html)

    }

}