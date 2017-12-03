var pageNo = 1;
var pageSize = 3;
$(function(){
	$("#freightPlanlist").empty();
	index(1,1);
});

function index(No,flag){
	$.ajax({
		url : "/publicMember/rollingMessage/freightPlan",//
		data : {
			"pageNo":No,
			"pageSize":pageSize},
		type : "post",
		success : function(rs){
			if(rs.code=="000000"){
				innerHTML(rs.data,flag)
			}else{
				alert(rs.error);
			}
		}
	});
}

function moreSearch(){
	var no = pageNo + 1;
	index(no,1);
}

function innerHTML(ret,flag){
	var data = ret.list;
	pageNo = ret.pageNo;
	var total = ret.total;
	if(pageNo * pageSize > total){
		$("#moredate").hide();
	}else{
		$("#moredate").show();
	}
	if(flag == 0){
		$("#freightPlanlist").empty();
	}
	var hml = "";
	for (var a = 0; a < data.length; a++) {
			hml += '<div class="hycontlist" >';
			if(a%3 == 0){
				hml += '<div class="divju"></div>';
			}else if (a%3 == 1) {
				hml += '<div class="divblue"></div>';
			}else if (a%3 == 2) {
				hml += '<div class="divgreen"></div>';
			}
			hml += '<div class="w1200">';
			hml += '<div class="hyaddr">';
			hml += '<div class="hyaddrl fl">';
			hml += ' <img src="'+trRoot+'/tianrui/images/hybegin.png">';
			hml += ' <span>'+data[a].startCity+'</span>';
			hml += ' </div>';
			hml += '<div class="hyaddrr fr">';
			hml += '   <img src="'+trRoot+'/tianrui/images/hyend.png">';
			hml += '  <span>'+data[a].endCity+'</span>';
			hml += ' </div>';
			hml += ' </div>';
			hml += '<div class="hyroad">';
			hml += '<div class="hyroad_solo fl">';
			hml += '<div class="leftfont">';
			hml += '</div>';
			hml += '<div class="lefttip">';
			hml += ' <p>'+data[a].startCity+data[a].startName+'</p>';
			hml += '</div>';
			hml += '</div>';
			hml += '<div class="hyroadmid fl">';
			hml += '<div class="line1">';
			hml += ' <div class="hyroad_add fl">';
			hml += ' <img src="'+trRoot+'/tianrui/images/hypeople.png">';
			hml += '<span>承运人：'+data[a].vender+'</span>';
			hml += '</div>';
			hml += '<div class="fr">';
			hml += '<img src="'+trRoot+'/tianrui/images/hytime.png">';
			hml += '<span>'+new Date(data[a].starttime).format("yyyy-M-d")+ "至" +new Date(data[a].endtime).format("yyyy-M-d")+'</span>';
			hml += '</div>';
			hml += '</div>';
			hml += '<div class="line2">';
			hml += '<img src="'+trRoot+'/tianrui/images/hyline.png">';
			hml += '</div>';
			hml += '<div class="line3">';
			hml += '<div class="huo fl">';
			hml += '<img src="'+trRoot+'/tianrui/images/hygoods.png">';
			hml += '<span>'+data[a].cargoname+data[a].totalplanned+data[a].measure+'</span>';
			hml += '</div>';
			hml += '<div class="price fl">';
			hml += '<label>'+data[a].price+data[a].priceunits+'</label>';
			hml += '</div>';
			hml += '<div class="juli fr">';
			hml += '<img src="'+trRoot+'/tianrui/images/hygps.png">';
			hml += '<span>'+data[a].distance+'公里</span>';
            hml += '</div>';
            hml += '</div>';
            hml += '</div>';
            hml += '<div class="hyroad_solo fr">';
            hml += '<div class="rightfont">';
            hml += '</div>';
            hml += '<div class="righttip">';
            hml += '<p>'+data[a].endCity+data[a].endName+'</p>';
            hml += '</div>';
            hml += '</div>';
            hml += '</div>';
            hml += '</div>';
            hml += '</div>';
	}
	$("#freightPlanlist").append(hml);
	$(function () {
	    $(".leftfont").mouseover(function (){
	    	$(this).next(".lefttip").css("display","block");
	    }).mouseout(function (){
	        $(".lefttip").css("display","none");
	    });
	    $(".rightfont").mouseover(function (){
	    	$(this).next(".righttip").css("display","block");
	    }).mouseout(function (){
	        $(".righttip").css("display","none");
	    });

	    function show_hidden(obj) {
	        if(obj.css("display") == 'block') {
	            obj.css("display",'none');

	        } else if(obj.css("display") == 'none'){
	            obj.css("display",'block');
	        }
	    }
	});
}
$(window).scroll(function () {
    //$(window).scrollTop()这个方法是当前滚动条滚动的距离
    //$(window).height()获取当前窗体的高度
    //$(document).height()获取当前文档的高度
    if (($(document).scrollTop()) >= ($(document).height() - $(window).height())) {
    		moreSearch();
    }
});