var pageNo = 1;
var pageSize = 3;
$(function(){
	$("#freightDemandlist").empty();
	index(1,1);
});

function index(No,flag){
	$.ajax({
		url : "/publicMember/rollingMessage/freightDemand",//
		data : {
			"startOp":$("#zhuanghuo").val(),
			"endOp":$("#xiehuo").val(),
			"cargoname":$("#modal_add_materName" ).val(),
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
	if(flag == 0){
		$("#freightDemandlist").empty();
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
			hml += ' <img src="'+trRoot+'/tianrui/images/hytime.png">';
			hml += '<span>'+new Date(data[a].starttime).format("yyyy-M-d")+ "至" +new Date(data[a].endtime).format("yyyy-M-d")+'</span>';
			hml += '</div>';
			hml += '<div class="fr">';
			hml += '<img src="'+trRoot+'/tianrui/images/hygps.png">';
			hml += '<span>'+data[a].distance+'公里</span>';
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
			hml += '<div class="chengyun fr">';
			hml += '<a data-toggle="modal" data-target="#edit">';
			hml += '<button type="submit" class="btn" >承运</button>';
			hml += '</a>';
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
	$("#freightDemandlist").append(hml);
	//弹出层
	$(".chengyun .btn").on('click', function () {
	    $(".hy_alert").show(300);
	});
	//隐藏层
	$(".hy_alert button").on('click', function () {
	    $(".hy_alert").hide(300);
	});
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

var i = 0;
//找货查询
$(".zhaohuo").on('click', function () {
    var div1 = $(".searcont");
    var div2 = $(".zhaohuoright");
    div1.toggle();
    div2.toggle();
    if (div1.css("display") == 'none') {
        $("#gengduo").attr('src', trRoot+"/tianrui/images/hydown.png");
    }
    else {
        $("#gengduo").attr('src', trRoot+"/tianrui/images/hyup.png");
    }
    getAndMakeCargoList();
});
$(".zhaohuoleft label").on('click', function () {
	var div1 = $(".searcont");
    var div2 = $(".zhaohuoright");
    div1.toggle();
    div2.toggle();
    if (div1.css("display") == 'none') {
        $("#gengduo").attr('src', trRoot+"/tianrui/images/hydown.png");
    }
    else {
        $("#gengduo").attr('src', trRoot+"/tianrui/images/hyup.png");
    }
    getAndMakeCargoList();
});
$(".zhaohuoleft span").on('click', function () {
	var div1 = $(".searcont");
    var div2 = $(".zhaohuoright");
    div1.toggle();
    div2.toggle();
    if (div1.css("display") == 'none') {
        $("#gengduo").attr('src', trRoot+"/tianrui/images/hydown.png");
    }
    else {
        $("#gengduo").attr('src', trRoot+"/tianrui/images/hyup.png");
    }
    getAndMakeCargoList();
});
//找货功能点击
 function getAndMakeCargoList() {
   if(i > 0){
	   return;
   }
var materNameData = new Array();
	$("#modal_add_materName").val("");
	$.ajax({
		url : '/publicMember/rollingMessage/getCargo',// 跳转到 action
		data : {
			"pageNo":1,
			"pageSize":198
		},
		type : "post",
		success : function(result) {
			var data = result.data;
			for (var i=0; i<data.length; i++) {
				var orgObj =  new Object();
				orgObj.value = data[i].id;
				orgObj.label = data[i].materName;
				materNameData[i] = orgObj;
			}
			$("#modal_add_materName").autocomplete({
				minLength: 0,
		        source: materNameData,
		        select: function( event, ui ) {
		            $( "#modal_add_materName" ).val( ui.item.label );
		            $( "#modal_add_materId" ).val( ui.item.value );
		            return false;
		        }
		    }).off('click').on('click',function(){
		    	$(this).autocomplete('search');
		    });
		}
	});
	i++;
};
$("#ps1").pickArea({
    "format":"province/city", //格式
    "width":"300",
    "borderColor":"#cccccc",//文本边框的色值
    "arrowColor":"#cccccc",//下拉箭头颜色
    "listBdColor":"#cccccc",//下拉框父元素ul的border色值
    "color":"#555555",//字体颜色
    "hoverColor":"#f7792d",
    //"preSet":"山东省/临沂市/兰陵县", //预先设置地址
    "getVal":function(){
        var thisdom = $("."+$(".pick-area-dom").val());
        thisdom.next().val($(".pick-area-hidden").val());
        $("#zhuanghuo").val(($(".pick-area-hidden").val()));
    }
});
$("#ps2").pickArea({
    "format":"province/city", //格式
    "width":"300",
    "borderColor":"#cccccc",//文本边框的色值
    "arrowColor":"#cccccc",//下拉箭头颜色
    "listBdColor":"#cccccc",//下拉框父元素ul的border色值
    "color":"#555555",//字体颜色
    "hoverColor":"#f7792d",
    "getVal":function(){
        var thisdom = $("."+$(".pick-area-dom").val());
        thisdom.next().val($(".pick-area-hidden").val());
        $("#xiehuo").val(($(".pick-area-hidden").val()));
    }
});
function zhuanghuo() {
	index(1,0);
}
function reset() {
	$("#modal_add_materName").val("");
	$("span.pick-province").text("请选择省");
    $("span.pick-city").text("请选择市");
	$("#zhuanghuo").val("");
	$("#xiehuo").val("");
	index(1,0);
}