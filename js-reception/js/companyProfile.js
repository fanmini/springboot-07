$(function (){
    let data;
    let html;
    let i;
    // 实时导航
    sessionStorage.setItem('nav','关于我们');
// 关于我们的赋值
    let companyData = myAjax("http://localhost:8080/back/companyProfile/query",{},"get");
    if (companyData.data!=null){
        data = companyData.data;
        $(".our-info>div:first>img").attr("src",data[2].imgHref);
        $(".our-info>").eq(1).html('<div class="info-text title-text">'+data[2].content+'</div>' )
        // 公司历史
        html="";
        var le =6 ;
        data.length<le?le=data.length:le=6;
        for (i=3; i<le; i++){
            html+='<div class="centers">\n' +
                '                    <div><img src="'+data[i].imgHref+'" alt=""></div>\n' +
                '                    <div>\n' +
                '                        <div>'+data[i].date+'</div>\n' +
                '                        <div>\n' +
                '                            <div class="many-over">'+data[i].content+'</div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                </div>'
        }
        $('.history').html(html);
    }
    // 学员们风采赋值
    let studentData = myAjax("http://localhost:8080/back/student/query",{},"get");
    if (studentData.data!=null){
        data = studentData.data;
        html="";
        for (i=0; i<data.length;i++){
            html += '<div><img src="'+data[i].imgHref+'"></div>'
        }
        $('.student-img>div:first').html(html)
    }

})
