init();
function init(){
	$.ajax({
		url:"/publicMember/rollingMessage/findRollingMessage",
		type:"POST",
//		async : false,
		data: {},
		success:function(ret){
			if(ret.code == '000000'){
				var data = ret.data;
				innerHml(data);
			}
		}
	});
}
function show(){
	   var mydate = new Date();
	   var str = mydate.getFullYear() + "年";
	   str += (mydate.getMonth()+1) + "月";
	   str += mydate.getDate() + "日";
	   return str;
	  }

function innerHml(data){
	$("#messgeloop").empty();
	var mydate = new Date();
	
	for (var a = 0; a < data.length; a++) {
		var messtype ="";
		var messdate = "";
		var typenum = data[a].messageType;
		var imgsrc="";
		if(typenum == 1){
			messtype="托运新消息";
			imgsrc="/tianrui/images/hym_ty.png";
			messdate=data[a].time;
		}else if(typenum == 2){
			messtype="承运新消息";
			imgsrc="/tianrui/images/hym_cy.png";
			messdate=data[a].time;
		}
		else if(typenum == 3){
			messtype="装货新消息";
			imgsrc="/tianrui/images/hym_zh.png";
			messdate=show();
		}
		else if(typenum == 4){
			messtype="卸货新消息";
			imgsrc="/tianrui/images/hym_xh.png";
			messdate=show();
		}
		var hml = "<div class='swiper-slide'>"+
	    "<a><div class='midcont'><div class='mtitle'>" +
		"<img src='" + trRoot + imgsrc + "'>"+
		"<span>"  + messtype+ "</span>"+
		"<label>" + messdate + "<label></div>"+
		"<div class='maddr'>"+
		"<img src='" + trRoot + "/tianrui/images/hym_yello.png"+"'>"+
		"<label>" + data[a].departure + "<label></div>"+
		"<div class='maddr'>"+
		"<img src='"+trRoot+"/tianrui/images/hym_blue.png"+"'>"+
		"<label>" + data[a].unloading + "<label></div>"+
		"<div class='maddbott'>"+
		"<label>" + data[a].messageContent + "<label></div>"+
		"</div></a></div>"
		$("#messgeloop").append(hml);
	}
	var swiper = new Swiper('.swiper-container',{
	       autoplay:3000,
	       speed:1000,
	       autoplayDisableOnInteraction : false,
	       loop:true,
	       centeredSlides : true,
	       slidesPerView:2,
	       pagination : false,
	       paginationClickable:false,
	       prevButton:'.swiper-button-prev',
	       nextButton:'.swiper-button-next',

	   });
}
$(".indexlogin").on("click",function(){
    $(".login").css("display","block");
})
$(".loginclose").on("click",function(){
    $(".login").css("display","none");
})
