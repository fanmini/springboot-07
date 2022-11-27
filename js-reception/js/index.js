$(function (){
    let data;
    let html;
    let i;
// 关于我们的赋值
    let companyData = myAjax("http://localhost:8080/back/companyProfile/query",{},"get");
    if (companyData != null && companyData.data != null) {
        data = companyData.data;
        $(".infos>div:last").html('<div>' + data[0].content + '</div>');
        $("#indexSubContent").html('<div class="title-text many-over">' + data[1].content + '</div>');
        $("#companyImg").attr('src', data[1].imgHref);
    }
    // 私人教练
    let ourTeamData = myAjax("http://localhost:8080/back/ourTeam/query",{},"get");
    if (ourTeamData != null && ourTeamData.data != null) {
        data = ourTeamData.data;
        html = "";
        let size = 3;
        if (data.length < 3) {
            size = data.length;
        }
        for (i = 0; i < size; i++) {
            html += '<div class="coach">\n' +
                '                        <div><img src="' + data[i].imgHref + '"></div>\n' +
                '                        <div>' + data[i].name + '</div>\n' +
                '                        <div>' + data[i].position + '</div>\n' +
                '                    </div>'
        }
        $('#indexOurTeam').html(html);
    }

    // 新闻界面赋值
    let newsData = myAjax("http://localhost:8080/back/news/query",{},"get");
    if (newsData != null && newsData.data != null) {

        data = newsData.data;
        html = "";
        let size = 3;
        if (data.length < 3) {
            size = data.length;
        }
        for (i = 0; i < size; i++) {
            html += '<div class="news-img">\n' +
                '                        <div><img src="' + data[i].imgHref + '"></div>\n' +
                '                        <div class="news-text">\n' +
                '                            <div class="single-over">' + data[i].title + '</div>\n' +
                '                            <div>' + data[i].date + '</div>\n' +
                '                            <div class="many-over">' + data[i].content + '</div>\n' +
                '                        </div>\n' +
                '                    </div>'
        }
    }
    $('.news>div[class="centers news-content"]').html(html);
})

