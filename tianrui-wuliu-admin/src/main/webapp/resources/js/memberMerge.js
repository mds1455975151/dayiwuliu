var list;

$(function(){
	//search();	
});

//搜索
function search(){
	 var idCard = $("#idCard").val();

	//15位和18位身份证号码的基本校验
   	 var check = /^\d{15}|(\d{17}(\d|x|X))$/.test(idCard);
   	 if(!check){
        layer.msg('请输入15位或18位正确的身份证号！！', {icon: 10});
  		return false;
   	 }
   	 
    $.ajax({
    	url:'/memberUser/findMemberInfo',
		data:{
			"idCard":$.trim(idCard),
		},
		type:"post",
		success: function(data) {
			if(data.code==000000){
				var ret = data.data.list;
				list = data.data.list;
				
			    if(data.data.count == 0) {
			    	$("#memberInfo").empty();
			    	var hml = "";
			    	hml +='<td colspan="12">';
		    		hml +='<div class="ht_none">';
		    		hml +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
		    		hml +='<div>';
		    		hml +='<h3>唉呀！查询不到您要找的内容</h3>';
		    		hml +='<p>换个查询条件试试吧？</p>';
		    		hml +='</div>';
		    		hml +='</div>';
		    		hml +='</td>';
		    		$("#memberInfo").append(hml);
			    }
				
				//填充数据
				$("#innerHtml").empty();
				for (var a = 0; a < ret.length; a++) {
					var input1 = $("<input name='dafult' class='check_member'></input>").attr("type","radio").attr("value",ret[a].id)
					var td1 = $("<td></td>").addClass("check-col").attr("field", "id");
					var td2 = $("<td></td>").attr("field", "cellphone").append(ret[a].cellPhone||"");
					var td3 = $("<td class='identity_Card'></td>").attr("field", "identityCard").append(ret[a].identityCard||"");
					var td4 = $("<td></td>").attr("field", "userName").append(ret[a].userName||"");
					var td5 = $("<td></td>").attr("field", "registtimeStr").append(ret[a].registtimeStr||"");
					var td6 = $("<td></td>").attr("field", "operation");
					var href1 = "<span><a data-toggle='modal' onclick=\"details('"+a+"')\" data-target='#detail'>【详情】</a></span>";
					var href2 = "<span><a data-toggle='modal' onclick=\"remove(this)\" data-target='#remove'>【移除】</a></span>";
					var span1 = $("<span></span>").append(href1);
					var span2 = $("<span></span>").append(href2);
					td1.append(input1);
					td6.append(span1).append(span2)
					
					var tr = $("<tr></tr>").append(td1).append(td2).append(td3).append(td4).append(td5).append(td6);		
					$("#memberInfo").append(tr);
				}
			}
		}
    });
}

//重置
function reset(){
	document.getElementById("idCard").value="";
	$("#memberInfo").empty();
}

//合并
function merge(){
	var mainId = "";
	var idStr = "";
	$(".check_member").each(function() {
		if($(this).is(':checked')){
			mainId = $(this).val();
		}else{
			idStr = idStr + ";" + $(this).val();
		}
	});
	//判断是否有数据被选中
	var flag = $(".check_member").is(':checked');
	if(!flag){
		layer.msg('合并时请选中一条数据！', {icon: 10});
		return;
	}
	
	//只有一条数据时无法合并
	if(idStr == ''){
		layer.msg('只有一条数据无法合并！', {icon: 10});
		return;
	}
	if(confirm("是否确认合并！")){
		$.ajax({
			url:'/memberUser/setMergerMember',
			data:{
				"mainMemberid":mainId,
				"otherMemberids":idStr,
			},
			type:"post",
			beforeSend: function(){
				layer.msg('正在处理合并，请稍等！', {icon: 5});
			},
			success: function(ret) {
				if(ret.code==000000){
					layer.msg('合并成功！', {icon: 12});
					search();
				}else{
					alert(ret.error);
				}
			}
		});
	}
	
}

/**
 * 跳转详情页
 * @param id
 */
function details(id){
	var a=list[id]
	 var ellPhone = a.cellPhone
	if(a.driverpercheck=='1'){
		window.location.href="/memberUser/detailPage?"+"cellPhone="+ellPhone+"&type="+2+"&menuId=2";
	}else if(a.userpercheck=='1'||a.companypercheck=='1'){
		window.location.href="/memberUser/detailPage?"+"cellPhone="+ellPhone+"&type="+1+"&menuId=2";		
	}else{
		layer.msg('该数据没有详情！', {icon: 10});
	}
}		

/**
 * 移除一条数据
 */
function remove(obj){
	$(obj).parents("tr").remove();
}

