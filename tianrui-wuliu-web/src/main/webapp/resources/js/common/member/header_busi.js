/**
 * ==================================================================
 * 公共头部js v1.0
 * ==================================================================
 * @author kowuka 
 * @time 2016.04.25
 */

// 初始化处理
$(function() { 
	// 获取用户未读信息数
	$.ajax({
		url : CONTEXTPATH + '/trwuliu/Member/message/findUnread',// 跳转到 action
		data : {},
		type : "post",
		success : function(result) {
			if(result && result.code =="000000"){
				if( result.data > 9 ){
					$("#countMessage").html('<a href="/trwuliu/Member/message/message"><i class="iconfont icon-xiaoxi"></i><span>'+result.data+'条未读消息</span> </a>');
					$("#messageCount").html('<span class="m_note"><h4>9+</span></h4>');
				}else if(result.data > 0){
					$("#countMessage").html('<a href="/trwuliu/Member/message/message"><i class="iconfont icon-xiaoxi"></i><span>'+result.data+'条未读消息</span> </a>');
					$("#messageCount").html('<span class="m_note"><h4>'+result.data+'</span></h4>');
				}
				
			}
		}
	});
	// 导航信息
	var name = null;
	if (loginName != null && "null" != loginName) {
		name = loginName;
	} else {
		name = cellPhone;
	}
	$("#header_welcome").html(name + "，欢迎来到大易物流！");
	$("#header_loginHref").hide();
	$("#header_registerHref").hide();
	
});


