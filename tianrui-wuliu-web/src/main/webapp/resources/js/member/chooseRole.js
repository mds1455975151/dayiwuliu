(function($,win){
	$('#hzBtn').off('click').on('click',function(){
		chooseRole('hz');
	});
	$('#czBtn').off('click').on('click',function(){
		chooseRole('cz');
	});
	$('#sjBtn').off('click').on('click',function(){
		chooseRole('sj');
	});
	$('#clBtn').off('click').on('click',function(){
		chooseRole('cl');
	});
	var chooseRole = function(role){
		$.post(PATH + "/trwuliu/Member/bindRole",{role:role},function(result){
			if(result.code == '000000'){
				window.location.href = PATH + "/trwuliu/Member/message/message";
			}else{
				alert(result);
			}
		},'json');
	};
})(jQuery, window);