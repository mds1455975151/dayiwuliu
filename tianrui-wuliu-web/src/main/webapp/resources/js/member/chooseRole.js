(function($,win){
	$('.btn_hz').off('click').on('click',function(){
		chooseRole('hz');
	});
	$('.btn_cz').off('click').on('click',function(){
		chooseRole('cz');
	});
	$('.btn_sj').off('click').on('click',function(){
		chooseRole('sj');
	});
	var chooseRole = function(role){
		window.location.href = PATH + "/trwuliu/Member/message/message?role="+role;
	};
})(jQuery, window);