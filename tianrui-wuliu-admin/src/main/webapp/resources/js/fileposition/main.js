$(function(){
	// 后台访问url
	var URL={
		//列表查询url
		"queryUrl":"/file/fileposition/page",	
		//修改url
		"updateUrl":"/file/fileposition/update",	
		//保存url
		"saveUrl":"/file/fileposition/save",	
		//可用 禁用url
		"enableUrl":"/file/fileposition/delete",
		//删除url
		"deleteUrl":"/file/fileposition/deletePosition",
		//详情查询
		"detailUrl":"/file/fileposition/detail"
	}
	displayData(0);
	
	//搜索按钮绑定事件
	$(".searchbtn").click(function(){
		displayData(0);
	})
	
	//跳转事件
	$("#_toPageSize").click(function(){
		if($("#goPage").val() >= 1 && $("#goPage").val() <= parseInt($("#totalPages").text())){
			displayData($('#goPage').val()-1);return false;
		}else {
			alert("请输入正确的页码 范围为：1-  "+$("#totalPages").text());
			return false;
		}
	});
	
	
	//页面渲染
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
				    if( result.data && result.data.total > 0 ){
						initTable(result.data.list || [],pageNo);
					}else{
						$("#org_tbody").empty();
				    	var html;
				    	if($(".contuser_search .routeName").val() ){
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
				    		html +='<h3>组织下暂无可用地点档案！？</h3>';
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
				dataArr.push( "<td>"+index+"</td>");
				//<th>路径名称</th>
				dataArr.push( "<td>"+(item.name ||"")+"</td>");
				//<th>行政区域</th>
				dataArr.push( "<td>"+ (item.op||"") +(item.oc||"")+(item.oa||"")+"</td>");
				//<th>具体地点</th>
				dataArr.push( "<td>"+(item.addr ||"")+"</td>");
				//<th>地图坐标</th>
				dataArr.push(  "<td>["+(item.lat ||"")+","+(item.lng ||"")+"]</td>");
				//<th>创建者</th>
 				dataArr.push(  "<td>"+ (item.creator ||"") +"</td>");
 				//<th>状态</th>
 				if( item.status =="0" ){
 					dataArr.push(  "<td>已停用 </td>");
 				}else{
 					dataArr.push(  "<td>正常</td>");
 				}
 				//<th>备注</th>
 				dataArr.push(  "<td>"+ (item.remark ||"") +"</td>");
				//<th>操作</th>
				dataArr.push("<td>");
				/**
				if( item.status =="0" ){
					dataArr.push( $("<a></a>").attr("href","javascript:void(0)").attr("class","delBtn").attr("status",1).attr("dataId",item.id).html("【启用】")[0].outerHTML);
				}else{
					dataArr.push( $("<a></a>").attr("href","javascript:void(0)").attr("class","delBtn").attr("status",0).attr("dataId",item.id).html("【禁用】")[0].outerHTML);
				}
				*/
				if(item.count == 0){
					dataArr.push( $("<a></a>").attr("href","javascript:void(0)").attr("class","delPosotion").attr("status",0).attr("dataId",item.id).attr("pageNo",pageNo).html("【删除】")[0].outerHTML);
					dataArr.push("  ")
					dataArr.push( $("<a></a>").attr("href","javascript:void(0)").attr("class","editbtn").attr("dataId",item.id).attr("pageNo",pageNo).html("【修改】")[0].outerHTML)
				}
				dataArr.push("</td>");
				dataArr.push("</tr>");
			});
			$("#org_tbody").html(dataArr.join(" "));
		}
	}
	
	
	//新增数据初始化
	$(".addDiv").click(function(){
		
		$("#addModal").modal({"backdrop":"static",show:true});
	});
	
	//行政区域
	$("#addModal").on("click",".pcaInput",function(e){
		//打开地址选择框
		var addpca = new citySelect();   //相关城市
		addpca.initCitySelect("addpca", addpca, addpcaCallBack,e);			
	});
	//选择框回调函数
	var addpcaCallBack =function(data){
		if( data ){
			$("#addModal .op").val(data.provinceText ||"");
			$("#addModal .opc").val(data.provinceValue ||"");
			$("#addModal .oc").val(data.cityText ||"");
			$("#addModal .occ").val(data.cityValue||"");
			$("#addModal .oa").val(data.districtText ||"");
			$("#addModal .oac").val(data.districtValue ||"");
			$("#addModal #addpca").val($("#addModal .op").val()+$("#addModal .oc").val()+$("#addModal .oa").val());
		}
	}
	//具体信息
	$("#addModal").on("click",".addrInput",function(e){
		//打开地址选择框
		var mapSetPoint = new MapSetPoint();
		var option = {
	            isInput:true,
	            provinceId: "opcAdd",    //省份ID
	            cityId: "occAdd",       //城市ID
	            districtId: "oacAdd",   //地区ID
	            thisObject: mapSetPoint,            //构造的对象
	            callBack: addmapCallBack,          //点击“使用此地址”回调函数
	        }
	        mapSetPoint.openMap(option);
	});
	
	//地图回调函数  
	var addmapCallBack =function(data){
		//address:"吴敬梓路"
		//city:"滁州市"
		//district:"全椒县"
		//lat:32.115495
		//lon:118.287136
		//province:"安徽省"
//		alert(data.address);
		$("#addModal .op").val(data.province ||"");
		$("#addModal .oc").val(data.city ||"");
		$("#addModal .oa").val(data.district ||"");
		$("#addModal .opc").val("");
		$("#addModal .occ").val("");
		$("#addModal .oac").val("");
	    $.each(province_data, function (i, n) {
	        if (data.province == n.Name) {
	        	$("#addModal .opc").val(n.Bin);
	            return false;
	        }
	    });
	    $.each(city_data, function (i, n) {
	        if (data.city == n.Name) {
	        	$("#addModal .occ").val(n.Bin);
	            return false;
	        }
	    });
	    $.each(district_data, function (i, n) {
	        if (data.district == n.Name) {
	        	$("#addModal .oac").val(n.Bin);
	            return false;
	        }
	    });
	    //行政区域重新赋值
		$("#addModal #addpca").val($("#addModal .op").val()+$("#addModal .oc").val()+$("#addModal .oa").val());
		//详细地址赋值
		$("#addModal .addrInput").val(data.address);
		//经纬度赋值
		$("#addModal .lat").val(+((data.lat ||"0")*1000000).toFixed(0));
		$("#addModal .lon").val(+((data.lon ||"0")*1000000).toFixed(0));
	}

	var validFrom=$("#addForm").Validform({
		tiptype:3,
		showAllError:true
	})
	//新增提交按钮
	$("#addModal").on("click",".addSubBtn",function(e){
		
		var submitUrl =URL.saveUrl;
		if(  $("#addModal .id").val()){
			submitUrl =URL.updateUrl;
		}
		if( validFrom.check() ){
			
			if( !$("#addModal #addpca").val()){
				alert("行政区域不能为空");
				return ;
			}
			$(".addSubBtn").attr("disabled",true);
			$.ajax({
				url:submitUrl,
				data:$("#addModal .formV").serialize(),
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code=="000000" ){
						alert("操作成功");
						$(".searchbtn").trigger("click");
						$(".addSubBtn").attr("disabled",false);
					}else{
						alert(rs.error);
						$(".addSubBtn").attr("disabled",false);
					}
					$("#addModal").modal('hide');
				}	
			});
		}
		
		
	})
	//监听添加模态框关闭事件
	$('#addModal').on('hidden.bs.modal', function (e) {
		$("#addModal .titleSpan").text("新增");
		$("#addModal .id").val("");
		
		$("#addModal .op").val("");
		$("#addModal .oc").val("");
		$("#addModal .oa").val("");
		$("#addModal .opc").val("");
		$("#addModal .occ").val("");
		$("#addModal .oac").val("");
		
		$("#addModal .nameInput").val("");
		$("#addModal #addpca").val("");
		$("#addModal .addrInput").val("");
		$("#addModal .lat").val("");
		$("#addModal .lon").val("");
		$("#addModal .remark").val("");
		//验证样式去除
		$(".Validform_checktip").html("").removeClass("Validform_wrong").removeClass("Validform_right");
	})
	
	
	//修改按钮绑定事件
	$(".table").on("click",".editbtn",function(e){
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
					
					$("#addModal .op").val(data.op);
					$("#addModal .oc").val(data.oc);
					$("#addModal .oa").val(data.oa);
					$("#addModal .opc").val(data.opc);
					$("#addModal .occ").val(data.occ);
					$("#addModal .oac").val(data.oac);
					
					$("#addModal .nameInput").val(data.name);
					$("#addModal #addpca").val(data.op+data.oc+data.oa);
					$("#addModal .addrInput").val(data.addr);
					$("#addModal .lat").val(data.lat);
					$("#addModal .lon").val(data.lng);
					
					
					$("#addModal .titleSpan").text("修改");
					$("#addModal").modal("show");
				}
			}
		});
	})
	
	//启用禁用按钮绑定事件
	$(".table").on("click",".delBtn",function(e){
		var id=$(this).attr("dataId");
		var status=$(this).attr("status");
		$.ajax({
			url:URL.enableUrl,
			data:{"id":id,"status":status},
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					alert("操作成功");
					$(".searchbtn").trigger("click");
				}else{
					alert(rs.error);
				}
			}
		});
	})
	//删除按钮绑定事件
	$(".table").on("click",".delPosotion",function(e){
		var id=$(this).attr("dataId");
		var status=$(this).attr("status");
		var pageNo = $(this).attr("pageNo");
		if(window.confirm('确定要删除该位置？')){
			$.ajax({
				url:URL.deleteUrl,
				data:{"id":id,"status":status},
				type : "post",
				dataType:"json",
				success:function(rs){
					if( rs && rs.code =="000000" ){
						//$(".searchbtn").trigger("click");
						displayData(parseInt(pageNo));
					}else{
						alert(rs.error);
					}
				}
			});
		}
	})
	
});

window.alert =function(msg,title,callback){
	$("#modal_common_content").html(msg);
	$("#modal_del_title").html(title||"确认");
	$("#commonModal").modal();
}