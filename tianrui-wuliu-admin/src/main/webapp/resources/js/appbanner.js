$.ajax({
    url:"/admin/banner/queryBanner",
    dataType:'json',
    type:'post',
    success:function(result){
        if(result.code == '000000'){
        	var list = result.data||[];
        	var pagsize=4,count=0;
        	
        	$(".more").off('click').on('click',function(){
        		if(list.length>pagsize){
        			count++;
            		pagsize=pagsize*count;
            		 appbannerC(pagsize)
        		}else{
        			alert("已经没有数据啦")
        		}
        		
            })
            appbannerC(pagsize)
        	function appbannerC(pagsize){
        		 for(var i=0;i<pagsize;i++){
            		 var str="",str2="";
            		 if(list[i].pushStatus==0){
            			 str='<p>发布状态：<span class="status red">待发布</span></p>'
            		 }else{
            			 str='<p>发布状态：<span class="status blue">已发布</span></p>'
            		 }
            		 if((list[i].picStatus)==1){
                    	 str2='<div class="count">启用</div><div class="count2">删除</div>'
    				}else{
    					str2='<div class="count1">禁用</div>'
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
        	}
           
        }
    }
})
    $(".add").off('click').on('click',function(){
    	window.location.href="/admin/banner/bannerAddPage?menuId=158";
    })
    
 window.onload=function(){
	
}
    
