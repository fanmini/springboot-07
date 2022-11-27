let data;
let html;
let i;
$(function (){
    // 实时导航
    sessionStorage.setItem('nav','产品中心');

// 新闻内容赋值
    //标题赋值
    let navData = myAjax("http://localhost:8080/back/nav/typeAll/2", {},"get");
    if (navData.data!=null) {
        data = navData.data;
        html = "";
        for (i = 0; i < data.length; i++) {
            html += '<div onclick=newsContent(' + data[i].id+',this)>' + data[i].navTitle + '</div>';
        }
        $(".product>").eq(0).html(html);
        // 产品内容
        let obj = $(".product>div:nth-child(1)>div:nth-child(1)");
        newsContent(data[0].id,obj);

    }

})
function newsContent(id,obj){
    let productData = myAjax("http://localhost:8080/back/product/query",{navId:id},"get");
    if(productData!=null){
        data = productData.data ;
        // 添加背景颜色
        $(".product>div:nth-child(1)>div").css("background-color","black");
        let a = $(obj);
        a.css("background-color","red");
        // 赋值
        html="";
        for(i = 0 ; i < data.length ; i++){
            html+='<div>\n' +
                '                        <div><img src="'+data[i].imgHref+'"></div>\n' +
                '                        <div>'+data[i].name+'</div>\n' +
                '                        <div class="centers">\n' +
                '                            <div>价格：</div>\n' +
                '                            <div>'+data[i].price+'</div>\n' +
                '                        </div>\n' +
                '                    </div>';
        }
        $(".product>div").eq(1).html(html)

    }

}