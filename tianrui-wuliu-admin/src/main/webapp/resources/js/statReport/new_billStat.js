$(function(){
	index();
});
function index(pageNo){
	$.ajax({
		url:"/report/find",
		type:"post",
		data:{},
		success:function(ret){
			if(ret.code=="000000"){
				if(ret.code=="000000"){
					$("#totalRecords").html(ret.data.total);
			    	document.getElementById("goPage").value = pageNo+1;
				    if(ret.data.total == 0) {
				    	$("#totalPages").html(1); 
				    }else {
				    	$("#totalPages").html(parseInt((ret.data.total-1)/pageSize+1));  
				    }   
					list = ret.data.list;
					if(ret.data.list ){
						innerHTML(ret.data.list);
					}else{
						var hml="";
						hml +='<td colspan="12">';
				    	hml +='<div class="ht_none">';
				    	hml +='<img src="'+imagesRoot+'/s0.png" class="ht_nimg1">';
				    	hml +='<div>';
				    	hml +='<h3>唉呀！查询不到您要找的内容</h3>';
				    	hml +='<p>换个查询条件试试吧？</p	>';
				    	hml +='</div>';
				    	hml +='</div>';
				    	hml +='</td>';
				    	document.getElementById("innerHml").innerHTML=hml;
					}
					$("#pagination").pagination(ret.data.total, {   
					    callback: pageCallback,   
					    prev_text: '上一页',   
					    next_text: '下一页',   
					    items_per_page:pageSize,   
					    num_display_entries:4,   
					    current_page:pageNo,   
					    num_edge_entries:1, 
					    maxentries: ret.data.total, 
					    link_to:"javascript:void(0)"
					}); 
				}
			}
		}
	});
}

function innerHTML(data){
	var hml = "";
	for (var a = 0; a < data.length; a++) {
		hml +="<tr>" +
				"<td>"+a+1+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"<td>"+data[a]+"</td>" +
				"</tr>";
	}
	$("#innerHml").html(hml);
}