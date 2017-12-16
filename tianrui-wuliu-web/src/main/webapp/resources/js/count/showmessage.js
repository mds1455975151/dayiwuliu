
    window.onload = function() {
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
		

function init(){
	$.ajax({
		url:"/publicMember/rollingMessage/findRollingMessage",
		type:"POST",
		data: {},
		success:function(ret){
			if(ret.code == '000000'){
				var data = ret.data;
				innerHml(data);
			}
		}
	});
}

function innerHml(data){
	$("#messgeloop").empty();
		for (var a = 0; a < data.length; a++) {
			var messtype ="";
			var typenum = data[a].messageType;
			var imgsrc="";
			if(typenum == 1){
				messtype="托运新消息";
				imgsrc="/tianrui/images/hym_ty.png";
			}else if(typenum == 2){
				messtype="承运新消息";
				imgsrc="/tianrui/images/hym_cy.png";
			}
			else if(typenum == 3){
				messtype="装货新消息";
				imgsrc="/tianrui/images/hym_zh.png";
			}
			else if(typenum == 4){
				messtype="卸货新消息";
				imgsrc="/tianrui/images/hym_xh.png";
			}
			if(a==0){
				var hml = "<div class='swiper-slide swiper-slide-center none-effect'>"+
			    "<a><div class='midcont'><div class='mtitle'>" +
				"<img src='" + trRoot + imgsrc + "'>"+
				"<span>"  + messtype+ "</span>"+
				"<label>" + data[a].time + "<label></div>"+
				"<div class='maddr'>"+
				"<img src='"+trRoot+"/tianrui/images/hym_yello.png"+"'>"+
				"<label>" + data[a].departure + "<label></div>"+
				"<div class='maddr'>"+
				"<img src='"+trRoot+"/tianrui/images/hym_blue.png"+"'>"+
				"<label>" + data[a].Unloading + "<label></div>"+
				"<div class='maddbott'>"+
				"<label>" + data[a].messageContent + "<label></div>"+
				"</div></a></div>"
			}else{
				var hml = "<div class='swiper-slide'>"+
			    "<a><div class='midcont'><div class='mtitle'>" +
				"<img src='" + trRoot + imgsrc + "'>"+
				"<span>"  + messtype+ "</span>"+
				"<label>" + data[a].time + "<label></div>"+
				"<div class='maddr'>"+
				"<img src='"+trRoot+"/tianrui/images/hym_yello.png"+"'>"+
				"<label>" + data[a].departure + "<label></div>"+
				"<div class='maddr'>"+
				"<img src='"+trRoot+"/tianrui/images/hym_blue.png"+"'>"+
				"<label>" + data[a].Unloading + "<label></div>"+
				"<div class='maddbott'>"+
				"<label>" + data[a].messageContent + "<label></div>"+
				"</div></a></div>"
			}

			$("#messgeloop").append(hml);
			
		}
}
init();


