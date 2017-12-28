var pageSize=4;
countload(pageSize);
function countload(pageSize){
	$.ajax({
	    url:"/admin/banner/queryBanner",
	    dataType:'json',
	    type:'post',
	    data:{pageNo:"0",pageSize:pageSize},
	    success:function(result){
	        if(result.code == '000000'){
	        	var list = result.data||[];
	        	$(".more").off('click').on('click',function(){
	        		pageSize+=4;
	        		countload(pageSize);
	            })
	            appbannerC()
	        	function appbannerC(){
	        		$('.infoBox').empty();
	        		 for(var i=0;i<list.length;i++){
	            		 var str="",str2="";
	            		 if(list[i].pushStatus==0){
	            			 str='<p>发布状态：<span class="status red">待发布</span></p>'
	            		 }else{
	            			 str='<p>发布状态：<span class="status blue">已发布</span></p>'
	            		 }
	            		 if((list[i].picStatus)==1){
	                    	 str2='<div class="count1">禁用</div>'
	    				}else{
	    					str2='<div class="count">启用</div><div class="count2">删除</div>'
	    				}
	            	var html='<div class="border">'+
	                 '<div class="info" data-id='+(list[i].id)+'>'+
	                    '<img src='+(list[i].httpUrl)+'>'+
	                    '<p>'+(list[i].picName)+'</p>'+
	                    str2+
	                    '<p>链接：'+(list[i].httpUrl)+'</p>'+
	                    	str+
	                    ' </div>'+
	                    '</div>'
	                    $('.infoBox').append(html);
	            	 } 
	        		 
	        		 $(".count").each(function(){
	     	        	$(this).off('click').on('click',function(){
	     	        		var id=$(this).parent().attr("data-id");
	     	        		$.ajax({
	     	            		url:"/admin/banner/enableOrDisable",
	     	            		type:"POST",
	     	            		data:{id:id},
	     	            		success:function(ret){
	     	            			if(ret.code==000000){
	     	            				alert("操作成功");
	     	            				countload(pageSize);
	     	            			}
	     	            		}
	     	            	});
	     	        	})
	     	        })
	     	    	
	     	        
	     	       $(".count1").each(function(){
	     	    	   $(this).off('click').on('click',function(){
	     	    			var id=$(this).parent().attr("data-id");
	     	    			$.ajax({
	     	    	    		url:"/admin/banner/enableOrDisable",
	     	    	    		type:"POST",
	     	    	    		data:{id:id},
	     	    	    		success:function(ret){
	     	    	    			if(ret.code==000000){
	     	    	    				alert("操作成功");
	     	    	    				countload(pageSize);
	     	    	    			}
	     	    	    		}
	     	    	    	});
	     	    		})
	     	       })
	     	    	
	     	       $(".count2").each(function(){
	     	    	   $(this).off('click').on('click',function(){
	     	    			var id=$(this).parent().attr("data-id");
	     	    			alert(id);
	     	    			$.ajax({
	     	    	    		url:"/admin/banner/delBanner",
	     	    	    		type:"POST",
	     	    	    		data:{id:id},
	     	    	    		success:function(ret){
	     	    	    			if(ret.code==000000){
	     	    	    				alert("删除成功");
	     	    	    				countload(pageSize);
	     	    	    			}
	     	    	    		}
	     	    	    	});
	     	    		})
	     	       }) 
	        		 
	        	}
	           
	        }
	       
	    }
	})
}
    $(".add").off('click').on('click',function(){
    	window.location.href="/admin/banner/bannerAddPage?menuId=158";
    })
    $(".push").off('click').on('click',function(){
    	window.location.href="/admin/banner/bannerPushPage?menuId=158";
    })
     $(".button").off('click').on('click',function(){
    	window.location.href="/admin/banner/bannerPushPage?menuId=158";
    })

    
