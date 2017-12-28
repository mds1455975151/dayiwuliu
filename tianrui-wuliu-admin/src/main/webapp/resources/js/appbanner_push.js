var  pageSize=4;
countload();
function countload(){
	var id,idStr="";
	$.ajax({
	    url:"/admin/banner/queryPushBanner",
	    dataType:'json',
	    type:'post',
	    data:{pageNo:"0",pageSize:pageSize},
	    success:function(result){
	        if(result.code == '000000'){
	        	console.log(result)
	        	var list = result.data||[];
	        	var pagsize=4,count=1;
	        	$(".more").off('click').on('click',function(){
	        		pageSize+=4;
	        		countload(pageSize);
	            })
	            appbannerC()
	        	function appbannerC(){
	        		 $('.infoBox').empty();
	        		 for(var i=0;i<list.length;i++){ 
		            	 var html='<div class="border">'+
		                 '<div class="info" data-id='+(list[i].id)+'>'+
		                 '<div class="piaochecked on_check"><input name="need_inv" type="checkbox" style="height:20px;width:20px;" class="radioclass input" value="1"></div>'+
		                    '<img src='+(list[i].httpUrl)+'>'+
		                    '<p>'+(list[i].picName)+'</p>'+
		                    '<p>链接：'+(list[i].httpUrl)+'</p>'+
		                    ' </div>'+
		                    '</div>'
		                    $('.infoBox').append(html);
	            	 } 
	        		 $(".piaochecked").off('click').on('click',function(){
	        			 id=$(this).parent().attr("data-id");
	        			 idStr+=id+";";
	        			 $(this).toggleClass( "on_check" );
	        		 })	
	        	}
	        }
	    }
	})
	$(".button").off('click').on('click',function(){
		$.ajax({
		    url:"/admin/banner/pushBanner",
		    dataType:'json',
		    type:'post',
		    data:{pushBannerIds:idStr},
		    success:function(result){
		        if(result.code == '000000'){
		        	alert("发布成功")
		        }
		        }
		})
	})
}
    $(".add").off('click').on('click',function(){
    	window.location.href="/admin/banner/bannerAddPage?menuId=158";
    })
    $(".push").off('click').on('click',function(){
    	window.location.href="/admin/banner/bannerPushPage?menuId=158";
    })
   
    

    
