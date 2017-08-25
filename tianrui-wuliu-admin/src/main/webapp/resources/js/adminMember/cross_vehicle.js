
function loadSearch(){
	displayRec(0);
}
function displayData(d){
	var recPage = $("#recPage").val();
	if(recPage==""){
		displayRec(d);
	}else{
		displayRec(recPage-1);
		$("#recPage").val("");
	}
}
function displayRec(pageNo){
	var vehicleno = $('#vehicleno').val() || '';vehicleno = $.trim(vehicleno);
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+'/fileCross/crossVehicle',
		data:{"vehicleno":vehicleno,
			"pageNo":(pageNo+1),
			"pageSize":pageSize
		},
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				alert("加载失败");
			}else{
				var hml = "";
				$("#totalRecords").html(ret.data.count);
		    	document.getElementById("goPage").value = pageNo+1;
			    if(ret.data.count == 0) {
			    	$("#totalPages").html(1);  
			    	hml +='<td colspan="17">';
		    		hml +='<div class="ht_none">';
		    		hml +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
		    		hml +='<div>';
		    		hml +='<h3>唉呀！查询不到您要找的内容</h3>';
		    		hml +='<p>换个查询条件试试吧？</p>';
		    		hml +='</div>';
		    		hml +='</div>';
		    		hml +='</td>';
			    }else {
			    	$("#totalPages").html(parseInt((ret.data.count-1)/pageSize+1));  
			    	var d = ret.data.list;
					for (var a = 0; a < d.length; a++) {
						var c = a+1;
						
						var vehicleno = d[a].vehicleno;
						if(d[a].vehicleno == undefined){
							vehicleno = "";
						}
						var crossloge = d[a].crossloge;
						if(d[a].crossloge == undefined){
							crossloge = "";
						}
						var vehiclelogo = d[a].vehiclelogo;
						if(d[a].vehiclelogo == undefined){
							vehiclelogo = "";
						}
						if(d[a].vehiclelogo == 0){
							vehiclelogo = "启用";
						}
						if(d[a].vehiclelogo == 1){
							vehiclelogo = "停用";
						}
						var creator = d[a].creator;
						if(d[a].creator == undefined){
							creator = "";
						}
						var createtime = d[a].createtime;
						if(d[a].createtime == undefined){
							createtime = "";
						}
						hml += "<tr><td>"+c+"</td>"+
						    "<td>"+vehicleno+"</td>"+
							"<td>"+crossloge+"</td>"+
							"<td>"+vehiclelogo+"</td>"+
							"<td>"+creator+"</td>"+
							"<td>"+createtime+"</td></tr>";
					}
			    }  
			   
				document.getElementById("innerhml").innerHTML = hml;
				$("#pagination").pagination(ret.data.count, {  
				    callback: pageCallback,   
				    prev_text: '上一页',   
				    next_text: '下一页',   
				    items_per_page:pageSize,   
				    num_display_entries:4,   
				    current_page:pageNo,   
				    num_edge_entries:1, 
				    maxentries: ret.data.count, 
				    link_to:"javascript:void(0)"
				}); 
			}
		}
	});  
}
/**
 * 添加车辆信息
 */
function saveAdd(){
	var vehicleno = $("#vehicleno").val();
	var crossloge = $("#crossloge").val();
	var vehiclelogo = $("#vehiclelogo").val();
	$.ajax({
		url:CONTEXTPATH+'/fileCross/saveAdd',
		data:{"vehicleno":vehicleno,
			  "crossloge":crossloge,
			  "vehiclelogo":vehiclelogo
			},
		type:"post",
		success: function(ret) {
			
		}
	})		
}
/**
 * 清空搜索
 */
function clearSearch(){
	$("#vehicleno").val("");

}




