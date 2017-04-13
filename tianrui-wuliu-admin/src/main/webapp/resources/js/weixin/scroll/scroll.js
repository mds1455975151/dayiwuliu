$(function(){
	indexPageOf();
	$(window).scroll(function () {
        //$(window).scrollTop()这个方法是当前滚动条滚动的距离
        //$(window).height()获取当前窗体的高度
        //$(document).height()获取当前文档的高度
        var bot = 0; //bot是底部距离的高度
        if ((bot + $(window).scrollTop()) >= ($(document).height() - $(window).height())) {
        	var pageNo = $("#pageNo").val();
        	$("#pageNo").val((parseInt(pageNo)+parseInt(1)));
        	indexPageOf();
        }
    });
});

function yiShenpage(state){
	var searchKey = $("#searchKey").val();
	window.location.href="/weixin/login/yiShenPage?state="+state+"&searchKey="+searchKey;
}
