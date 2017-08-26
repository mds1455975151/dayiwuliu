
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
	var vehiclelogo = $('#vehiclelogos').val() || '';vehiclelogos = $.trim(vehiclelogos);
	var pageSize=$("#pageSize").val();
	$.ajax({
		url:CONTEXTPATH+'/fileCross/crossVehicle',
		data:{"vehicleno":vehicleno,
			"vehiclelogo":vehiclelogo,
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
						if(d[a].vehiclelogo == 1){
							vehiclelogo = "启用";
						}
						if(d[a].vehiclelogo == 0){
							vehiclelogo = "禁用";
						}
						var creator = d[a].creator;
						if(d[a].creator == undefined){
							creator = "";
						}
						var createtime = d[a].createtime;
						if(d[a].createtime == undefined){
							createtime = "";
						}
						if(createtime!=""){
							var createtime=new Date(createtime).toLocaleDateString().replace(/\//g, "-"); 
						}
						hml += "<tr><td>"+c+"</td>"+
						    "<td>"+vehicleno+"</td>"+
							"<td>"+crossloge+"</td><td>";
							if(d[a].vehiclelogo==1){
								hml += "<span><a  onclick=\"vehiclelogo('"+d[a].id+"','"+(pageNo)+"')\"  >【启用】</a></span>";
							}
							if(d[a].vehiclelogo==0){
								hml += "<span><a  onclick=\"vehiclelogo('"+d[a].id+"','"+(pageNo)+"')\"  >【禁用】</a></span>";
							}
							hml += "<span><a ></a></span>"+
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
	var vehicleno = $("#vehiclenos").val();
	vehicleno = $.trim(vehicleno);
	if(vehicleno==""){
		alert("车牌号不能为空！");
		return;
	}
	var crossloge = $("#crossloge").val();
	crossloge = $.trim(crossloge);
	var vehiclelogo = $("#vehiclelogo").val();
	vehiclelogo = $.trim(vehiclelogo);
	$.ajax({
		url:CONTEXTPATH+'/fileCross/saveAdd',
		data:{"vehicleno":vehicleno,
			  "crossloge":crossloge,
			  "vehiclelogo":vehiclelogo
			},
		type:"post",
		success: function(ret) {
			if(ret.code=="000000"){
				alert(ret.error);
				 window.location.reload();
			}else{
				alert(ret.error);
			}
		}
	})		
}
/**
 * 重置按钮
 */
function clearSearch(){
	$("#vehicleno").val("");
	$("#vehiclelogo").val("");
	displayRec(0);
}
/**
 * 启用/禁用
 */
function vehiclelogo(id,pageNo){
	$.ajax({
		url: CONTEXTPATH+'/fileCross/vehiclelogo',
		data:{"id":id,
			"pageNo":pageNo,},
		type:"post",
		success: function(ret){
			if(ret.code=="000000"){
				displayRec(parseInt(pageNo));
				alert(ret.error);
			}else{
				alert(ret.error);
			}
		}
	})
}
/**
 * 搜索车辆信息
 */
function select(){
	var vehicleno = $("#vehiclenos").val();
	vehicleno = $.trim(vehicleno);
	if(vehicleno==""){
		alert("车牌号不能为空！");
		return;
	}
	$.ajax({
		url: CONTEXTPATH+'/fileCross/selectVehicle',
		data:{"vehicleno":vehicleno},
		type:"post",
		success: function(ret){
				if(ret.code=="000000"){
					$(".model_width").css('display','block'); 
					var cellphone =ret.data.cellphone == undefined ? "":ret.data.cellphone;
					var vehiOwnerTel =ret.data.vehiOwnerTel == undefined ? "":ret.data.vehiOwnerTel;
					var vehiOwnerName =ret.data.vehiOwnerName == undefined ? "":ret.data.vehiOwnerName;
					var vehicletypename =ret.data.vehicletypename == undefined ? "":ret.data.vehicletypename;
					var status =ret.data.status == undefined ? "":ret.data.status;
					if(status!=""&&status=="-1"){
						status="认证失败";
					}
					if(status!=""&&status=="0"){
						status="未认证";
					}
					if(status!=""&&status=="1"){
						status="认证成功";
					}
					if(status!=""&&status=="2"){
						status="认证中";
					}
					var desc1 =ret.data.desc1 == undefined ? "":ret.data.desc1;
					if(desc1!=""&&desc1=="-1"){
						desc1="认证失败";
					}
					if(desc1!=""&&desc1=="0"){
						desc1="未认证";
					}
					if(desc1!=""&&desc1=="1"){
						desc1="认证成功";
					}
					if(desc1!=""&&desc1=="2"){
						desc1="认证中";
					}
					var desc2 =ret.data.desc2 == undefined ? "":ret.data.desc2;
					if(desc2!=""&&desc2==1){
						desc2="临时认证";
					}if(desc2!=""&&desc2==2){
						desc2="完全认证";
					}
					$("#cellphone").val(cellphone);//添加账号
					$("#vehiOwnerTel").val(vehiOwnerTel);//所有人电话
					$("#vehiOwnerName").val(vehiOwnerName);//所有人名称
					$("#vehicletypename").val(vehicletypename);//车辆名称
					$("#status").val(status);//车辆状态
					$("#desc1").val(ret.data.desc1);//开票认证状态
					$("#desc2").val(ret.data.desc2);//临时车辆or认证车辆
				}else{
					alert(ret.error);
				}
			}
		})
}
/**
 * 清空添加弹框车辆信息
 * */

function empty(){
	$("[name='selects']").css('display','none'); 
	$("#crossloge").val("")
	$("#vehiclelogo").val("")
	$("#vehiclenos").val("")
	$("#cellphone").val("")
	$("#vehiOwnerTel").val("")
	$("#vehiOwnerName").val("")
	$("#vehicletypename").val("")
	$("#status").val("")
	$("#desc1").val("")
	$("#desc2").val("")
}
