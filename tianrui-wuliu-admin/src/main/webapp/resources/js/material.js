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
	queryWaitMate();
	querySelecedMaterial();
})
 $('.queding').click(function(){
	 var optionssel = document.getElementById('select2');
	 var materialIds = "";
		for (var i=0; i<optionssel.options.length; i++) {
			materialIds += optionssel.options[i].value+";";
		}
		$.ajax({
			url:'/material/selected',
			data:{
				"materialIds":$.trim(materialIds)
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
function queryWaitMate(){
	var cargoName = $("#cargoName").val();
	$.ajax({
		url:CONTEXTPATH+'/material/queryWaitMate',
		data:{
			"cargoName":$.trim(cargoName)
		},
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				alert("加载失败");
			}else{
				$("#select1").empty();
				var data = ret.data;
				for (var i=0; i<data.length; i++) {
					$("#select1").append('<option value="'+data[i].id+'">'+ data[i].materieName+'</option>'); 
				}
			}
		}
	});  
}

function querySelecedMaterial(){
	var cargoName = $("#cargoName1").val();
	$.ajax({
		url:CONTEXTPATH+'/material/queryee',
		data:{
			"cargoName":$.trim(cargoName)
		},
		type:"post",
		success: function(ret) {
			if(ret.code!="000000"){
				alert("加载失败");
			}else{
				$("#select2").empty();
				var data = ret.data;
				for (var i=0; i<data.length; i++) {
					$("#select2").append('<option value="'+data[i].id+'">'+ data[i].materieName+'</option>'); 
				}
			}
		}
	});  
}