 $(function(){
	$("#top").load("top.html");
	$("#foot").load("foot.html");
	$("#emun-nav").load("emnu-nav.html");
	$("#page").load("page.html");
	$("#top-menu").load("top-menu.html");
})
// 跳转私人教练界面
function openOurTeam(){
	window.open('/html/personal-traniner.html','_self')
}
// 跳转新闻界面
function openNews(){
	window.open('/html/news.html','_self')
}

// 预约提交在线留言
function add() {
	let phone = $('.inp[name="phone"]').val();
	if(phone==null && phone.length<=0){
		alert("手机号不能为空！");
	}
	else{
		var re = /^1\d{10}$/
		if (re.test(phone)) {
			var name = $('.inp[name="name"]').val();
			var email = $('.inp[name="email"]').val();
			var content = $('.inp[name="content"]').val();
			var res  = myAjax("http://localhost:8080/back/customer/add",{name:name,phone:phone,email:email,content:content},"post")
			res.code==0?alert("预约成功"):alert("预约失败");
			$('.inp').val("");
		} else {
			alert("手机号格式错误！");
		}
	}
}