;(function($,win){
	 creditYearArr = [2016,2017,2018,2019,2020,2021,2022,2023,2024,2025];
	var creditMonthArr = [01,02,03,04,05,06,07,08,09,10,11,12];
	init();
	
	function init(){
		initSearchContent();
		initCreditData(true);
	}
	//初始化搜索内容
	function initSearchContent(){
		for(var i=0;i<creditYearArr.length;i++){
			$('#credityear').append('<option value="'+creditYearArr[i]+'">'+creditYearArr[i]+'</option>');
		}
		for(var i=0;i<creditMonthArr.length;i++){
			$('#creditmonth').append('<option value="'+creditMonthArr[i]+'">'+creditMonthArr[i]+'</option>');
		}
	}
	
	function initCreditData(isNew){
		var params = {};
		if(!isNew){
			params.credityear = $('#credityer').val();
			params.creditmonth = $('#creditmonth').val();
		}
		$.ajax({
			url : CONTEXTPATH + '/creditManage/queryCredit',
			data : params,
			type : 'post',
			success : function(result) {
				if(result.code == '000000'){
					readerHtml(result.data);
				}else{
					alert(result.error);
				}
			}
		});
	}
	
	function readerHtml(list){
		$('#creditBody').empty();
		if(list != null && list.length > 0){
			$('#credityear').val(list[0].credityear);
			$('#creditmonth').val(list[0].creditmonth);
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var $tr = $('<tr>');
				var csp = obj.creditScoreResp;
				var addnum = csp.addassist+csp.addcontribute+csp.addspecial;
				var subnum = csp.subaccident+csp.subcomplaint+csp.subdisrupt+csp.submanned+csp.suboperation+csp.subturnover;
				$tr.append('<td>'+(i+1)+'</td>')
					.append('<td>'+obj.vendertel+'</td>')
					.append('<td>'+obj.vendername+'</td>')
					.append('<td>'+obj.vehiclesum+'</td>')
					.append('<td>'+obj.billcount+'</td>')
					.append('<td>'+obj.timelystart+'</td>')
					.append('<td>'+obj.timelydelivery+'</td>')
					.append('<td>'+obj.timelyreturn+'</td>')
					.append('<td>'+obj.cardamage+'</td>')
					.append('<td>'+obj.transportaccident+'</td>')
					.append('<td>'+addnum+'</td>')
					.append('<td>'+subnum+'</td>')
					.appendTo('#creditBody');
			}
		}else{
			//没有数据
			alert("暂无数据!");
		}
	}
	
	$('.searchCredit').off('click').on('click',function(){
		initCreditData(false);
	});
	
})(jQuery,window);