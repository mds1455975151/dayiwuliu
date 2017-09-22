$(function(){
	
	// 后台访问url
	var URL={
		//列表查询url
		"queryUrl":"/file/fileroute/page",	
		//修改url
		"updateUrl":"/file/fileroute/update",	
		//保存url
		"saveUrl":"/file/fileroute/save",	
		//可用 禁用url
		"enableUrl":"/file/fileroute/disable",	
		//详情url
		"detailUrl":"/file/fileroute/detail",
		//收货人详情
		"signdetail":"/file/fileOrgSigner/detail"
	}
	//页面数据初始化
	displayData(0);
	//跳转事件
	$("#_toPageSize").click(function(){
		if($("#goPage").val() >= 1 && $("#goPage").val() <= parseInt($("#totalPages").text())){
			displayData($('#goPage').val()-1);return false;
		}else {
			alert("请输入正确的页码 范围为：1-  "+$("#totalPages").text());
			return false;
		}
	});
	//收获人选择事件
	$("#receiveid_req").on("change",function(){
		$("#receivepersionphone_req").val("");
		$("#receivepersion_req").val("");
		$.ajax({
			url : URL.signdetail,
			data : {"id":$("#receiveid_req").val()},
			type : "POST",
			success : function(ret){
				$("#receivepersionphone_req").val(ret.data.cellphone);
				$("#receivepersion_req").val(ret.data.membername);
			}
				
		});
	});
	//搜索按钮绑定事件
	$(".searchbtn").click(function(){
		displayData(0);
	});
	function displayData(pageNo){
		
		var pageSize=$("#pageSize").val();
		$.ajax({
			url : URL.queryUrl,// 跳转到 action
			data : {
				pageNo :(pageNo+1),
				pageSize:pageSize,
				"name":$(".contuser_search .routeName").val(),	
				"status":$(".contuser_search .routeStatus").val()
			},
			type : "post",
			dataType:"json",
			success : function(result) {
				if( result && result.code=="000000"  ){
					rederPage(pageNo,result);
				    if( result.data && result.data.total > 0){
						initTable(result.data.list || [],pageNo);
					}else{
						$("#org_tbody").empty();
						var html;
				    	if($(".contuser_search .routeName").val() || $(".contuser_search .routeStatus").val() !=""){
				    		html +='<td colspan="12">';
				    		html +='<div class="ht_none">';
				    		html +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
				    		html +='<div>';
				    		html +='<h3>唉呀！查询不到您要找的内容</h3>';
				    		html +='<p>换个查询条件试试吧？</p>';
				    		html +='</div>';
				    		html +='</div>';
				    		html +='</td>';
				    	}else{
				    		html +=' <td colspan="12">';
				    		html +='<div class="ht_none">';
				    		html +='<img src="'+imagesRoot+'/none1.png" class="ht_nimg2">';
				    		html +='<div>';
				    		html +='<h3>组织下暂无可用路线档案！？</h3>';
				    		html +='<p>赶快动动手指加上吧！</p>';
				    		html +='</div>';
				    		html +='</div>';
				    		html +='</td>';
				    	}
				    	$("#org_tbody").html(html);
					}
				}else{
					$("#modal_common_content").html(result.error);
					$("#commonModal").modal();
				}
			}
		});
	}
	//重置按钮绑定事件
	$(".resetbtn").on("click",function(){
		$(".contuser_search .routeName").val("");
		$(".contuser_search .routeStatus").val("");
	});
	//分页数据渲染
	function rederPage(pageNo,result){
		var pageSize=10;
		 $("#totalRecords").html(result.data.total||0);
	    	$("#goPage").val(pageNo);
		    if(result.data.total == 0) {
		    	$("#totalPages").html(1);  
		    }else {
		    	$("#totalPages").html(parseInt((result.data.total-1)/pageSize+1));  
		    }   
		    $("#pagination").pagination(result.data.total, {   
		    	callback: function(pageNo){
		    		displayData(pageNo);
		    	},   
		    	prev_text: '上一页',   
		    	next_text: '下一页',   
		    	items_per_page:pageSize,   
		    	num_display_entries:4,   
		    	current_page:pageNo,   
		    	num_edge_entries:1, 
		    	maxentries: result.data.total, 
		    	link_to:"javascript:void(0)"
		    }); 
	}
	//table数据填充
	var initTable=function(data,pageNo){
		//清空
		$("#org_tbody").empty();
		if(data && data.length>0){
			var dataArr=[];
			$(data).each(function(i,item){
				var index =i+1;
				dataArr.push( "<tr id='"+item.id+"'>");
				//<th>序号</th>
				if(item.status == "1"){
					dataArr.push( '<td><input type="checkbox"  value="'+item.id+'" name="user"></td>');
				}else{
					dataArr.push( "<td></td>");
				}
				//<th>路径名称</th>
				dataArr.push( "<td>"+item.routename+"</td>");
				//<th>始发地</th>
				dataArr.push( "<td onclick= \"rountename('"+item.opositionid+"')\">"+"<font  color='blue'>"+ item.oaddr+"</font>"+"</td>");
				//<th>目的地</th>
				dataArr.push( "<td onclick= \"rountename('"+item.dpositionid+"')\">"+"<font  color='blue'>"+ item.daddr+"</font>"+"</td>");
				//<th>距离</th>	
				dataArr.push("<td>"+ item.distanceStr+"</td>" );
				//<th>收货人</th>	
				dataArr.push("<td>"+ item.sendpersion+"</td>" );
				//<th>发货人</th>	
				dataArr.push("<td>"+ item.receivepersion+"</td>" );
				//<th>状态</th>
				if( item.status =="1" ){
					dataArr.push("<td>正常</td>" );
				}else{
					dataArr.push("<td>已停用</td>" );
				}
				//<th>操作人</th>
				dataArr.push( "<td>"+ item.modifier+"</td>" );
			
				//<th>操作</th>
				dataArr.push("<td>");
				if( item.status =="1" ){
					dataArr.push( $("<a></a>").attr("href","javascript:void(0)").attr("class","delbtn").attr("status",0).attr("dataId",item.id).attr("pageNo",pageNo).html("【禁用】")[0].outerHTML);
				}else{
					dataArr.push( $("<a></a>").attr("href","javascript:void(0)").attr("class","delbtn").attr("status",1).attr("dataId",item.id).attr("pageNo",pageNo).html("【启用】")[0].outerHTML);
				}
				dataArr.push("  ")
				if(item.count == 0){
					dataArr.push( $("<a></a>").attr("href","javascript:void(0)").attr("class","editbtn").attr("dataId",item.id).attr("pageNo",pageNo).html("【修改】")[0].outerHTML);
					dataArr.push( $("<a></a>").attr("href","javascript:void(0)").attr("class","deleRoutebtn").attr("dataId",item.id).attr("pageNo",pageNo).html("【删除】")[0].outerHTML)
				}
				dataArr.push("</td>");
				dataArr.push("</tr>");
			});
			$("#org_tbody").html(dataArr.join(" "));
		}
	}
	
	//绑定下拉框change时间
	var a= '';
	var b ='';
	$("#addModal").on("change",".opositionid",function(){
		$("#addModal .oaddr").val($(this).find("option:checked").text());
		a=$(this).find("option:checked").text();
		$("#addModal .routename").val(a+"至"+b);
	})
	$("#addModal").on("change",".dpositionid",function(){
		if(a!=''){
		$("#addModal .daddr").val($(this).find("option:checked").text());
		b=$(this).find("option:checked").text();
			$("#addModal .routename").val(a+"至"+b);
		}else{
			alert("请选择发货地点！");
		}
	})
	
	
	var validFrom=$("#addForm").Validform({
		tiptype:3,
		showAllError:true
	})
	
	//新增模态框 提交
	$("#addModal").on("click",".submutBtn",function(){
		var submitUrl =URL.saveUrl;
		if(  $("#addModal .id").val()){
			submitUrl =URL.updateUrl;
		}
		if( $(".dpositionid").val() ==$(".opositionid").val() ){
			alert("发货地与收获地不能一致!");
			return;
		}
		var pageNo = $("#pageNo").val();
		$(".submutBtn").attr("disabled",true);
		//表单验证通过
		if(validFrom.check()){
			$.ajax({
				url:submitUrl,
				data:$("#addModal .formV").serialize(),
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code=="000000" ){
						alert("操作成功");
						displayData(parseInt(pageNo))
						$(".submutBtn").attr("disabled",false);
					}else{
						alert(rs.error);
						$(".submutBtn").attr("disabled",false);
					}
					$("#addModal").modal('hide');
				}	
			});
		}
		
	});
	
	//监听添加模态框关闭事件
	$('#addModal').on('hidden.bs.modal', function (e) {
		$("#addModal .titleSpan").text("新增");
		$("#addModal .id").val("");
		
		$("#addModal .opositionid").val("");
		$("#addModal .addr").val("");
		$("#addModal .sendpersion").val("");
		$("#addModal .sendpersionphone").val("");

		$("#addModal .dpositionid").val("");
		$("#addModal .daddr").val("");
		$("#addModal .receivepersion").val("");
		$("#addModal .receivepersionphone").val("");
		
		$("#addModal .routename").val("");
		$("#addModal .distanceStr").val("");
		
		//验证样式去除
		$(".Validform_checktip").html("").removeClass("Validform_wrong").removeClass("Validform_right");
		
	})
	
	//启用禁用按钮
	$(".table").on("click",".delbtn",function(){
		var id=$(this).attr("dataId");
		var status=$(this).attr("status");
		var pageNo = $(this).attr("pageNo");
		if (status == "0") {
			if(window.confirm('停用后关联的下游单据将无法使用！你确定要停用选择的路线？')){
				$.ajax({
					url:URL.enableUrl,
					data:{"id":id,"status":status},
					type : "post",
					dataType:"json",
					success:function(rs){
						if( rs && rs.code =="000000" ){
						//	$(".searchbtn").trigger("click");
							displayData(parseInt(pageNo));
						}else{
							alert(rs.error);
						}
					}
				});
			}
		}else {
			$.ajax({
				url:URL.enableUrl,
				data:{"id":id,"status":status},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						displayData(parseInt(pageNo));
					}else{
						alert(rs.error);
					}
				}
			});
		}
		
	});
	
	//删除按钮
	$(".table").on("click",".deleRoutebtn",function(){
		var id=$(this).attr("dataId");
		var pageNo = $(this).attr("pageNo");
			if(window.confirm('你确定要删除该路线？')){
				$.ajax({
					url:"/file/fileroute/deleteRoute",
					data:{"id":id},
					type : "post",
					dataType:"json",
					success:function(rs){
						if( rs && rs.code =="000000" ){
							displayData(parseInt(pageNo));
						}else{
							alert(rs.error);
						}
					}
				});
			}
	});
	
	//修改按钮
	$(".table").on("click",".editbtn",function(){
		var id=$(this).attr("dataId");
		var pageNo=$(this).attr("pageNo");
		$.ajax({
			url:URL.detailUrl,
			data:{"id":id},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					//修改数据初始化
					var data =rs.data;
					$("#addModal .id").val(data.id);
					
					$("#addModal .opositionid").val(data.opositionid);
					$("#addModal .oaddr").val(data.oaddr);
					$("#addModal .sendpersion").val(data.sendpersion);
					$("#addModal .sendpersionphone").val(data.sendpersionphone);

					$("#addModal .dpositionid").val(data.dpositionid);
					$("#addModal .daddr").val(data.daddr);
					$("#addModal .receivepersion").val(data.receivepersion);
					$("#addModal .receivepersionphone").val(data.receivepersionphone);
					
					$("#addModal .routename").val(data.routename);
					$("#addModal .distanceStr").val(data.distance);
					
					$("#receiveid_req").val(data.receiveid);
					$("#pageNo").val(pageNo);
					$("#addModal .titleSpan").text("修改");
					$("#addModal").modal("show");
				}
			}
		});
		
	});
});

