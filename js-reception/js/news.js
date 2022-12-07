let data;
let html;
let i;
$(function () {
// 新闻内容赋值
    // 实时导航
    sessionStorage.setItem('nav', '新闻资讯');
    //标题赋值
    let navData = myAjax("http://localhost:8080/back/nav/typeAll/1", {}, "get");
    if (navData != null && navData.data != null) {
        data = navData.data;
        html = "";
        for (i = 0; i < data.length; i++) {
            html += '<div class="newsTitle" onclick=newsContent(' + data[i].id + ',this)>' + data[i].navTitle + '</div>';
        }
        $(".news>").eq(0).html(html);
        // 新闻内容
        let obj = $(".news>div:nth-child(1)>div:nth-child(1)");
        newsContent(data[0].id, obj);

    }

})
// 展示对应新闻内容
function newsContent(id, obj) {
    sessionStorage.setItem('navId',id);
    let newsData = myAjax("http://localhost:8080/back/news/findAllByLike", {navId: id}, "get");
    extracted(newsData,obj);
}
// 展示对应新闻内容具体方法
function extracted(newsData,obj) {
    if (newsData != null) {
        data = newsData.data;
        // 添加背景颜色
        $(".news>div:nth-child(1)>div").css("background-color", "black");
        let a = $(obj);
        a.css("background-color", "red");
        html = "<div><input id=\"query\" type=\"text\" placeholder=\"新闻检索\"/>\n" +
            "                <button onclick=\"esFindByWord(this)\" type=\"button\" value=\"搜索\">搜索</button>\n" +
            "            </div>\n" +
            "            <br/>";
        // 新闻条目
        for (i = 0; i < data.length; i++) {
            html += '<div class="centers">\n' +
                '                        <div>\n' +
                '                            <div>' + data[i].date + '</div>\n' +
                '                        </div>\n' +
                '                        <div>\n' +
                '                            <div class="news-title" onclick=showContent(' + data[i].id +','+ data[i].index + ')>' + data[i].title + '</div>\n' +
            '                            <div class="title-text many-over content">' + data[i].content + '</div>\n' +
            '                        </div>\n' +
            '                    </div>';
        }
        $(".news>div").eq(1).html(html)
    }
}


// 显示某条新闻的具体内容
function showContent(id,index) {
    if(index != null && index > 0 ){
        id = index ;
    }
    console.log(id);
    let newContent = myAjax("http://localhost:8080/back/news/query/" + id, {}, "get");
    html = '';
    if (newContent != null) {
        $(".news").html(html + newContent.data.content);
    }

}

// 通过搜索高光回显新闻内容
//    注意点就是新闻分类
function esFindByWord(obj) {
    // 获取navId
    var navId = sessionStorage.getItem("navId");
    var content = $('#query').val()
    // es 搜索
    let esContent = myAjax("http://localhost:8080/back/news/findAllByLike", {navId: navId, content: content}, "get");
    extracted(esContent,obj)
}