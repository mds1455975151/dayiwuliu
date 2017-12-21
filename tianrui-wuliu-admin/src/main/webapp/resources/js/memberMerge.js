var list;

$(function(){
	search();	
});

//搜索
function search(){
	 var idCard = $("#idCard").val();
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
					
					$("#idCardList").empty();
					for (var a = 0; a < ret.length; a++) {
						var hml = "<option value='"+ret[a].identityCard+"'></option>";
						$("#idCardList").append(hml);
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
						//var href2 = $("<a></a>").attr("href", "javascript:permissionDel('"+ret[a].id+ "','设置主账号')").html("【设置主账号】");
						var span1 = $("<span></span>").append(href1);
						//var span2 = $("<span></span>").append(href2);
						td1.append(input1);
						td6.append(span1)/*.append(span2)*/
						
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
	search();
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
		alert("合并时请选中一条数据！")
		return;
	}
	
	//验证要合并的身份证号是否一致
	/*if(!validateIdcard()){
		return;
	};*/
	
	//只有一条数据时无法合并
	if(idStr == ''){
		alert("只有一条数据无法合并！")
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
			success: function(ret) {
				if(ret.code==000000){
					alert("合并成功");
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
	if(a.driverpercheck=='1'){
		window.location.href="/AdminMember/userDriver?"+a.cellPhone;		
	}else if(a.userpercheck=='1'||a.companypercheck=='1'){
		window.location.href="/AdminMember/userPerson?"+a.cellPhone;
	}
}		

//验证要合并的身份证号是否一致
function validateIdcard(){
	var a=list
	var obj = {};
	for (var i = 0; i < a.length; i++) {
	    var obj = a[i].identityCard;
	}	
	
}