window.alert =function(msg,title,callback){
	$("#modal_common_content").html(msg);
	$("#modal_del_title").html(title||"确认");
	$("#commonModal").modal();
}
/**
 *『批量停用』功能点击事件
 * 
 * @param null
 * @return null
 * @author tank
 * @time 2016.07.05
 */
$("#batchDisable").click(function() {
	// 获取表体所有checkbox被选中数据的用户ID
	var checkedRoleId = "";
	$("#org_tbody input[type=checkbox]:checked").each(function() {
		if (checkedRoleId != "") {
			checkedRoleId = checkedRoleId + "," + $(this).val();
		} else {
			checkedRoleId = checkedRoleId + $(this).val();
		}
	});
	if(checkedRoleId == ""){
		alert("请选择要停用的路线档案！");
		return;
	}
	if(window.confirm('停用后关联的下游单据将无法使用！你确定要停用选择的路线？')){
		$.ajax({
			url : "/file/fileroute/updateStatus",// 跳转到 action
			data : {
				id: checkedRoleId
			},
			type : "post",
			success : function(result) {
				$("#modal_common_content").html("批量停用完成！");
				$("#commonModal").modal();
				$(".searchbtn").trigger("click");
			}
		});
    }else{
        return false;
    }
});

function rountename (id){
	alert(id);
	$.ajax({
		url:"/file/fileroute/selectRountename",
		data:{"id":id},
		type : "post",
		dataType:"json",
		success:function(rs){
			if(rs.code =="000000" ){
				var op = rs.data.op == undefined ? "":rs.data.op;
				var oc = rs.data.oc == undefined ? "":rs.data.oc;
				var oa = rs.data.oa == undefined ? "":rs.data.oa;
				var addr = rs.data.addr == undefined ? "":rs.data.addr;
				alert(op+oc+oa+addr);
			}else{
				alert(rs.error);
			}
		}
	});
}