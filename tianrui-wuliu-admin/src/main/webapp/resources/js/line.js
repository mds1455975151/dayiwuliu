 $(function(){
        //移到右边
        $('#add').click(function(){
            //先判断是否有选中
            if(!$("#select1 option").is(":selected")){
                alert("请选择需要移动的选项")
            }
            //获取选中的选项，删除并追加给对方
            else{
                $('#select1 option:selected').appendTo('#select2');
            }
        });

        //移到左边
        $('#remove').click(function(){
            //先判断是否有选中
            if(!$("#select2 option").is(":selected")){
                alert("请选择需要移动的选项")
            }
            else{
                $('#select2 option:selected').appendTo('#select1');
            }
        });


        //双击选项
        $('#select1').dblclick(function(){ //绑定双击事件
            //获取全部的选项,删除并追加给对方
            $("option:selected",this).appendTo('#select2'); //追加给对方
        });

        //双击选项
        $('#select2').dblclick(function(){
            $("option:selected",this).appendTo('#select1');
        });

    });
$(function(){
	querySelectName();
	queryWaitRoute();
	querySr();
})
function changeSelectName(){
	queryWaitRoute();
	querySr();
}
 $('.queding').click(function(){
	 var optionssel = document.getElementById('select2');
	 var routeIds = "";
		for (var i=0; i<optionssel.options.length; i++) {
			routeIds += optionssel.options[i].value+";";
		}
		$.ajax({
			url:'/material/setRoute',
			data:{
				"routeIds":$.trim(routeIds),
				"materieId":$("#nameSelect").val()
			},
			type:"post",
			success: function(ret) {
				if(ret.code!="000000"){
					alert("保存失败");
				}else{
					alert("设置成功");
				}
			}
		});  
  });
function queryWaitRoute(){
	$.ajax({
		url:CONTEXTPATH+'/material/queryWaitRoute',
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				alert("加载失败");
			}else{
				$("#select1").empty();
				var data = ret.data;
				alert(data.length);
				for (var i=0; i<data.length; i++) {
					$("#select1").append('<option value="'+data[i].id+'">'+ data[i].routeName+'</option>'); 
				}
			}
		}
	});  
}

function querySr(){
	var materieId = $("#nameSelect").val();
	$.ajax({
		url:CONTEXTPATH+'/material/querySr',
		data:{
			"materieId":$.trim(materieId)
		},
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				alert("加载失败");
			}else{
				$("#select2").empty();
				var data = ret.data;
				for (var i=0; i<data.length; i++) {
					$("#select2").append('<option value="'+data[i].id+'">'+ data[i].routeName+'</option>'); 
				}
			}
		}
	});  
}
function querySelectName(){
	$.ajax({
		url:CONTEXTPATH+'/material/queryee',
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				alert("加载失败");
			}else{
				$("#nameSelect").empty();
				var data = ret.data;
				for (var i=0; i<data.length; i++) {
					if(i == 0){
						$("#nameSelect").append('<option selected="selected" value="'+data[i].id+'">'+ data[i].materieName+'</option>'); 
					}else {
						$("#nameSelect").append('<option value="'+data[i].id+'">'+ data[i].materieName+'</option>'); 
					}
					
				}
			}
		}
	});  
}
